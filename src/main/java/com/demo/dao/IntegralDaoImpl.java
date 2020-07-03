package com.demo.dao;

import com.demo.model.IntegralConsumeDetail;
import com.demo.model.IntegralDetail;

import java.time.LocalDate;
import java.util.List;

/**
 * @author hanzhuofan
 * @date 2020/7/2 22:
 */
public class IntegralDaoImpl implements IntegralDao {
    @Override
    public IntegralDetail queryByUserId(int userId) {
        return null;
    }

    @Override
    public void add(IntegralDetail integralDetail) {

    }

    @Override
    public int queryIntegralSumByUserIdAndNow(int userId, LocalDate now) {
        return 0;
    }

    @Override
    public List<IntegralDetail> queryIntegralDetailsByUserIdAndNow(int userId, LocalDate now) {
        return null;
    }

    @Override
    public List<IntegralConsumeDetail> queryIntegralConsumeDetailsByUserId(int userId) {
        return null;
    }

    @Override
    public void add(IntegralConsumeDetail integralConsumeDetail) {

    }

    @Override
    public void update(IntegralDetail integralDetail) {

    }
}
