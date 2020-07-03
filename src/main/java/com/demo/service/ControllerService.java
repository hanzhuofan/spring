package com.demo.service;

import java.util.UUID;

import java.time.LocalDate;
import java.util.List;

import com.demo.dao.*;
import com.demo.model.*;

/**
 * @author hanzhuofan
 * @date 2020/7/2 22:
 */
public class ControllerService {
    private final IntegralDao integralDao = new IntegralDaoImpl();

    private final OrderDao orderDao = new OrderDaoImpl();

    private final ReviewDao reviewDao = new ReviewDaoImpl();

    private final SignInDao signInDao = new SignInDaoImpl();

    /**
     * 购物积分
     *
     * @param order 订单
     */
    public void shopping(Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        order.setDate(LocalDate.now());
        double money = order.getMoney();
        double oneIntegral;
        if (money < 500) {
            oneIntegral = money * 0.1;
        } else if (money >= 500 && money <= 1000) {
            oneIntegral = money * 0.15;
        } else {
            oneIntegral = money * 0.2;
        }
        orderDao.add(order);
        addIntegralDetail(order.getUserId(), order.getDate().plusMonths(3), oneIntegral, order.getOrderId());
    }

    /**
     * 一次评论
     *
     * @param review 评论信息
     */
    public void oneReview(Review review) {
        review.setReviewId(UUID.randomUUID().toString());
        review.setDate(LocalDate.now());
        reviewDao.add(review);
        addIntegralDetail(review.getUserId(), review.getDate().plusMonths(2), 5.0, review.getReviewId());
    }

    /**
     * 签到
     *
     * @param userId 用户id
     */
    public void signIn(int userId) {
        List<SignIn> signInList = signInDao.queryByUserId(userId);
        boolean isSignIn = signInList.get(signInList.size() - 1).getDate().equals(LocalDate.now());
        if (isSignIn) {
            return;
        }
        SignIn signIn = new SignIn();
        signIn.setUserId(userId);
        signIn.setDate(LocalDate.now());
        signIn.setSignInId(UUID.randomUUID().toString());
        signInDao.add(signIn);

        if (isContinuous(signInList)) {
            addIntegralDetail(userId, signIn.getDate().plusMonths(1), 20.0, signIn.getSignInId());
        } else {
            addIntegralDetail(userId, signIn.getDate().plusMonths(1), 10.0, signIn.getSignInId());
        }
    }

    //判断是否连续签到
    private boolean isContinuous(List<SignIn> signInList) {
        return signInList.size() == LocalDate.now().getDayOfMonth() - 1;
    }

    //增加积分
    private void addIntegralDetail(int userId, LocalDate date, Double points, String addId) {
        IntegralDetail integralDetail = new IntegralDetail();
        integralDetail.setDate(date);
        integralDetail.setPoints(points);
        integralDetail.setAddId(addId);
        integralDetail.setUserId(userId);
        integralDao.add(integralDetail);
    }

    /**
     * 积分消费
     *
     * @param order 订单中会携带消费的金额和消费多少积分
     */
    public void integralConsume(Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        int sum = queryIntegralSum(order.getUserId());
        double points = order.getPoints();
        if (sum < points) {
            return;
        }
        List<IntegralDetail> integralDetails = queryIntegralDetails(order.getUserId());
        double tmp = 0;
        for (IntegralDetail integralDetail : integralDetails) {
            Double integralDetailPoints = integralDetail.getPoints();
            if (points - tmp - integralDetailPoints > 0) {
                tmp += integralDetailPoints;
                integralDetail.setPoints(0.0);
                integralDao.update(integralDetail);
            } else {
                integralDetail.setPoints(integralDetailPoints + tmp - points);
                integralDao.update(integralDetail);
            }
        }

        IntegralConsumeDetail integralConsumeDetail = new IntegralConsumeDetail();
        integralConsumeDetail.setUserId(order.getUserId());
        integralConsumeDetail.setDate(LocalDate.now());
        integralConsumeDetail.setPoints(order.getPoints());
        integralConsumeDetail.setOrderId(order.getOrderId());
        integralDao.add(integralConsumeDetail);
        shopping(order);
    }

    /**
     * 根据userId查询积分总和
     *
     * @param userId 用户id
     * @return 积分总额
     */
    public int queryIntegralSum(int userId) {
        return integralDao.queryIntegralSumByUserIdAndNow(userId, LocalDate.now());
    }

    /**
     * 根据userId查询所有的积分详情
     *
     * @param userId 用户id
     * @return 积分有效期
     */
    public List<IntegralDetail> queryIntegralDetails(int userId) {
        return integralDao.queryIntegralDetailsByUserIdAndNow(userId, LocalDate.now());
    }

    /**
     * 根据userId查询所有的消费积分记录
     *
     * @param userId 用户id
     * @return 消费积分记录
     */
    public List<IntegralConsumeDetail> queryIntegralConsumeDetails(int userId) {
        return integralDao.queryIntegralConsumeDetailsByUserId(userId);
    }
}
