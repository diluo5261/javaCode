package com.dilo.gmall.cart.service;

import com.dilo.gmall.model.cart.CartInfo;

/**
 * 异步曹操,同步操作redis,异步操作mysql
 */
public interface CartAsyncService {

    /**
     * 修改购物车
     * @param cartInfo
     */
    void updateCartInfo(CartInfo cartInfo);

    /**
     * 保存购物车
     * @param cartInfo
     */
    void saveCartInfo(CartInfo cartInfo);

    /**
     * 删除购物车
     * @param cartInfo
     */
    void deleteCartInfo(CartInfo cartInfo);
    void deleteCartInfo(String userId);

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


}
