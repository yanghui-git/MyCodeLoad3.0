package com.yh.mybatisplus.demouser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yh.mybatisplus.demouser.entity.DemoUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DemoUserMapper extends BaseMapper<DemoUser> {
}
