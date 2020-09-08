package com.atguigu.edu.controller;


import com.atguigu.edu.service.MemberCenterService;
import com.atguigu.response.RetVal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author liuwenbo
 * @since 2020-05-19
 */
@RestController
@CrossOrigin
@RequestMapping("/member/center")
public class MemberCenterController {
    @Autowired
    private MemberCenterService memberCenterService;
    //获取网站每日注册用户人数
    @GetMapping("queryRegisterNum/{day}")
    public RetVal queryRegisterNum(@PathVariable("day") String day){
       Integer registerNum= memberCenterService.queryRegisterNum(day);
        return RetVal.success().data("registerNum",registerNum);
    }

}

