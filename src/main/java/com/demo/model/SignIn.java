package com.demo.model;

import java.time.LocalDate;

/**
 * @author hanzhuofan
 * @date 2020/7/2 22:
 */
public class SignIn {
    private String signInId;

    private int userId;

    private LocalDate date;

    public String getSignInId() {
        return signInId;
    }

    public void setSignInId(String signInId) {
        this.signInId = signInId;
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
}
