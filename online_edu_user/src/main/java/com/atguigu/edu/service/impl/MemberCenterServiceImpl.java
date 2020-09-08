package com.atguigu.edu.service.impl;

import com.atguigu.edu.entity.MemberCenter;
import com.atguigu.edu.mapper.MemberCenterMapper;
import com.atguigu.edu.service.MemberCenterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author liuwenbo
 * @since 2020-05-19
 */
@Service
public class MemberCenterServiceImpl extends ServiceImpl<MemberCenterMapper, MemberCenter> implements MemberCenterService {

    @Override
    public Integer queryRegisterNum(String day) {
        Integer registerNum=  baseMapper.queryRegisterNum(day);
        return registerNum;
    }
}
