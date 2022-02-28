package com.dilo.staService.scheduled;

import com.dilo.staService.service.StatisticsDailyService;
import com.dilo.staService.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTask {

    @Autowired
    private StatisticsDailyService dailyService;

    //每天凌晨1点跑前一天数据
    @Scheduled(cron = "0 0 1 * * ? ")
    public void scheduledTaskStatistics(){
        String day = DateUtil.formatDate(DateUtil.addDays(new Date(),-1));

        dailyService.createDaily(day);
    }
}
