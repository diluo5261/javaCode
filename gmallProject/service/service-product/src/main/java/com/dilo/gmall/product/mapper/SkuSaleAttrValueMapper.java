package com.dilo.gmall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dilo.gmall.model.product.SkuSaleAttrValue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SkuSaleAttrValueMapper extends BaseMapper<SkuSaleAttrValue> {
    //根据spuId查询map集合数据,获取销售属性值对应的skuId组合
    List<Map> selectSaleAttrValuesBySpu(Long spuId);

}
