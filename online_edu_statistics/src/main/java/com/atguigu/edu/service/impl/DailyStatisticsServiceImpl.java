package com.atguigu.edu.service.impl;

import com.atguigu.edu.entity.DailyStatistics;
import com.atguigu.edu.mapper.DailyStatisticsMapper;
import com.atguigu.edu.service.DailyStatisticsService;
import com.atguigu.edu.service.UserServiceFeign;
import com.atguigu.response.RetVal;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * @author liuwenbo
 * @since 2020-05-19
 */
@Service
public class DailyStatisticsServiceImpl extends ServiceImpl<DailyStatisticsMapper, DailyStatistics> implements DailyStatisticsService {

    @Autowired
    private UserServiceFeign userServiceFeign;
    @Override
    public void generateData(String day) {
        //t通过远程rpc的形式调用远程拿取数据
        RetVal retVal = userServiceFeign.queryRegisterNum(day);
        Integer registerNum = (Integer)retVal.getData().get("registerNum");
        //模拟生成其他的数据

        DailyStatistics dailyStatistics = new DailyStatistics();
        dailyStatistics.setRegisterNum(registerNum);
        dailyStatistics.setCourseNum(RandomUtils.nextInt(400, 500));
        dailyStatistics.setVideoViewNum(RandomUtils.nextInt(00, 600));
        dailyStatistics.setLoginNum(RandomUtils.nextInt(600, 700));
        dailyStatistics.setDateCalculated(day);
        //将数据保存到i数据库中
        baseMapper.insert(dailyStatistics);

    }

    @Override
    public Map<String, Object> showStatistics(String dataType, String beginTime, String endTime) {

        QueryWrapper<DailyStatistics> wrapper = new QueryWrapper<>();
         wrapper.between("date_calculated", beginTime, endTime);

        List<DailyStatistics> dailyStatisticsList = baseMapper.selectList(wrapper);
        ArrayList<String> xData = new ArrayList<>();
        ArrayList<Integer> yData = new ArrayList<>();

        for (DailyStatistics dailyStatistics : dailyStatisticsList) {
            String dateCalculated = dailyStatistics.getDateCalculated();
            xData.add(dateCalculated);
            switch (dataType){
                case "register_num":
                    Integer registerNum = dailyStatistics.getRegisterNum();
                    yData.add(registerNum);
                    break;
                case "login_num":
                    Integer loginNum = dailyStatistics.getLoginNum();
                    yData.add(loginNum);
                    break;
                case "video_view_num":
                    Integer videoViewNum = dailyStatistics.getVideoViewNum();
                    yData.add(videoViewNum);
                    break;
                case "course_num":
                    Integer courseNum = dailyStatistics.getCourseNum();
                    yData.add(courseNum);
                    break;
                default:
                    break;
            }


        }
        HashMap<String, Object> retMap = new HashMap<>();
        retMap.put("xData", xData);
        retMap.put("yData", yData);

        return retMap;
    }
}
