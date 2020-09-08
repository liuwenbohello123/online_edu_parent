package com.atguigu.edu.controller;


import com.atguigu.edu.entity.EduSubject;
import com.atguigu.edu.service.EduSubjectService;
import com.atguigu.response.RetVal;
import com.atguigu.response.SubjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author liuwenbo
 * @since 2020-05-12
 */
@RestController
@RequestMapping("/edu/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
   private EduSubjectService eduSubjectService;
    //导入课程分类到数据库中
    @PostMapping("uploadSubject")
    public RetVal uploadSubject(MultipartFile file) throws Exception {
        eduSubjectService.uploadSubject(file);
        return RetVal.success();
    }
    //查询所有课程分类列别
    @GetMapping("getAllSubject")
    public RetVal getAllSubject(){
       List<SubjectVo> allSubject= eduSubjectService.getAllSubject();
        return RetVal.success().data("allSubject",allSubject);
    }

    //删除节点信息
    @DeleteMapping("{id}")
    public RetVal deleteSubjectById(@PathVariable String id){
       boolean flag= eduSubjectService.deleteSubjectById(id);

        if(flag==true){
            return RetVal.success();
        }else {
            return RetVal.error();
        }
    }
    //添加一级分类
    @PostMapping("saveParentSubject")
    public RetVal saveParentSubject(EduSubject eduSubject){
       boolean flag= eduSubjectService.saveParentSubject(eduSubject);

        if(flag==true){
            return RetVal.success();
        }else {
            return RetVal.error();
        }
    }

    //添加二级分类
    @PostMapping("saveChildSubject")
    public RetVal saveChildSubject(EduSubject eduSubject){
        boolean flag= eduSubjectService.saveChildSubject(eduSubject);

        if(flag==true){
            return RetVal.success();
        }else {
            return RetVal.error();
        }
    }
}

