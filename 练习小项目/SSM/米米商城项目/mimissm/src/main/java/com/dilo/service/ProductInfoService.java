package com.dilo.service;

import com.dilo.pojo.ProductInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductInfoService {

    //显示全部商品不分页

    List<ProductInfo> getAll();

    PageInfo splitPage(int pageNum,int pageSize);
}
