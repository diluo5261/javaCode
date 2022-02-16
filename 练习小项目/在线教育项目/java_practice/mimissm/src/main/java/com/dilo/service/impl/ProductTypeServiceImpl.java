package com.dilo.service.impl;

import com.dilo.mapper.ProductInfoMapper;
import com.dilo.mapper.ProductTypeMapper;
import com.dilo.pojo.ProductType;
import com.dilo.pojo.ProductTypeExample;
import com.dilo.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {

    //在业务逻辑层,一定会有数据访问层的对象
    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> getAll() {
        return productTypeMapper.selectByExample(new ProductTypeExample());
    }
}
