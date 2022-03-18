package com.dilo.gmall.cart.service;

import com.dilo.gmall.model.cart.CartInfo;

import java.util.List;

public interface CartInfoService {

    /**
     * 添加购物车,商品ID,商品数量
     * @param skuId
     * @param userId
     * @param skuNum
     */
    void addToCart(Long skuId,String userId,Integer skuNum);

    /**
     * 通过用户Id查询购物车列表
     * @param userId
     * @param userTempId
     * @return
     */
    List<CartInfo> getCartList(String userId,String userTempId);

    /**
     * 更改选中状态
     * @param userId
     * @param isChecked
     * @param skuId
     */
    void checkCart(String userId,Integer isChecked,Long skuId);

    /**
     * 删除
     * @param userId
     * @param skuId
     */
    void deleteCart(String userId, Long skuId);

    /**
     * 根据用户Id 查询购物车列表
     *
     * @param userId
     * @return
     */
    List<CartInfo> getCartCheckedList(String userId);

    /**
     * 根据userId查询购物车最新最新
     * @param userId
     */
    List<CartInfo> loadCartCache(String userId);
}
