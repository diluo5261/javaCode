package com.example.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//@Component
public interface UserDao extends BaseMapper<User> {
}
