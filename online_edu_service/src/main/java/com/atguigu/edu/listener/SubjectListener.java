package com.atguigu.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.edu.entity.EduSubject;
import com.atguigu.edu.entity.ExcelSubject;
import com.atguigu.edu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubjectListener extends AnalysisEventListener<ExcelSubject> {
    @Autowired
        private EduSubjectService eduSubjectService;
    @Override
    public void invoke(ExcelSubject excelSubject, AnalysisContext analysisContext) {
       //读取到的数据分为两列，第一列是parent第二列是child
        String parentSubjectName = excelSubject.getParentSubjectName();
        String childSubjectName = excelSubject.getChildSubjectName();

        //保存一级或者二级的时候都需要判断数据库是否已经存在
        EduSubject parentSubject = eduSubjectService.existSubject("0", parentSubjectName);
        if(parentSubject==null){
            parentSubject=  new EduSubject();
            parentSubject.setParentId("0");
            parentSubject.setTitle(parentSubjectName);
            eduSubjectService.save(parentSubject);
        }
        //二级分类的福ID是一级分类的id,二级的时候都需要判断数据库是否已经存在
       String parentId= parentSubject.getId();
        EduSubject childSubject = eduSubjectService.existSubject(parentId, childSubjectName);
        if(childSubject==null){
            childSubject=  new EduSubject();
            childSubject.setParentId(parentId);
            childSubject.setTitle(childSubjectName);
            eduSubjectService.save(childSubject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
