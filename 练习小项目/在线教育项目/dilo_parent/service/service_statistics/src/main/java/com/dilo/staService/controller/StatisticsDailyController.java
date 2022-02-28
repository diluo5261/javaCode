package com.dilo.staService.controller;


import com.dilo.commonutils.R;
import com.dilo.staService.client.UcenterClient;
import com.dilo.staService.service.StatisticsDailyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author dilo
 * @since 2022-02-24
 */
@RestController
@RequestMapping("/staService/stadaily")
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService dailyService;

    @ApiOperation("生成统计数据")
    @PostMapping("/createStaDaily/{day}")
    public R createStaDaily(@PathVariable String day) {
        dailyService.createDaily(day);
        return R.ok();
    }

    @ApiOperation("查询统计统计数据")
    @GetMapping("/getStaDaily/{type}/{begin}/{end}")
    public R getStaDaily(@PathVariable String type,
                         @PathVariable String begin,
                         @PathVariable String end) {

        Map<String,Object> map = dailyService.getStaDaily(type,begin,end);

        return R.ok().data(map);
    }
}

