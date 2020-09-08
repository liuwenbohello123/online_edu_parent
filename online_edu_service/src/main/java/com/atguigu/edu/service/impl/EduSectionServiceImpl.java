package com.atguigu.edu.service.impl;

import com.atguigu.edu.entity.EduSection;
import com.atguigu.edu.mapper.EduSectionMapper;
import com.atguigu.edu.service.EduSectionService;
import com.atguigu.edu.service.VideoServiceFeign;
import com.atguigu.exception.EduException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程小节 服务实现类
 * </p>
 *
 * @author liuwenbo
 * @since 2020-05-17
 */
@Service
public class EduSectionServiceImpl extends ServiceImpl<EduSectionMapper, EduSection> implements EduSectionService {
    @Autowired
    private VideoServiceFeign videoServiceFeign;
    @Override
    public void addSection(EduSection section) {
        //1.判断是否存在小节
        QueryWrapper<EduSection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",section.getCourseId());
        queryWrapper.eq("chapter_id",section.getChapterId());
        queryWrapper.eq("title",section.getTitle());
        EduSection existSection = baseMapper.selectOne(queryWrapper);
        if(existSection==null){
            baseMapper.insert(section);
        }else{
            throw new EduException(20001,"存在重复的小节");
        }
    }

    @Override
    public void deleteSection(String id) {
        //通过小节的id查询小节的信息
        EduSection section = baseMapper.selectById(id);
        //在小节的信息中获取到videoId,因为rpc远程调用video微服务的时候需要用
        String videoId = section.getVideoSourceId();
        //通过调用远程的微服务来删除视频
        videoServiceFeign.deleteSingleVideo(videoId);
        //删除小节其他的信息
        baseMapper.deleteById(id);
    }

    @Override
    public void deleteCourseInfo(String courseId) {
        //通过课程的id获取到该课程下的所有的小节信息
        QueryWrapper<EduSection> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        List<EduSection> sectionList = baseMapper.selectList(wrapper);

        //通过小节的信息获取的video微服务的id集合，传递、
        ArrayList<String> videoIdList = new ArrayList<>();
        for (EduSection section : sectionList) {
            String videoSourceId = section.getVideoSourceId();
            videoIdList.add(videoSourceId);
        }

        // 调用远程的微服务进行删除视频
       videoServiceFeign.deleteMultiVideo(videoIdList);
        //删除其他的小节信息
        baseMapper.delete(wrapper);
    }
}
