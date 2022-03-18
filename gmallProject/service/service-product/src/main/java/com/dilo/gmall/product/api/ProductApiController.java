package com.dilo.gmall.product.api;

import com.alibaba.fastjson.JSONObject;
import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.model.product.*;
import com.dilo.gmall.product.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Api(tags = "提供给item调用的方法")
@RestController
@RequestMapping("/api/product")
public class ProductApiController {

    @Autowired
    private ManagerService managerService;

    //带有inner的url表示给内部微服务提供的数据
    @ApiOperation("根据skuId查询skuInfo信息,(内部使用)")
    @GetMapping("inner/getSkuInfo/{skuId}")
    public SkuInfo getSkuInfo(@PathVariable("skuId") Long skuId){
        SkuInfo skuInfo = managerService.getSkuInfo(skuId);
        return skuInfo;
    }

    @ApiOperation("根据三级分类id获取对应的分类名称")
    @GetMapping("inner/getCategoryView/{category3Id}")
    public BaseCategoryView getCategoryView(@PathVariable Long category3Id){
        BaseCategoryView categoryViewCateBy3Id = managerService.getCategoryViewCateBy3Id(category3Id);
        return categoryViewCateBy3Id;
    }

    @ApiOperation("根据skuId获取商品的价格")
    @GetMapping("inner/getSkuPrice/{skuId}")
    public BigDecimal getSkuPrice(@PathVariable Long skuId){
        return managerService.getSkuPrice(skuId);
    }


    /**
     * 根据spuId，skuId 查询销售属性集合
     * @param skuId
     * @param spuId
     * @return
     */
    @ApiOperation("查询销售属性集合")
    @GetMapping("inner/getSpuSaleAttrListCheckBySku/{skuId}/{spuId}")
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(@PathVariable("skuId") Long skuId, @PathVariable("spuId") Long spuId){
        return managerService.getSpuSaleAttrListCheckBySku(skuId, spuId);
    }

    /**
     * 根据spuId 查询map 集合属性
     * @param spuId
     * @return
     */
    @ApiOperation("根据spuId获取销售属性值Id与skuId组成map")
    @GetMapping("inner/getSkuValueIdsMap/{spuId}")
    public Map getSkuValueIdsMap(@PathVariable("spuId") Long spuId){
        //调用服务层方法
        return managerService.getSkuValueIdsMap(spuId);
    }

    @ApiOperation("获取所有分类数据的集合")
    @GetMapping("inner/getBaseCategoryList")
    public Result getBaseCategoryList(){
        //获取所有分类数据的集合
        List<JSONObject> baseCategoryList = managerService.getBaseCategoryList();
        return Result.ok(baseCategoryList);
    }

    @ApiOperation("通过品牌Id查询数据")
    @GetMapping("inner/getTradeMarkById/{tmId}")
    public BaseTrademark getTradeMarkById(@PathVariable Long tmId){
        BaseTrademark trademark = managerService.getTradeMarkById(tmId);
        return trademark;
    }


    @ApiOperation("通过skuId查询销售属性和值集合")
    @GetMapping("inner/getAttrList/{skuId}")
    public List<BaseAttrInfo> getAttrList(@PathVariable Long skuId){
        List<BaseAttrInfo> attrList = managerService.getAttrList(skuId);
        return attrList;
    }


}
