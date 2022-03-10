package com.dilo.gmall.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.model.product.SkuInfo;
import com.dilo.gmall.product.service.ManagerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/product")
public class SkuManageController {

    //注入服务层
    @Autowired
    private ManagerService managerService;

    //http://api.gmall.com/admin/product/saveSkuInfo

    @ApiOperation("保存Sku信息")
    @PostMapping("saveSkuInfo")
    public Result saveSkuInfo(@RequestBody SkuInfo skuInfo){
        //调用服务层

        managerService.saveSkuInfo(skuInfo);
        return Result.ok();
    }

//    http://localhost/admin/product/list/1/10
    @ApiOperation("商品信息分页管理")
    @GetMapping("list/{page}/{limit}")
    public Result getSkuInfoList(@PathVariable Long page,@PathVariable Long limit){
        Page<SkuInfo> skuInfoPage = new Page<>(page,limit);
       IPage<SkuInfo> iPage= managerService.getSkuInfoList(skuInfoPage);
       return Result.ok(iPage);

    }


    //http://api.gmall.com/admin/product/onSale/{skuId}
    @ApiOperation("商品上架")
    @GetMapping("onSale/{skuId}")
    public Result onSale(@PathVariable Long skuId){
        managerService.onSale(skuId);
        return Result.ok();
    }


    //http://api.gmall.com/admin/product/cancelSale/{skuId}
    @ApiOperation("商品下架")
    @GetMapping("cancelSale/{skuId}")
    public Result cancelSale(@PathVariable Long skuId){
        managerService.cancelSale(skuId);
        return Result.ok();
    }

}
