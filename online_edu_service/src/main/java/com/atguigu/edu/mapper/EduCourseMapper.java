package com.atguigu.edu.mapper;

import com.atguigu.edu.entity.EduCourse;
import com.atguigu.response.EduCourseConfirmVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author liuwenbo
 * @since 2020-05-14
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    EduCourseConfirmVo getCourseConfirmInfo(String courseId);
}
