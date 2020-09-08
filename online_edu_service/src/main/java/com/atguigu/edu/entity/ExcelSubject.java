package com.atguigu.edu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelSubject {
    private String parentSubjectName;
    private String childSubjectName;
}
