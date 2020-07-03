package com.demo.model;

import java.time.LocalDate;

/**
 * 评论
 *
 * @author hanzhuofan
 * @date 2020/7/2 22:
 */
public class Review {
    private String reviewId;

    private int userId;

    private LocalDate date;

    private String review;

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
