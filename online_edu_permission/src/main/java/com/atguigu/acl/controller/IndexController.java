package com.atguigu.acl.controller;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.acl.entity.TPermission;
import com.atguigu.acl.helper.MenuHelper;
import com.atguigu.acl.helper.TPermissionHelper;
import com.atguigu.acl.service.TPermissionService;
import com.atguigu.response.RetVal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/acl/index")
@CrossOrigin
public class IndexController {
    @Autowired
    private TPermissionService permissionService;

    //登录的方法
    @PostMapping("login")
    public RetVal login(){
        return RetVal.success().data("token","admin");
    }
    //获取信息的方法
    @GetMapping("info")
    public RetVal info(){
        Map<String, Object> userInfo=new HashMap<>();
        userInfo.put("name", "admin");
        userInfo.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        userInfo.put("roles", "[超级管理员]");
        //有哪些操作权限
        String[] permissionNameArray={"chapter.edit", "course.publish", "course.save", "course.update", "daily.list", "permission.add", "permission.list", "permission.remove", "permission.update",
                "role.acl", "role.add", "role.list", "role.remove", "role.update", "statistics.generate", "subject.list", "subject.upload", "teacher.delete", "teacher.list", "teacher.save", "teacher.update", "user.add", "user.assgin", "user.list", "user.remove", "user.update"};
        List<String> permissionNameList = Arrays.asList(permissionNameArray);
        userInfo.put("permissionNameList", permissionNameList);
        return RetVal.success().data(userInfo);
    }

    /**
     * 获取菜单
     * @return
     */
    @GetMapping("router")
    public RetVal getDynamicRouter(){
        //获取当前登录用户用户名
        String userName = "admin";
        List<TPermission> permissionList = permissionService.list(null);
        List<TPermission> formatPermission = TPermissionHelper.bulid(permissionList);
        List<JSONObject> jsonList = MenuHelper.bulid(formatPermission);
        return RetVal.success().data("permissionList", jsonList);
    }

    @PostMapping("logout")
    public RetVal logout(){
        return RetVal.success();
    }

}