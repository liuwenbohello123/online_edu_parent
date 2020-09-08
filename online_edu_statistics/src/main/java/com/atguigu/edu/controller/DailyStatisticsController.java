package com.atguigu.edu.controller;


import com.atguigu.edu.service.DailyStatisticsService;
import com.atguigu.response.RetVal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author liuwenbo
 * @since 2020-05-19
 */
@RestController
@CrossOrigin
@RequestMapping("/daily/statistics")
public class DailyStatisticsController {
    @Autowired
    private DailyStatisticsService dailyStatisticsService;
    //前端传递日期，后端调用微服务生成数据并保存在数据库
   @GetMapping("generateData/{day}")
    public RetVal generateData(@PathVariable("day") String day){
       dailyStatisticsService.generateData(day);
        return RetVal.success();
    }

    //显示数据信息，需要参数 数据类型，开始事件和结束事件
    @GetMapping("showStatistics/{dataType}/{beginTime}/{endTime}")
    public RetVal showStatistics(@PathVariable("dataType") String dataType,
                                 @PathVariable("beginTime") String beginTime,
                                 @PathVariable("endTime") String endTime){

       Map<String, Object> retMap= dailyStatisticsService.showStatistics(dataType,beginTime,endTime);
        return RetVal.success().data(retMap);
    }
}

