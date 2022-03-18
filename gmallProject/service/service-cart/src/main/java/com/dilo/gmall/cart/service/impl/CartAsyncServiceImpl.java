package com.dilo.gmall.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dilo.gmall.cart.mapper.CartInfoMapper;
import com.dilo.gmall.cart.service.CartAsyncService;
import com.dilo.gmall.model.cart.CartInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class CartAsyncServiceImpl implements CartAsyncService {

    @Autowired
    private CartInfoMapper cartInfoMapper;
    @Override
    @Async
    public void updateCartInfo(CartInfo cartInfo) {
        //代码是有问题的,后面继续优化,
        //cartInfoMapper.updateById(cartInfo);
        QueryWrapper<CartInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",cartInfo.getUserId());
        queryWrapper.eq("sku_id",cartInfo.getSkuId());


        //使用skuId和userId代替cartInfo.id
        cartInfoMapper.update(cartInfo,queryWrapper);
    }

    @Override
    @Async
    public void saveCartInfo(CartInfo cartInfo) {

        cartInfoMapper.insert(cartInfo);
    }

    @Override
    @Async
    public void deleteCartInfo(CartInfo cartInfo) {
        QueryWrapper<CartInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",cartInfo.getUserId());

        cartInfoMapper.delete(queryWrapper);
    }

    @Override
    @Async
    public void deleteCartInfo(String userId) {
        QueryWrapper<CartInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);

        cartInfoMapper.delete(queryWrapper);
    }

    @Override
    @Async
    public void checkCart(String userId, Integer isChecked, Long skuId) {
        CartInfo cartInfo = new CartInfo();
        cartInfo.setIsChecked(isChecked);

        UpdateWrapper<CartInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id",userId).eq("sku_id",skuId);

        cartInfoMapper.update(cartInfo,updateWrapper);
    }

    @Override
    public void deleteCart(String userId, Long skuId) {
        QueryWrapper<CartInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("sku_id",skuId);
        cartInfoMapper.delete(queryWrapper);
    }
}
