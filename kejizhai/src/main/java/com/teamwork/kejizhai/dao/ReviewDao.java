package com.teamwork.kejizhai.dao;

import com.teamwork.kejizhai.bean.review;
import java.sql.SQLException;
import java.util.List;

public interface ReviewDao {
    boolean addReview(review review) throws SQLException;
    List<review> getReviewsByItemId(int itemId) throws SQLException;
    List<review> getReviewsByUserId(String userId) throws SQLException;
    review getReviewByOrderId(int orderId) throws SQLException;
    boolean updateReview(review review) throws SQLException;
    boolean deleteReview(int reviewId) throws SQLException;
}