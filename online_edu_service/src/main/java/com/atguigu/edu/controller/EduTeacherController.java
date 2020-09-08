package com.atguigu.edu.controller;


import com.atguigu.edu.entity.EduTeacher;
import com.atguigu.edu.service.EduTeacherService;
import com.atguigu.request.TeacherCondition;
import com.atguigu.response.RetVal;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author liuwenbo
 * @since 2020-05-08
 */
@RestController
@RequestMapping("/edu/teacher")
@CrossOrigin
public class EduTeacherController {
    @Autowired
    EduTeacherService teacherService;

   /* //查询所有的讲师
    @GetMapping
    public List<EduTeacher> queryAllTeachers(){
        List<EduTeacher> list = teacherService.list(null);
        for (EduTeacher eduTeacher : list) {
            System.out.println("eduTeacher = " + eduTeacher);
        }
        return list;
    }
    *//**
    * 删除讲师信息
    *//*
    @DeleteMapping("{id}")
    public boolean deleteTeacherById(@PathVariable("id") String id) {
        boolean flag = teacherService.removeById(id);
        return flag;
    }
*/
    //查询所有的讲师
    @GetMapping
    public RetVal queryAllTeachers() {

        List<EduTeacher> list = teacherService.list(null);
        RetVal teacherList = RetVal.success().data("teacherList", list);
        return teacherList;
    }
    /**
     * 删除讲师信息
     */
    @DeleteMapping("{id}")
    public RetVal deleteTeacherById(@PathVariable("id") String id) {
        boolean flag = teacherService.removeById(id);
        if(flag==true){
            return RetVal.success();
        }else {
            return RetVal.error();
        }

    }

    //分页查询讲师信息
    @GetMapping("queryTeacherPage/{pagNum}/{pagSize}")
    public RetVal queryTeacherPage(@PathVariable("pagNum") long pagNum,
                                   @PathVariable("pagSize") long pagSize){
        Page<EduTeacher> page = new Page<>(pagNum,pagSize);

        IPage<EduTeacher> teacherPage = teacherService.page(page, null);
        List<EduTeacher> teacherList = teacherPage.getRecords();
        System.out.println("records = " + teacherList);
        long total = teacherPage.getTotal();
        System.out.println("total = " + total);
        return RetVal.success().data("teacherList", teacherList).data("total", total);
    }

    //分页查询讲师信息待条件
    @GetMapping("queryTeacherPageByCondition/{pagNum}/{pagSize}")
    public RetVal queryTeacherPageByCondition(@PathVariable("pagNum") long pagNum,
                                             @PathVariable("pagSize") long pagSize,
                                              TeacherCondition teacherCondition
                                               ){
        Page<EduTeacher> page = new Page<>(pagNum,pagSize);

       teacherService.queryTeacherPageByCondition(page,teacherCondition);
        List<EduTeacher> teacherList = page.getRecords();
        //System.out.println("records = " + teacherList);
        long total = page.getTotal();
        //System.out.println("total = " + total);
        return RetVal.success().data("teacherList", teacherList).data("total", total);
    }

    /**
     * 添加讲师
     */
    @PostMapping
    public RetVal saveTeacher(EduTeacher eduTeacher){
        boolean flag = teacherService.save(eduTeacher);
        if(flag==true){
            return RetVal.success();
        }else {
            return RetVal.error();
        }
    }
    /**
     * 根据id查询讲师信息
     */
    @GetMapping("{id}")
    public RetVal queryTeacherById(@PathVariable("id") String id ){
        EduTeacher teacher = teacherService.getById(id);
        return RetVal.success().data("teacher", teacher);

    }
    /**
     * 更新讲师
     */
    @PutMapping
     public RetVal updateTeacher(EduTeacher teacher){
        boolean flag = teacherService.updateById(teacher);
        if(flag==true){
            return RetVal.success();
        }else {
            return RetVal.error();
        }
    }


}

