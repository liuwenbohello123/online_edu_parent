package com.atguigu.edu.service.impl;

import com.atguigu.edu.entity.EduCourse;
import com.atguigu.edu.entity.EduCourseDescription;
import com.atguigu.edu.mapper.EduCourseMapper;
import com.atguigu.edu.service.EduChapterService;
import com.atguigu.edu.service.EduCourseDescriptionService;
import com.atguigu.edu.service.EduCourseService;
import com.atguigu.edu.service.EduSectionService;
import com.atguigu.exception.EduException;
import com.atguigu.request.CourseCondition;
import com.atguigu.request.CourseInfoVo;
import com.atguigu.response.EduCourseConfirmVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author liuwenbo
 * @since 2020-05-14
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
               @Autowired
                private EduCourseDescriptionService courseDescriptionService;
                 @Autowired
               private EduChapterService chapterService;
                 @Autowired
               private EduSectionService sectionService;



    @Override
    public String saveCourseInfo(CourseInfoVo courseInfo) {

//        保存课程的基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        String courseId = eduCourse.getId();
//        d.保存课程的描述信息 e.课程描述表和课程基本信息表共用一个主键
////        共用一个主键 type = IdType.INPUT
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfo.getDescription());
        eduCourseDescription.setId(eduCourse.getId());
        courseDescriptionService.save(eduCourseDescription);

        return courseId;
    }

    @Override
    public void queryCoursePageByCondition(Page<EduCourse> page, CourseCondition courseCondition) {
        String title = courseCondition.getTitle();
        String status = courseCondition.getStatus();
        QueryWrapper<EduCourse> eduCourseQueryWrapper = new QueryWrapper<>();
        
        if(StringUtils.isNotEmpty(title)){
            eduCourseQueryWrapper.like("title", title);
        }
        if(StringUtils.isNotEmpty(status)){
            eduCourseQueryWrapper.eq("status", status);
        }


         baseMapper.selectPage(page, eduCourseQueryWrapper);
    }

    @Override
    public CourseInfoVo getCourseInfoById(String id) {
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        EduCourse eduCourse = baseMapper.selectById(id);

        BeanUtils.copyProperties(eduCourse,courseInfoVo);

        EduCourseDescription description = courseDescriptionService.getById(id);
        if(description!=null){
            courseInfoVo.setDescription(description.getDescription());
        }
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //        保存课程的基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
            //更新课程的描述信息
        EduCourseDescription description = new EduCourseDescription();

        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(description);

    }

    @Override
    public EduCourseConfirmVo getCourseConfirmInfo(String courseId) {

        return baseMapper.getCourseConfirmInfo(courseId);

    }

    @Override
    public void deleteCourseInfo(String courseId) {
         //删除小节的信息
        sectionService.deleteCourseInfo(courseId);
        //删除章节的信息
        chapterService.deleteCourseInfo(courseId);
        //删除课程信息
        int rows = baseMapper.deleteById(courseId);
        if(rows==0){
            new EduException(20001, "删除课程失败");
        }
        //删除课程描述信息
        courseDescriptionService.removeById(courseId);



    }
}
