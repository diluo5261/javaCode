package com.dilo.staService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dilo.staService.client.UcenterClient;
import com.dilo.staService.entity.StatisticsDaily;
import com.dilo.staService.mapper.StatisticsDailyMapper;
import com.dilo.staService.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author dilo
 * @since 2022-02-24
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public void createDaily(String day) {
        //1.先删除数据,保证数据的唯一性
        baseMapper.delete(new QueryWrapper<StatisticsDaily>().eq("date_calculated", day));

        //2.统计数据
        int countRegister = ucenterClient.countRegister(day);
        Integer loginNum = RandomUtils.nextInt(100, 200);//TODO
        Integer videoViewNum = RandomUtils.nextInt(100, 200);//TODO
        Integer courseNum = RandomUtils.nextInt(100, 200);//TODO


        //3.封装数据,入库
        StatisticsDaily daily = new StatisticsDaily();
        daily.setRegisterNum(countRegister);
        daily.setLoginNum(loginNum);
        daily.setVideoViewNum(videoViewNum);
        daily.setCourseNum(courseNum);
        daily.setDateCalculated(day);

        baseMapper.insert(daily);
    }

    //查询统计数据
    @Override
    public Map<String, Object> getStaDaily(String type, String begin, String end) {
        //1.查询数据
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("date_calculated", begin, end).select("date_calculated", type);
        List<StatisticsDaily> dailyList = baseMapper.selectList(queryWrapper);

        //2.遍历查询结果,封装数据
        HashMap<String, Object> map = new HashMap<>();

        ArrayList<String> xData = new ArrayList<>();
        ArrayList<Integer> yData = new ArrayList<>();

        for (StatisticsDaily daily : dailyList) {

            //3.封装x轴数据
            xData.add(daily.getDateCalculated());


            //4.封装y轴数据
            switch (type) {
                case "login_num":
                    yData.add(daily.getLoginNum());
                    break;

                case "register_num":
                    yData.add(daily.getRegisterNum());
                    break;
                case "video_view_num":
                    yData.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    yData.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }

        map.put(" xData", xData);
        map.put("yData",yData);

        return map;
    }
}
