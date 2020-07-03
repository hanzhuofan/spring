package com.demo.dao;

import com.demo.model.IntegralConsumeDetail;
import com.demo.model.IntegralDetail;

import java.time.LocalDate;
import java.util.List;

/**
 * @author hanzhuofan
 * @date 2020/7/2 22:
 */
public interface IntegralDao {
    IntegralDetail queryByUserId(int userId);

    void add(IntegralDetail integralDetail);

    int queryIntegralSumByUserIdAndNow(int userId, LocalDate now);

    List<IntegralDetail> queryIntegralDetailsByUserIdAndNow(int userId, LocalDate now);

    List<IntegralConsumeDetail> queryIntegralConsumeDetailsByUserId(int userId);

    void add(IntegralConsumeDetail integralConsumeDetail);

    void update(IntegralDetail integralDetail);
}
