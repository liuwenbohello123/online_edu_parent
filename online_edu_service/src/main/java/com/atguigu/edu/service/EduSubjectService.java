package com.atguigu.edu.service;

import com.atguigu.edu.entity.EduSubject;
import com.atguigu.response.SubjectVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author liuwenbo
 * @since 2020-05-12
 */
public interface EduSubjectService extends IService<EduSubject> {

    void uploadSubject(MultipartFile file) throws IOException, Exception;
    //判断该分类是否在数据库中存在
    EduSubject existSubject(String parentId,String title);

    List<SubjectVo> getAllSubject();

    boolean deleteSubjectById(String id);

    boolean saveParentSubject(EduSubject eduSubject);

    boolean saveChildSubject(EduSubject eduSubject);
}
