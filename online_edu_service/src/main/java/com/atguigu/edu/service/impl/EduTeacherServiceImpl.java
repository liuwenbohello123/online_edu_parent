package com.atguigu.edu.service.impl;

import com.atguigu.edu.entity.EduTeacher;
import com.atguigu.edu.mapper.EduTeacherMapper;
import com.atguigu.edu.service.EduTeacherService;
import com.atguigu.request.TeacherCondition;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author liuwenbo
 * @since 2020-05-08
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void queryTeacherPageByCondition(Page<EduTeacher> page, TeacherCondition teacherCondition) {
        String name = teacherCondition.getName();
        Integer level = teacherCondition.getLevel();
        String beginTime = teacherCondition.getBeginTime();
        String endTime = teacherCondition.getEndTime();

        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
       //判断传递过来的信息是否为空
        if(StringUtils.isNotEmpty(name)){
            wrapper.like("name", name);
        }
        if(level!=null){
            wrapper.eq("level", level);
        }
        if(StringUtils.isNotEmpty(beginTime)){
            wrapper.ge("gmt_create", beginTime);
        }
        if(StringUtils.isNotEmpty(endTime)){
            wrapper.le("gmt_create", endTime);
        }
        baseMapper.selectPage(page, wrapper);


    }
}
