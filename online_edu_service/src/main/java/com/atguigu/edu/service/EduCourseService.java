package com.atguigu.edu.service;

import com.atguigu.edu.entity.EduCourse;
import com.atguigu.request.CourseCondition;
import com.atguigu.request.CourseInfoVo;
import com.atguigu.response.EduCourseConfirmVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author liuwenbo
 * @since 2020-05-14
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfo);

    void queryCoursePageByCondition(Page<EduCourse> page, CourseCondition courseCondition);

    CourseInfoVo getCourseInfoById(String id);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    EduCourseConfirmVo getCourseConfirmInfo(String courseId);

    void deleteCourseInfo(String courseId);
}
