package com.teamwork.kejizhai.dao;

import java.sql.SQLException;
import java.util.List;

import com.teamwork.kejizhai.bean.review;

public interface ReviewDao {
    boolean addReview(review review) throws SQLException;
    List<review> getReviewsByItemId(String iid) throws SQLException;
    List<review> getReviewsByUserId(String uid) throws SQLException;
    review getReviewByOrderId(String oid) throws SQLException;
    boolean updateReview(review review) throws SQLException;
    boolean deleteReview(String rid) throws SQLException;
}