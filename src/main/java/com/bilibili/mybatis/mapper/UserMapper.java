package com.bilibili.mybatis.mapper;

import com.bilibili.mybatis.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hanzhuofan
 * @date 2020/5/23 16:
 */
public interface UserMapper {
    User selectUserById(Integer id, String name);
}
