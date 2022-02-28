package com.dilo.staService.service;

import com.dilo.staService.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author dilo
 * @since 2022-02-24
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void createDaily(String day);

    Map<String, Object> getStaDaily(String type, String begin, String end);
}
