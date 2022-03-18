package com.dilo.gmall.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dilo.gmall.cart.mapper.CartInfoMapper;
import com.dilo.gmall.cart.service.CartAsyncService;
import com.dilo.gmall.cart.service.CartInfoService;
import com.dilo.gmall.common.constant.RedisConst;
import com.dilo.gmall.common.util.DateUtil;
import com.dilo.gmall.model.cart.CartInfo;
import com.dilo.gmall.model.product.SkuInfo;
import com.dilo.gmall.product.client.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CartInfoServiceImpl implements CartInfoService {

    @Autowired
    private CartInfoMapper cartInfoMapper;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CartAsyncService cartAsyncService;

    @Override
    public List<CartInfo> getCartCheckedList(String userId) {
        //获取购物车数据接口

        //获取kwy
        String cartKey = this.getCartKey(userId);
        List<CartInfo> cartInfoList = redisTemplate.boundHashOps(cartKey).values();
        cartInfoList.stream().filter(cartInfo -> {
            return cartInfo.getIsChecked().intValue() == 1;
        });
        return cartInfoList;


    }

    @Override
    public void deleteCart(String userId, Long skuId) {

        //删除mysql
       cartAsyncService.deleteCart(userId, skuId);
       
       //删除redis
        String cartKey = this.getCartKey(userId);
        Boolean flag = redisTemplate.boundHashOps(cartKey).hasKey(skuId.toString());
        if (flag){
            //删除购物车中的某一项
            redisTemplate.boundHashOps(cartKey).delete(skuId.toString());
        }



    }

    @Override
    public void checkCart(String userId, Integer isChecked, Long skuId) {
        //操作数据库
        cartAsyncService.checkCart(userId, isChecked, skuId);
        //同步redis
        //1.获取key
        String cartKey = this.getCartKey(userId);
        //2.获取修改的cartInfo
        CartInfo cartInfo = (CartInfo) redisTemplate.opsForHash().get(cartKey, skuId.toString());

        if (cartInfo != null){
            //变更状态
            cartInfo.setIsChecked(isChecked);
            redisTemplate.opsForHash().put(cartKey,skuId.toString(),cartInfo);

            //可以考虑是否需要重新设置过期时间
        }

        //redisTemplate.boundHashOps(cartKey).hasKey(skuId.toString())

    }

    @Override
    public List<CartInfo> getCartList(String userId, String userTempId) {
        List<CartInfo> cartInfoList = new ArrayList<>();
        //1.先查询数据库
        //2.判断查询结果
        List<CartInfo> cartList = new ArrayList<>();
        if (!StringUtils.isEmpty(userId)){
            //登录的情况下,有可能发生合并购物车
            //获取临时购物车数据
            if (StringUtils.isEmpty(userTempId)){
                cartInfoList = this.getCartList(userId);
                return cartInfoList;
            }else{
                cartList = this.getCartList(userTempId);
            }


            if(!CollectionUtils.isEmpty(cartList)){
                //说明未登录购物车数据有值,需要发生合并操作
                cartInfoList = this.mergeToCartList(cartList,userId);
                //删除临时购物车数据
                this.deleteCartList(userTempId);

            }else {
                //只查询登录购物车数据
                cartInfoList = this.getCartList(userId);
            }

        }

        //查询临时购物车数据
        if (StringUtils.isEmpty(userId)){
            cartInfoList = this.getCartList(userTempId);
        }

        return cartInfoList;
    }

    /**
     * 临时购车与登录购物车进行合并
     * @param cartInfoNoLoginList
     * @param userId
     * @return
     */
    private List<CartInfo> mergeToCartList(List<CartInfo> cartInfoNoLoginList, String userId) {
            /*
    demo1:
        登录：
            37 1
            38 1
        未登录：
            37 1
            38 1
            39 1
        合并之后的数据
            37 2
            38 2
            39 1

            1.根据userID获取到登录用户的购物车集合数据
            2.做合并处理,处理条件,skuId相同,数量相同
            3.相同的做update,没有相同的insert
            4.将最终的结果进行返回
         */

        List<CartInfo> cartInfoLoginList = this.getCartList(userId);
        //第一种方案,双重for遍历,根据skuId是否相同
        //第二种方案:使用map做包含cartInfoLoginList编程map集合
        Map<Long, CartInfo> longCartInfoMap  = cartInfoLoginList.stream().collect(Collectors.toMap(CartInfo::getSkuId, cartInfo -> {
            return cartInfo;
        }));

        //遍历临时购物车项的skuID
        for (CartInfo cartInfo : cartInfoNoLoginList) {
            //取出每一个未登录购物车项中的skuId
            Long skuId = cartInfo.getSkuId();
            if (longCartInfoMap.containsKey(skuId)){
                //数量相加
                CartInfo cartLoginInfo = longCartInfoMap.get(skuId);
                cartLoginInfo.setSkuNum(cartLoginInfo.getSkuNum()+cartInfo.getSkuNum());
                cartLoginInfo.setUpdateTime(new Timestamp(new Date().getTime()));
                //判断未登录购物车的选中状态
                if (cartInfo.getIsChecked().intValue() == 1){
                    cartLoginInfo.setIsChecked(1);
                }

                //更新数据库
                QueryWrapper<CartInfo> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("user_id",cartLoginInfo.getUserId());
                queryWrapper.eq("sku_id",cartLoginInfo.getSkuId());
                cartInfoMapper.update(cartLoginInfo,queryWrapper);   //如果是异步,建议redis没有过期时间
                /*
                购物车存储方案,
                redis缓存(过期时间) mysql持久化
                redis缓存  mysql持久化  ,同步reids异步数据库
                只有redis
                 */
            }else{
                //登录购物车中没有数据,插入购物车
                cartInfo.setUserId(userId);
                cartInfo.setCreateTime(new Timestamp(new Date().getTime()));
                cartInfo.setUpdateTime(new Timestamp(new Date().getTime()));
                cartInfoMapper.insert(cartInfo);
            }
        }

        //最终返回
        List<CartInfo> cartList = this.loadCartCache(userId);
        return cartList;
    }

    /**
     * 删除临时购物车
     * @param userTempId
     */
    private void deleteCartList(String userTempId) {
        //删除数据库,redis
        //QueryWrapper<CartInfo> queryWrapper = new QueryWrapper<>();
        //queryWrapper.eq("user_id",userTempId);
        //
        //cartInfoMapper.delete(queryWrapper);
        cartAsyncService.deleteCartInfo(userTempId);

        //删除redis
        String cartKey = this.getCartKey(userTempId);
        if (redisTemplate.hasKey(cartKey)){
            redisTemplate.delete(cartKey);
        }

    }

    /**
     * 根据用户id获取购物车
     * @param userId
     * @return
     */
    private List<CartInfo> getCartList(String userId) {
        //获取购物车的key
        String cartKey = this.getCartKey(userId);
        //使用key获取数据
        List<CartInfo> cartInfoList = redisTemplate.opsForHash().values(cartKey);

        //判断当前集合是否为空
        if (CollectionUtils.isEmpty(cartInfoList)){
            //缓存中没有数据,从数据库获取数据并放入缓存
            cartInfoList = this.loadCartCache(userId);
        }
        //先排序
        cartInfoList.sort(new Comparator<CartInfo>() {
            @Override
            public int compare(CartInfo o1, CartInfo o2) {
                return DateUtil.truncatedCompareTo(o2.getUpdateTime(),o1.getUpdateTime(),Calendar.SECOND);
            }
        });

        //cartInfoList.sort((CartInfo o1,CartInfo o2)->{
        //     DateUtil.truncatedCompareTo(o1.getUpdateTime(),o2.getUpdateTime(), Calendar.SECOND);
        //});

        return cartInfoList;

    }

    /**
     * 根据用户id查询数据,并方法缓存
     * @param userId
     * @return
     */
    public List<CartInfo> loadCartCache(String userId) {
        //当缓存中的购物车数据为空的时候,当前购物车的价格可能会发生变动,所以要查询一下实施价格
        List<CartInfo> cartInfoList = cartInfoMapper.selectList(new QueryWrapper<CartInfo>()
                .eq("user_id", userId).orderByDesc("update_time"));
        //如果为空,数据库中没有数据,则直接返回
        if (CollectionUtils.isEmpty(cartInfoList)){
            return cartInfoList;
        }


        //获取到当前购物车的key
        String cartKey = this.getCartKey(userId);
        //循环遍历当前购物车集合,放入缓存,同时将skuPrice赋值
        for (CartInfo cartInfo : cartInfoList) {
            cartInfo.setSkuPrice(productFeignClient.getSkuPrice(cartInfo.getSkuId()));
            redisTemplate.opsForHash().put(cartKey,cartInfo.getSkuId().toString(),cartInfo);
        }
        //也可以使用hmet一次写入多个数据
        //设置过期时间
        this.setCartKeyExpire(cartKey);
        return cartInfoList;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addToCart(Long skuId, String userId, Integer skuNum) {
        /*
        判断购物车中是否有该商品
        true:数量相加
        false:insert
         */

        addToCartOne(skuId, userId, skuNum);

    }



    private void addToCartOne(Long skuId, String userId, Integer skuNum) {
        String cartKey = this.getCartKey(userId);

        //如果购物车的key,在缓存中的数据不一致,把数据库中的数据全部放到缓存
        if (!redisTemplate.hasKey(cartKey)){
            this.loadCartCache(userId);

        }

        //select * from
        //优化完之后,代码走到这里说明已经有数据了,不需要查询数据库了,查询缓存就可以了,下面的代码就不需要了,hget key field
        CartInfo cartInfo = (CartInfo) redisTemplate.boundHashOps(cartKey).get(skuId.toString());
        /*QueryWrapper<CartInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("sku_id", skuId);
        CartInfo cartInfo = cartInfoMapper.selectOne(queryWrapper);*/
        //添加数据库之前,先查询一些缓存中是否有购物车key,如果没有这个key,则说明缓存过期,加载到缓存,如果有,说明这个缓存数据是全的

        if (cartInfo != null){
            //购物车已存在
            //数量相加
            cartInfo.setSkuNum(cartInfo.getSkuNum()+ skuNum);
            //赋值商品的实时价格
            cartInfo.setSkuPrice(productFeignClient.getSkuPrice(skuId));
            //重新设置修改 时间
            cartInfo.setUpdateTime(new Timestamp(new Date().getTime()));
            cartInfo.setIsChecked(1);

            //执行更新语句
            //cartInfoMapper.updateById(cartInfo);
            cartAsyncService.updateCartInfo(cartInfo);
        }else{
            //购物车不存在,插入到购物车
            SkuInfo skuInfo = productFeignClient.getSkuInfo(skuId);
            cartInfo = new CartInfo();
            cartInfo.setUserId(userId);
            cartInfo.setSkuId(skuId);
            cartInfo.setCartPrice(skuInfo.getPrice());
            cartInfo.setSkuPrice(skuInfo.getPrice());
            cartInfo.setSkuNum(skuNum);
            cartInfo.setImgUrl(skuInfo.getSkuDefaultImg());
            cartInfo.setCreateTime(new Timestamp(new Date().getTime()));
            cartInfo.setUpdateTime(new Timestamp(new Date().getTime()));

            //cartInfoMapper.insert(cartInfo);
            cartAsyncService.saveCartInfo(cartInfo);
        }


        //定义一个key,数据类型使用Hash hset key field value
        //cartkey = user:userId:cart
        //field = skuId
        //value = cartInfo
        //String cartKey = this.getCartKey(userId);

        //将购物车数据放入缓存
        redisTemplate.opsForHash().put(cartKey,String.valueOf(skuId),cartInfo);
        //购物车添加到了mysql,redis

        //给当前购物车设置一个过期时间
        this.setCartKeyExpire(cartKey);
    }


    /**
     * 根据用户ID获取购物车CartKey
     * @param userId
     * @return
     */
    private String getCartKey(String userId) {
        String cartKey = RedisConst.USER_KEY_PREFIX+ userId +RedisConst.USER_CART_KEY_SUFFIX;
        return cartKey;
    }

    /**
     * 给购物车设置一个过期时间
     * @param cartKey
     */
    private void setCartKeyExpire(String cartKey) {
        redisTemplate.expire(cartKey,RedisConst.USER_CART_EXPIRE, TimeUnit.SECONDS);
    }


}
