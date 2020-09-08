package com.atguigu;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EasyExcelTest {

    /**
    *往excel里写数据
    */
    @Test
    public void testWriteExcel(){
        //往哪个文件里写数据
        String fileName = "E:\\one.xls";
        //写什么数据
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            Student student = Student.builder().stuNo(i).stuName("老王" + i).build();
            studentList.add(student);

        }
        //执行写数据
        EasyExcel.write(fileName,Student.class).sheet("sheet1").doWrite(studentList);

    }
    
    /**
    * 往excel里度数据
    */
    @Test
    public void testReadExcel(){
        //从哪里读
        String fileName = "E:\\one.xls";
        //执行读的操作
        //对数据进行解析
        EasyExcel.read(fileName, Student.class, new StudentListener()).doReadAll();


    }
}
