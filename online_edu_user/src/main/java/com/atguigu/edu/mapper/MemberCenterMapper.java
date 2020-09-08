package com.atguigu.edu.mapper;

import com.atguigu.edu.entity.MemberCenter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author liuwenbo
 * @since 2020-05-19
 */
public interface MemberCenterMapper extends BaseMapper<MemberCenter> {

    Integer queryRegisterNum(String day);
}
