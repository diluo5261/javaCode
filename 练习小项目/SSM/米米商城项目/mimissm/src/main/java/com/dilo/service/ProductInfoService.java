package com.dilo.service;

import com.dilo.pojo.ProductInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductInfoService {

    //显示全部商品不分页

    List<ProductInfo> getAll();

    PageInfo splitPage(int pageNum,int pageSize);

    //增加商品
    int save(ProductInfo info);

    //按照主键id查询商品
    ProductInfo getById(int pid);

    //更新商品
    int update(ProductInfo info);

    //单个商品的删除
    int delete(int pid);
}
