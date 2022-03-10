package com.dilo.gmall.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.model.product.SpuImage;
import com.dilo.gmall.model.product.SpuInfo;
import com.dilo.gmall.model.product.SpuSaleAttr;
import com.dilo.gmall.product.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商品属性列表分页")
@RestController
@RequestMapping("admin/product")
public class SpuManageController {

    @Autowired
    private ManagerService managerService;

    //http://api.gmall.com/admin/product/{page}/{limit}?category3Id=61

    @ApiOperation("根据查询条件分页查询")
    @GetMapping("/{page}/{limit}")
    public Result getSpuInfoPage(@PathVariable Long page,
                                 @PathVariable Long limit,
                                 SpuInfo spuInfo){

        //创建page对象
        Page<SpuInfo> spuInfoPage = new Page<>(page,limit);

        //获取数据
        IPage<SpuInfo> spuInfoPageList = managerService.getSpuInfoPage(spuInfoPage,spuInfo);

        return Result.ok(spuInfoPageList);
    }

//    http://api.gmall.com/admin/product/baseSaleAttrList
    @ApiOperation("查询销售属性")
    @GetMapping("/baseSaleAttrList")
    public Result baseSaleAttrList(){
    //    查询所有的的销售属性集合

        return Result.ok(managerService.getBaseSaleAttrList());
    }

//    http://localhost/admin/product/saveSpuInfo
    @ApiOperation("保存方法")
    @PostMapping("/saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuInfo spuInfo){
        // 调用服务层的保存方法
        managerService.saveSpuInfo(spuInfo);
        return Result.ok();
    }

//    http://api.gmall.com/admin/product/spuImageList/{spuId}
    @ApiOperation("根据id查询图片信息")
    @GetMapping("/spuImageList/{spuId}")
    public Result getSpuImageList(@PathVariable Long spuId){
        //调用服务层方法
        List<SpuImage> imageList = managerService.getSpuImageList(spuId);
        return Result.ok(imageList);
    }

//    http://api.gmall.com/admin/product/spuSaleAttrList/{spuId}

    @ApiOperation("获取销售属性列表")
    @GetMapping("spuSaleAttrList/{spuId}")
    public Result<List<SpuSaleAttr>> getSpuSaleAttrList(@PathVariable("spuId") Long spuId) {
        List<SpuSaleAttr> spuSaleAttrList = managerService.getSpuSaleAttrList(spuId);
        return Result.ok(spuSaleAttrList);
    }




}
