package com.dilo.gmall.product.client;

import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.model.product.BaseCategoryView;
import com.dilo.gmall.model.product.SkuInfo;
import com.dilo.gmall.model.product.SpuSaleAttr;
import com.dilo.gmall.product.client.impl.ProductDegradeFeignClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@FeignClient(value = "service-product",fallback = ProductDegradeFeignClient.class)
public interface ProductFeignClient {
    //需要将controller控制器中的Url地址写在这个位置

    @ApiOperation("根据skuId查询skuInfo信息,(内部使用)")
    @GetMapping("/api/product/inner/getSkuInfo/{skuId}")
    public SkuInfo getSkuInfo(@PathVariable("skuId") Long skuId);

    @ApiOperation("根据三级分类id查询分类信息")
    @GetMapping("/api/product/inner/getCategoryView/{category3Id}")
    public BaseCategoryView getCategoryView(@PathVariable Long category3Id);

    @ApiOperation("获取sku最新价格")
    @GetMapping("/api/product/inner/getSkuPrice/{skuId}")
    public BigDecimal getSkuPrice(@PathVariable Long skuId);


    /**
     * 根据spuId，skuId 查询销售属性集合
     *
     * @param skuId
     * @param spuId
     * @return
     */
    @ApiOperation("查询销售属性集合")
    @GetMapping("/api/product/inner/getSpuSaleAttrListCheckBySku/{skuId}/{spuId}")
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(@PathVariable("skuId") Long skuId, @PathVariable("spuId") Long spuId);

    /**
     * 根据spuId 查询map 集合属性
     *
     * @param spuId
     * @return
     */
    @GetMapping("/api/product/inner/getSkuValueIdsMap/{spuId}")
    public Map getSkuValueIdsMap(@PathVariable("spuId") Long spuId);


    /**
     * 获取全部分类信息
     * @return
     */
    @GetMapping("/api/product/inner/getBaseCategoryList")
    public Result getBaseCategoryList();

}
