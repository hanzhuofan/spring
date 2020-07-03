package com.demo.model;

import java.time.LocalDate;

/**
 * 订单
 *
 * @author hanzhuofan
 * @date 2020/7/2 21:
 */
public class Order {
    private String orderId;

    private int userId;

    private LocalDate date;

    private double money;

    /**
     * 此订单使用了多少积分
     */
    private double points;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }
}
