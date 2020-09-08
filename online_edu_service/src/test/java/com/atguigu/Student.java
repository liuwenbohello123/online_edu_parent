package com.atguigu;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @ExcelProperty(value = "学生编号",index = 1)
    private Integer stuNo;
    @ExcelProperty(value = "学生姓名")
    private String stuName;
}
