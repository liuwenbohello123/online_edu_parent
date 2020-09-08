package com.atguigu.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
    public class ChapterVo {
        @ApiModelProperty(value = "章节ID")
        private String id;
        @ApiModelProperty(value = "章节名称")
        private String title;
        //小节对象集合
        private List<SectionVo> children=new ArrayList<>();
    }
