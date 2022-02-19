package com.dilo.cmsservice.controller;


import com.dilo.cmsservice.entity.CrmBanner;
import com.dilo.cmsservice.service.CrmBannerService;
import com.dilo.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author dilo
 * @since 2022-02-19
 */

@Api("前台展示")
@RestController
//@RequestMapping("/cmsservice/banner")
@CrossOrigin
public class CrmBannerController {

    @Autowired
    private CrmBannerService bannerService;


    @ApiOperation("查询所有banner信息")
    @GetMapping("/getAllBanner")
    public R getAllBanner(){

        List<CrmBanner> bannerList = bannerService.list();
        return R.ok().data("bannerList",bannerList);
    }



}

