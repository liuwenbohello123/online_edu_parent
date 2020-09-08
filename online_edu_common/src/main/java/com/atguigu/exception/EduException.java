package com.atguigu.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("这是一个自定义的异常")
public class EduException extends RuntimeException {
    @ApiModelProperty("异常的状态码")
    private Integer code;
    @ApiModelProperty("异常的消息")
    private String message;

}
