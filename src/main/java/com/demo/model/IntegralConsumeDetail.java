package com.demo.model;

import java.time.LocalDate;

/**
 * 积分消费表
 *
 * @author hanzhuofan
 * @date 2020/7/3 21:
 */
public class IntegralConsumeDetail {
    private int consumeId;

    private int userId;

    private LocalDate date;

    private Double points;

    /**
     * 积分消费的订单id
     */
    private String orderId;

    public int getConsumeId() {
        return consumeId;
    }

    public void setConsumeId(int consumeId) {
        this.consumeId = consumeId;
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

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
