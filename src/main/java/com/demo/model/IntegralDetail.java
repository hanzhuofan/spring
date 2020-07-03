package com.demo.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * 积分表
 *
 * @author hanzhuofan
 * @date 2020/7/2 22:
 */
public class IntegralDetail {
    private int integralId;

    private int userId;

    /**
     * 积分有效期
     */
    private LocalDate date;

    private Double points;

    /**
     * 积分增加的方式的id
     */
    private String addId;

    public int getIntegralId() {
        return integralId;
    }

    public void setIntegralId(int integralId) {
        this.integralId = integralId;
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

    public String getAddId() {
        return addId;
    }

    public void setAddId(String addId) {
        this.addId = addId;
    }
}
