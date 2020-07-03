package com.demo.dao;

import com.demo.model.SignIn;

import java.util.List;

/**
 * @author hanzhuofan
 * @date 2020/7/2 23:
 */
public interface SignInDao {
    /**
     * 根据userId获取当月的签到记录
     *
     * @param userId 用户id
     * @return 签到list
     */
    List<SignIn> queryByUserId(int userId);

    void add(SignIn signIn);
}
