package com.teamwork.kejizhai.services;

import com.teamwork.kejizhai.bean.Items;
import com.teamwork.kejizhai.bean.Order;
import com.teamwork.kejizhai.bean.review;
import java.sql.SQLException;
import java.util.List;

public interface ReviewService {
 
    boolean addReview(review review) throws SQLException;
    
  
    List<review> getReviewsByItemId(String iid) throws SQLException;

    List<review> getReviewsByUserId(String uid) throws SQLException;

    review getReviewByOrderId(int oid) throws SQLException;
    
 
    boolean updateReview(review review) throws SQLException;
      boolean deleteReview(int reviewId) throws SQLException;
}