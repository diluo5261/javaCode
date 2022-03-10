package com.dilo.gmall.product.controller;


import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.model.product.BaseAttrInfo;
import com.dilo.gmall.model.product.BaseCategory1;
import com.dilo.gmall.model.product.BaseCategory2;
import com.dilo.gmall.model.product.BaseCategory3;
import com.dilo.gmall.product.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商品基础属性接口")
@RestController
@RequestMapping("/admin/product")
public class BaseManageController {

    @Autowired
    private ManagerService managerService;

    //http://api.gmall.com/admin/product/getCategory1

    /**
     * 查询所有一级分类信息
     * @return
     */
    @ApiOperation("查询所有一级分类信息")
    @GetMapping("/getCategory1")
    public Result<List<BaseCategory1>> getCategory1(){
        List<BaseCategory1> category1List = managerService.getCategory1();
        return Result.ok(category1List);
    }

    //http://api.gmall.com/admin/product/getCategory2/{category1Id}
    @ApiOperation("获取二级分类")
    @GetMapping("getCategory2/{category1Id}")
    public Result<List<BaseCategory2>> getCategory2(@PathVariable Long category1Id){
        List<BaseCategory2> category2List = managerService.getCategory2(category1Id);
        return Result.ok(category2List);
    }

    //http://api.gmall.com/admin/product/getCategory3/{category2Id}

    @ApiOperation("根据二级id获取三级分类")
    @GetMapping("/getCategory3/{category2Id}")
    public Result<List<BaseCategory3>> getCategory3(@PathVariable Long category2Id){
        List<BaseCategory3> category3List = managerService.getCategory3(category2Id);
        return Result.ok(category3List);
    }


    //http://api.gmall.com/admin/product/attrInfoList/{category1Id}/{category2Id}/{category3Id}
    @ApiOperation("通过分类id获取平台属性")
    @GetMapping("attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result<List<BaseAttrInfo>> attrInfoList(@PathVariable Long category1Id,
                               @PathVariable Long category2Id,
                               @PathVariable Long category3Id){
        List<BaseAttrInfo> attrInfoList = managerService.getAttrInfoList(category1Id, category2Id, category3Id);
        return Result.ok(attrInfoList);
    }


    //http://api.gmall.com/admin/product/saveAttrInfo

    @ApiOperation("保存/修改平台属性")
    @PostMapping("/saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        //前端数据封装到baseAttriInfo中
        managerService.saveAttrInfo(baseAttrInfo);
        return Result.ok();
    }

    //http://api.gmall.com/admin/product/getAttrValueList/{attrId}

    @ApiOperation("根据平台属性ID获取平台属性对象数据")
    @GetMapping("/getAttrValueList/{attrId}")
    public Result getAttrValueList(@PathVariable Long attrId){

        //先判断一下当前是否存在该属性,如果有属性,则调用属性对象的属性集合
        BaseAttrInfo baseAttrInfo = managerService.getBaseAttrInfo(attrId);
        if (baseAttrInfo != null){
            return Result.ok(baseAttrInfo.getAttrValueList());
        }

        //List<BaseAttrValue> baseAttrValueList=managerService.getAttrValueList(attrId);
        //返回数据
        //return Result.ok(baseAttrValueList);
        return Result.ok();
    }
}
