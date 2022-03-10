package com.dilo.gmall.product.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dilo.gmall.common.cache.GmallCache;
import com.dilo.gmall.common.constant.RedisConst;
import com.dilo.gmall.model.product.*;
import com.dilo.gmall.product.mapper.*;
import com.dilo.gmall.product.service.ManagerService;
import jodd.time.TimeUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ManagerServiceImpl extends ServiceImpl<SpuInfoMapper,SpuInfo> implements ManagerService {
    //服务层调用mapper层

    @Autowired
    private BaseCategory1Mapper baseCategory1Mapper;
    @Autowired
    private BaseCategory2Mapper baseCategory2Mapper;
    @Autowired
    private BaseCategory3Mapper baseCategory3Mapper;
    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;
    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;
    @Autowired
    private SpuInfoMapper spuInfoMapper;
    @Autowired
    private BaseSaleAttrMapper baseSaleAttrMapper;

    @Autowired
    private SpuImageMapper spuImageMapper;
    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;
    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;

    @Autowired
    private SkuInfoMapper skuInfoMapper;

    @Autowired
    private SkuImageMapper skuImageMapper;

    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;

    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Autowired
    private BaseCategoryViewMapper baseCategoryViewMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public List<BaseCategory1> getCategory1() {
        return baseCategory1Mapper.selectList(null);
    }

    @Override
    public List<BaseCategory2> getCategory2(Long category1Id) {
        //select * from base_category2 where category1_id = category1Id
        return baseCategory2Mapper.selectList(new QueryWrapper<BaseCategory2>().eq("category1_id",category1Id));
    }

    @Override
    public List<BaseCategory3> getCategory3(Long category2Id) {
        return baseCategory3Mapper.selectList(new QueryWrapper<BaseCategory3>().eq("category2_id",category2Id));
    }

    @Override
    public List<BaseAttrInfo> getAttrInfoList(Long category1Id, Long category2Id, Long category3Id) {

        return baseAttrInfoMapper.selectBaseAttrInfoList(category1Id, category2Id, category3Id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {

        //判断baseAttrInfo.id是否为空
        if (baseAttrInfo.getId() != null){
            baseAttrInfoMapper.updateById(baseAttrInfo);
        }else{
            //调用mapper,存储baseAttriInfo信息
            baseAttrInfoMapper.insert(baseAttrInfo);
        }

        //什么时候新增,什么时候修改,回显数据的时候不知道什么时候
        //因此先删除,再新增
        baseAttrValueMapper.delete(new QueryWrapper<BaseAttrValue>().eq("attr_id",baseAttrInfo.getId()));


        //int i = 1/0;

        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();

        if (!CollectionUtils.isEmpty(attrValueList)){
            //循环遍历集合,插入到base_attr_value表中
            for (BaseAttrValue baseAttrValue: attrValueList) {
                baseAttrValue.setAttrId(baseAttrInfo.getId());
                baseAttrValueMapper.insert(baseAttrValue);
            }
        }
    }

    @Override
    public List<BaseAttrValue> getAttrValueList(Long attrId){
        QueryWrapper<BaseAttrValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_id",attrId);


        List<BaseAttrValue> baseAttrValueList = baseAttrValueMapper.selectList(queryWrapper);

        return baseAttrValueList;
    }

    @Override
    public BaseAttrInfo getBaseAttrInfo(Long attrId) {
        BaseAttrInfo baseAttrInfo = baseAttrInfoMapper.selectById(attrId);
        if (baseAttrInfo != null){
            List<BaseAttrValue> attrValueList = getAttrValueList(attrId);
            if (!CollectionUtils.isEmpty(attrValueList)){
                baseAttrInfo.setAttrValueList(attrValueList);
            }
            return baseAttrInfo;
        }
        return null;
    }


    @Override
    public IPage<SpuInfo> getSpuInfoPage(Page<SpuInfo> spuInfoPage, SpuInfo spuInfo) {

        QueryWrapper<SpuInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category3_id",spuInfo.getCategory3Id()).orderByDesc("id");
        IPage<SpuInfo> spuInfoIPage = spuInfoMapper.selectPage(spuInfoPage, queryWrapper);

        return spuInfoIPage;
    }

    @Override
    public List<BaseSaleAttr> getBaseSaleAttrList() {
        return baseSaleAttrMapper.selectList(null);

    }

    @Override
    @Transactional
    public void saveSpuInfo(SpuInfo spuInfo) {
        //1.spu_info 商品表
        spuInfoMapper.insert(spuInfo);


        //2.spu_img
        //先获取spuImagesList集合信息
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        if (spuImageList != null && spuImageList.size() >0){
            for (SpuImage spuImage : spuImageList) {
                //前端传过来的时候没有spuId,所以要对其进行赋值
                spuImage.setSpuId(spuInfo.getId());
                spuImageMapper.insert(spuImage);
            }
        }
        //3.spu_sale_attr,销售属性表,获取当前的销售属性集合
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        if (!CollectionUtils.isEmpty(spuSaleAttrList)){
            for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
                //将spu_id进行赋值
                spuSaleAttr.setSpuId(spuInfo.getId());
                spuSaleAttrMapper.insert(spuSaleAttr);

                //4.spu_sale_attr_value,获取销售属性值
                List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
                if (!CollectionUtils.isEmpty(spuSaleAttrValueList)){
                    for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
                        //将spuId进行赋值
                        spuSaleAttrValue.setSpuId(spuInfo.getId());
                        //赋值销售属性名
                        spuSaleAttrValue.setSaleAttrName(spuSaleAttr.getSaleAttrName());
                        spuSaleAttrValueMapper.insert(spuSaleAttrValue);

                    }
                }
            }
        }
    }

    @Override
    public List<SpuImage> getSpuImageList(Long spuId) {
        return spuImageMapper.selectList(new QueryWrapper<SpuImage>().eq("spu_id", spuId));
    }

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrList(Long spuId) {
        List<SpuSaleAttr> spuSaleAttrList= spuSaleAttrMapper.selectSpuSaleAttrList(spuId);
        return spuSaleAttrList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSkuInfo(SkuInfo skuInfo) {
        /*
        skuInfo 库存单元表 --- spuInfo！
        skuImage 库存单元图片表 --- spuImage!
        skuSaleAttrValue sku销售属性值表{sku与销售属性值的中间表} --- skuInfo ，spuSaleAttrValue
        skuAttrValue sku与平台属性值的中间表 --- skuInfo ，baseAttrValue
     */

        skuInfoMapper.insert(skuInfo);

        //遍历插入图像表
        List<SkuImage> imageList = skuInfo.getSkuImageList();
        if (!CollectionUtils.isEmpty(imageList)){
            for (SkuImage image : imageList) {
                image.setSkuId(skuInfo.getId());
                skuImageMapper.insert(image);
            }
        }

        //遍历插入销售属性表
        List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
        if (!CollectionUtils.isEmpty(skuAttrValueList)){
            for (SkuAttrValue skuAttrValue : skuAttrValueList) {
                skuAttrValue.setAttrId(skuInfo.getId());
                skuAttrValueMapper.insert(skuAttrValue);
            }
        }

        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        if (!CollectionUtils.isEmpty(skuSaleAttrValueList)){
            for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValueList) {
                skuSaleAttrValue.setSkuId(skuInfo.getId());
                skuSaleAttrValue.setSpuId(skuInfo.getSpuId());
                skuSaleAttrValueMapper.insert(skuSaleAttrValue);
            }
        }
    }

    @Override
    public IPage<SkuInfo> getSkuInfoList(Page<SkuInfo> skuInfoPage) {

        return skuInfoMapper.selectPage(skuInfoPage, new QueryWrapper<SkuInfo>().orderByDesc("id"));
    }

    @Override
    public void onSale(Long skuId) {

        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setId(skuId);
        skuInfo.setIsSale(1);
        skuInfoMapper.updateById(skuInfo);
    }

    @Override
    public void cancelSale(Long skuId) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setId(skuId);
        skuInfo.setIsSale(0);
        skuInfoMapper.updateById(skuInfo);
    }

    @Override
    @GmallCache(prefix ="skuInfo:" )//加注解就能代替原来分布式锁的业务逻辑:锁getskuInfo sku:skuId:lock
    public SkuInfo getSkuInfo(Long skuId) {
        //调用redisson
        //return getSkuInfoRedisson(skuId);
        return getInfoDB(skuId);
    }

    private SkuInfo getSkuInfoRedisson(Long skuId) {
        try {
            //获取缓存数据,如何获取数据,key是谁
            String skukey = RedisConst.SKUKEY_PREFIX+ skuId +RedisConst.SKUKEY_SUFFIX;
            //配置类中已经将String,Hash 做了序列化处理
            SkuInfo skuInfo = (SkuInfo) redisTemplate.opsForValue().get(skukey);

            if (skuInfo == null){
                //上锁
                //定义锁的key
                String lockKey = RedisConst.SKUKEY_PREFIX+ skuId +RedisConst.SKULOCK_SUFFIX;
                RLock lock = redissonClient.getLock(lockKey);
                boolean flag = lock.tryLock(RedisConst.SKULOCK_EXPIRE_PX1, RedisConst.SKULOCK_EXPIRE_PX2, TimeUnit.SECONDS);
                //判断获取锁是否成功
                if (flag){
                    try{
                        //加锁成功.获取到锁,查询数据库
                        skuInfo = getInfoDB(skuId);

                        if (skuInfo == null){
                            //数据库没有这个数据,防止缓存穿透,将空的对象放入缓存
                            SkuInfo skuInfo1 = new SkuInfo();
                            redisTemplate.opsForValue().set(skukey,skuInfo1,RedisConst.SKUKEY_TIMEOUT, TimeUnit.SECONDS);
                            return skuInfo1;
                        }
                        //数据库中有这个数据,将数据放入到缓存中
                        redisTemplate.opsForValue().set(skukey,skuInfo,RedisConst.SKUKEY_TIMEOUT, TimeUnit.SECONDS);
                        return skuInfo;

                    }finally {
                        lock.unlock();
                    }
                }else {
                    //没有获取到锁
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //自旋
                    return getSkuInfo(skuId);
                }
            }else{
                //缓存有数据
                return skuInfo;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getInfoDB(skuId);
    }

    /**
     * 根据skuId获取skuInfo --redis 做分布式锁
     * @param skuId
     * @return
     */
    private SkuInfo getInfoRedis(Long skuId) {
        //获取缓存数据,如何获取数据,key是谁
        String skukey = RedisConst.SKUKEY_PREFIX+ skuId +RedisConst.SKUKEY_SUFFIX;
        try {
            SkuInfo skuInfo = (SkuInfo) redisTemplate.opsForValue().get(skukey);
            if (skuInfo == null){
                //查询数据库
                //1.加锁
                String lockKey = RedisConst.SKUKEY_PREFIX+ skuId +RedisConst.SKULOCK_SUFFIX;
                String uuid = UUID.randomUUID().toString();
                Boolean isExist = redisTemplate.opsForValue().setIfAbsent(lockKey, uuid, RedisConst.SKULOCK_EXPIRE_PX1, TimeUnit.SECONDS);

                if (isExist){
                    //加锁成功.获取到锁,查询数据库
                    skuInfo = getInfoDB(skuId);

                    if (skuInfo == null){
                        //数据库没有这个数据,防止缓存穿透,将空的对象放入缓存
                        SkuInfo skuInfo1 = new SkuInfo();
                        redisTemplate.opsForValue().set(skukey,skuInfo1,RedisConst.SKUKEY_TIMEOUT, TimeUnit.SECONDS);
                        return skuInfo1;
                    }
                    //数据库中有这个数据,将数据放入到缓存中
                    redisTemplate.opsForValue().set(skukey,skuInfo,RedisConst.SKUKEY_TIMEOUT, TimeUnit.SECONDS);

                    String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                    DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
                    redisScript.setScriptText(script);
                    redisScript.setResultType(Long.class);

                    //删除锁,对应的id
                    redisTemplate.execute(redisScript,Arrays.asList(lockKey),uuid);
                    return skuInfo;

                }
            }else{
                return skuInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getInfoDB(skuId);
    }

    private SkuInfo getInfoDB(Long skuId) {
        //select * from sku_info where id = skuId
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);

        //根据skuid查询图片信息
        if (skuInfo != null){
            List<SkuImage> skuImageList = skuImageMapper.selectList(new QueryWrapper<SkuImage>().eq("sku_id", skuId));
            skuInfo.setSkuImageList(skuImageList);
        }

        return skuInfo;
    }

    @Override
    @GmallCache(prefix = "categoryViewCateBy3Id:")
    public BaseCategoryView getCategoryViewCateBy3Id(Long category3Id) {
        BaseCategoryView baseCategoryView = baseCategoryViewMapper.selectById(category3Id);

        return baseCategoryView;
    }

    @Override
    @GmallCache(prefix = "skuPrice:")
    public BigDecimal getSkuPrice(Long skuId) {
        //select price from sku_info where id = skuId

        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);
        if (skuInfo != null){
            return skuInfo.getPrice();
        }
        return new BigDecimal(0);
    }

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(Long skuId, Long spuId) {
        List<SpuSaleAttr> spuSaleAttrList = spuSaleAttrMapper.selectSpuSaleAttrListCheckBySku(skuId, spuId);
        return spuSaleAttrList;
    }

    @Override
    public Map getSkuValueIdsMap(Long spuId) {
        //执行sql语句获取数据结果集:编写xml,应该写在那个mapper中,数据来自哪个表,就写在哪个mapper中
        HashMap<Object, Object> resultMap = new HashMap<>();
        List<Map> mapList = skuSaleAttrValueMapper.selectSaleAttrValuesBySpu(spuId);
        if (!CollectionUtils.isEmpty(mapList)){
            for (Map map : mapList) {
                resultMap.put(map.get("value_ids"),map.get("sku_id"));
            }
        }
        return resultMap;
    }

    @Override
    public List<SpuSaleAttr> selectSaleAttrValuesBySpu(Long skuId, Long spuId) {
        return spuSaleAttrMapper.selectSpuSaleAttrListCheckBySku(skuId,spuId);

    }

    @Override
    @GmallCache(prefix = "baseCategoryList")
    public List<JSONObject> getBaseCategoryList() {
        //新建一个集合
        List<JSONObject> list = new ArrayList<>();

        //查询所有的分类数据
        List<BaseCategoryView> baseCategoryViewList = baseCategoryViewMapper.selectList(null);

        //声明一个index
        int index = 1;

        //按照一级分类id进行分组
        Map<Long, List<BaseCategoryView>> category1Map = baseCategoryViewList.stream().collect(Collectors.groupingBy(BaseCategoryView::getCategory1Id));


        //遍历集合,获取一级分类下的所有数据
        Iterator<Map.Entry<Long, List<BaseCategoryView>>> iterator = category1Map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Long, List<BaseCategoryView>> entry = iterator.next();

            //获取一级分类的id
            Long category1Id = entry.getKey();

            //获取一级分类下的所有数据
            List<BaseCategoryView> category2List = entry.getValue();

            //获取一级分类的名字
            String categoryName = category2List.get(0).getCategory1Name();

            //赋值
            JSONObject category1 = new JSONObject();
            category1.put("index",index);
            category1.put("categoryId",category1Id);
            category1.put("categoryName",categoryName);

            //index迭代
            index++;

            //-------------------------------------------------------------------------------------------
            //获取二级分类的数据
            Map<Long, List<BaseCategoryView>> category2Map = category2List.stream().collect(Collectors.groupingBy(BaseCategoryView::getCategory2Id));

            //声明一个集合存储二级分类数据
            ArrayList<JSONObject> category2Child = new ArrayList<>();

            //循环遍历,获取二级分类下的所有数据
            Iterator<Map.Entry<Long, List<BaseCategoryView>>> iterator1 = category2Map.entrySet().iterator();
            while(iterator1.hasNext()){
                Map.Entry<Long, List<BaseCategoryView>> entry1 = iterator1.next();

                //获取二级分类的id
                Long category2Id = entry1.getKey();

                //获取二级分类的所有数据
                List<BaseCategoryView> category3List = entry1.getValue();

                //获取二级分类的名字
                String category2Name = category3List.get(0).getCategory2Name();

                //赋值
                JSONObject category2 = new JSONObject();

                category2.put("categoryName",category2Name);
                category2.put("categoryId",category2Id);

                //声明一个集合存储二级分类数据
                category2Child.add(category2);

                //-------------------获取三级分类集合数据------------------------------------------

                //声明一个三级分类集合数据
                ArrayList<JSONObject> category3Child = new ArrayList<>();

                category3List.forEach(baseCategoryView -> {
                    JSONObject category3 = new JSONObject();
                    category3.put("categoryName",baseCategoryView.getCategory3Name());
                    category3.put("categoryId",baseCategoryView.getCategory3Id());
                    category3Child.add(category3);
                });

                //将三级数据放到二级中
                category2.put("categoryChild",category3Child);

            }

            //将二级分类数据放入到一级对象中
            category1.put("categoryChild",category2Child);
            //将一级分类数据放入到list中
            list.add(category1);
        }
        //返回集合数据
        return list;
    }
}
