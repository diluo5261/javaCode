package com.dilo.gmall.product.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.model.product.BaseTrademark;
import com.dilo.gmall.product.service.BaseTrademarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "品牌接口")
@RestController
@RequestMapping("/admin/product/baseTrademark")
public class BaseTrademarkController {

    @Autowired
    private BaseTrademarkService baseTrademarkService;

    //http://api.gmall.com/admin/product/baseTrademark/{page}/{limit}
    @ApiOperation("品牌分页查询")
    @GetMapping("/{page}/{limit}")
    public Result getBaseTrademarkController(@PathVariable Long page,@PathVariable Long limit){

        //创建page对象
        Page<BaseTrademark> spuInfoPage = new Page<>(page,limit);

        //获取数据
        IPage<BaseTrademark> spuInfoPageList = baseTrademarkService.getBaseTrademarkController(spuInfoPage);
        return Result.ok(spuInfoPageList);
    }

    //http://api.gmall.com/admin/product/baseTrademark/save
    @ApiOperation(value = "添加品牌")
    @PostMapping("save")
    public Result save(@RequestBody BaseTrademark baseTrademark) {
        baseTrademarkService.save(baseTrademark);
        return Result.ok();
    }


    //http://api.gmall.com/admin/product/baseTrademark/update
    @ApiOperation(value = "修改品牌")
    @PutMapping("update")
    public Result updateById(@RequestBody BaseTrademark baseTrademark) {
        baseTrademarkService.updateById(baseTrademark);
        return Result.ok();
    }

    //http://api.gmall.com/admin/product/baseTrademark/remove/{id}
    @ApiOperation(value = "删除品牌")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        baseTrademarkService.removeById(id);
        return Result.ok();
    }

    //http://api.gmall.com/admin/product/baseTrademark/get/{id}

    @ApiOperation(value = "根据id获取品牌")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        BaseTrademark baseTrademark = baseTrademarkService.getById(id);
        return Result.ok(baseTrademark);
    }

    //http://api.gmall.com/admin/product/baseTrademark/getTrademarkList
    @ApiOperation(value = "查询所有的品牌信息")
    @GetMapping("/getTrademarkList")
    public Result getTrademarkList(){

        return Result.ok(baseTrademarkService.list(null));
    }










}
