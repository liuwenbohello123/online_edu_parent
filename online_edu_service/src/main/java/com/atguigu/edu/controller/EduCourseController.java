package com.atguigu.edu.controller;


import com.atguigu.edu.entity.EduCourse;
import com.atguigu.edu.service.EduCourseService;
import com.atguigu.request.CourseCondition;
import com.atguigu.request.CourseInfoVo;
import com.atguigu.response.EduCourseConfirmVo;
import com.atguigu.response.RetVal;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author liuwenbo
 * @since 2020-05-14
 */
@RestController
@RequestMapping("/edu/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
   private EduCourseService courseService;

   //1.添加课程，保存课程信息
    @PostMapping("saveCourseInfo")
    public RetVal saveCourseInfo(CourseInfoVo courseInfo){
      String courseId = courseService.saveCourseInfo(courseInfo);
        return RetVal.success().data("courseId",courseId);
    }

    //2.分页查询课程信息待条件
    @GetMapping("queryCoursePageByCondition/{pagNum}/{pagSize}")
    public RetVal queryCoursePageByCondition(@PathVariable("pagNum") long pagNum,
                                              @PathVariable("pagSize") long pagSize,
                                              CourseCondition courseCondition
    ){
        Page<EduCourse> page = new Page<>(pagNum,pagSize);

        courseService.queryCoursePageByCondition(page,courseCondition);
        List<EduCourse> courseList = page.getRecords();
        //System.out.println("records = " + teacherList);
        long total = page.getTotal();
        //System.out.println("total = " + total);
        return RetVal.success().data("courseList", courseList).data("total", total);
    }
    //3.根据课程id查询课程的信息，用于回显
    @GetMapping("{id}")
    public RetVal getCourseInfoById(@PathVariable String id){
        //涉及到数据库里的两张表
      CourseInfoVo courseInfoVo=  courseService.getCourseInfoById(id);
        return RetVal.success().data("courseInfoVo", courseInfoVo);
    }
    //根据课程id更新课程信息
    @PostMapping("updateCourseInfo")
    public RetVal updateCourseInfo(CourseInfoVo courseInfoVo){
        courseService.updateCourseInfo(courseInfoVo);
        return  RetVal.success();
    }

    //课程确认信息的返回
    @GetMapping("getCourseConfirmInfo/{courseId}")
    public RetVal getCourseConfirmInfo(@PathVariable String courseId){
       EduCourseConfirmVo eduCourseConfirmVo= courseService.getCourseConfirmInfo(courseId);
        return RetVal.success().data("courseConfirm",eduCourseConfirmVo);
    }
    //6.发布课程
    @GetMapping("publishCourse/{courseId}")
    public RetVal publishCourse(@PathVariable String courseId){
        EduCourse course = new EduCourse();
        course.setId(courseId);
        course.setStatus("Normal");
        courseService.updateById(course);
        return RetVal.success();
    }
    //7,删除课程信息
    @DeleteMapping("deleteCourseInfo/{courseId}")
    public RetVal deleteCourseInfo(@PathVariable String courseId){
       courseService.deleteCourseInfo(courseId);
        return RetVal.success();


    }


}

