package com.dilo.service.impl;

import com.dilo.mapper.ProductInfoMapper;
import com.dilo.pojo.ProductInfo;
import com.dilo.pojo.ProductInfoExample;
import com.dilo.service.ProductInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    //切记业务逻辑层,一定要有数据访问层的对象
    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> getAll() {
        //查询所有的商品,没有条件,new 一个 EXample对象,不需要任何条件
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }

    @Override
    public PageInfo splitPage(int pageNum, int pageSize) {
        //分页插件使用 PageHelper工具类完成分页设置
        PageHelper.startPage(pageNum,pageSize);

        //进行pageInfo的数据封装
        //进行有条件的查询操作,必须要创建 ProductorInfoExample对象
        ProductInfoExample example = new ProductInfoExample();
        //设置排序,按主键降序排序,
        //select * from product_info order by p_id desc
        example.setOrderByClause("p_id desc");

        //设置排序后,取集合,切记,切记:一定在取集合之前设置 PageHelper.startPage(pageNum,pageSize)
        List<ProductInfo> list = productInfoMapper.selectByExample(example);
        //将查询到的集合封装进 PageInfo 对象中
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public int save(ProductInfo info) {
        return productInfoMapper.insert(info);
    }

    @Override
    public ProductInfo getById(int pid) {
        return productInfoMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int update(ProductInfo info) {
        return productInfoMapper.updateByPrimaryKey(info);

    }

    @Override
    public int delete(int pid) {
      return productInfoMapper.deleteByPrimaryKey(pid);
    }
}
