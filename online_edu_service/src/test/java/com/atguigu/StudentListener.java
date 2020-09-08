package com.atguigu;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class StudentListener extends AnalysisEventListener<Student> {

    //一行一行的读取数据

    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        System.out.println("student = " + student+"1255");
    }



    //读取数据后进行的操作
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("你好");
    }
    //获取表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
    }
}
