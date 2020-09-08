package com.atguigu.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.edu.entity.EduSubject;
import com.atguigu.edu.entity.ExcelSubject;
import com.atguigu.edu.listener.SubjectListener;
import com.atguigu.edu.mapper.EduSubjectMapper;
import com.atguigu.edu.service.EduSubjectService;
import com.atguigu.exception.EduException;
import com.atguigu.response.SubjectVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author liuwenbo
 * @since 2020-05-12
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
        @Autowired
        private SubjectListener subjectListener;
    @Override
    public void uploadSubject(MultipartFile file) throws Exception {
        //得到文件输入流
        InputStream inputStream = file.getInputStream();
        EasyExcel.read(inputStream, ExcelSubject.class, subjectListener).doReadAll();
    }

    @Override
    public EduSubject existSubject(String parentId, String title) {
       //判断标准是title和parentid
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        wrapper.eq("title", title);
        EduSubject eduSubject = baseMapper.selectOne(wrapper);
        return eduSubject;
    }

    @Override
    public List<SubjectVo> getAllSubject() {
      /*  //查询所有的学院
        List<EduSubject> allSubject = baseMapper.selectList(null);
        //创建一个集合将所有的组长放进集合中
        ArrayList<SubjectVo> parentSubjectVos = new ArrayList<>();
        //查询所有的一级分类(组长)
        for (EduSubject subject : allSubject) {
            //判断标准是parentid是否为0
            if(subject.getParentId().equals("0")){
                SubjectVo parentSubjectVo = new SubjectVo();
//                parentSubjectVo.setId(subject.getId());
//                parentSubjectVo.setTitle(subject.getTitle());
                BeanUtils.copyProperties(subject,parentSubjectVo);
                parentSubjectVos.add(parentSubjectVo);

            }


        }
        //将组长放进一个map集合中
        HashMap<String, SubjectVo> parentSubjectMap = new HashMap<>();
        for (SubjectVo parentSubjectVo : parentSubjectVos) {
            //key是一级分类id，value是一级分类本身
            parentSubjectMap.put(parentSubjectVo.getId(), parentSubjectVo);
        }
        //查询二级分类，并放进一级分类的children中
        for (EduSubject subject : allSubject) {
            //判断标准是parentid是否为0
            if(!subject.getParentId().equals("0")){
                //根据组员的父id获取组长
                SubjectVo parentVo = parentSubjectMap.get(subject.getParentId());
                //属性赋值
                SubjectVo childrenSubjectVo = new SubjectVo();
//                childrenSubjectVo.setId(subject.getId());
//                childrenSubjectVo.setTitle(subject.getTitle());
                BeanUtils.copyProperties(subject,childrenSubjectVo);
                parentVo.getChildren().add(childrenSubjectVo);


            }


        }
        return parentSubjectVos;*/


        List<EduSubject> allSubject = baseMapper.selectList(null);
        //创建一个集合将所有的组长放进集合中
        ArrayList<SubjectVo> parentSubjectVos = new ArrayList<>();
        //查询所有的一级分类(组长)
        for (EduSubject subject : allSubject) {
            //判断标准是parentid是否为0
            if (subject.getParentId().equals("0")) {
                SubjectVo parentSubjectVo = new SubjectVo();
//                parentSubjectVo.setId(subject.getId());
//                parentSubjectVo.setTitle(subject.getTitle());
                BeanUtils.copyProperties(subject, parentSubjectVo);
                parentSubjectVos.add(parentSubjectVo);

            }
        }
        //查询二级分类并放进一级分类的属性里
        for (EduSubject subject : allSubject) {
            if (!subject.getParentId().equals("0")) {
                SubjectVo childSubjectVo = new SubjectVo();
//                childSubjectVo.setId(subject.getId());
//                childSubjectVo.setTitle(subject.getTitle());
                BeanUtils.copyProperties(subject, childSubjectVo);
                for (SubjectVo subjectVo : parentSubjectVos) {
                    if (subjectVo.getId().equals(subject.getParentId())){
                        subjectVo.getChildren().add(childSubjectVo);
                    }
                }

            }
        }


      return  parentSubjectVos;
    }

    @Override
    public boolean deleteSubjectById(String id) {
       //删除节点的时候要看该节点是否有字节点，如果有子节点则不润许删除
        //构建一个查询条件，parent-id=id，看其是否有子节点
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        int count = baseMapper.selectCount(wrapper);
        if (count==0){
            int rows = baseMapper.deleteById(id);
            //影响行数rows>0，则返回true，否则返回false
            return rows>0;

        }else {
            throw new EduException(20001,"该节点存在子节点");
        }


    }

    @Override
    public boolean saveParentSubject(EduSubject eduSubject) {
       //根据title和parentid来确定一级分类是否存在

        EduSubject parentSubject = existSubject("0", eduSubject.getTitle());
        if(parentSubject==null){
            parentSubject= new EduSubject();
            parentSubject.setParentId("0");
            parentSubject.setTitle(eduSubject.getTitle());
            int rows = baseMapper.insert(parentSubject);
            return rows>0;
        }
        return false;
    }

    @Override
    public boolean saveChildSubject(EduSubject eduSubject) {

        EduSubject childSubject = existSubject(eduSubject.getParentId(), eduSubject.getTitle());
        if(childSubject==null){
            int rows = baseMapper.insert(eduSubject);
            return rows>0;
        }
        return false;
    }
}
