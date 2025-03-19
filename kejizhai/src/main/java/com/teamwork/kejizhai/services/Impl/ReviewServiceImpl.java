package com.teamwork.kejizhai.services.impl;

import com.teamwork.kejizhai.bean.review;
import com.teamwork.kejizhai.dao.ReviewDao;
import com.teamwork.kejizhai.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public boolean addReview(review review) throws SQLException {
        return reviewDao.addReview(review);
    }

    @Override
    public List<review> getReviewsByItemId(int itemId) throws SQLException {
        return reviewDao.getReviewsByItemId(itemId);
    }

    @Override
    public List<review> getReviewsByUserId(String userId) throws SQLException {
        return reviewDao.getReviewsByUserId(userId);
    }

    @Override
    public review getReviewByOrderId(int orderId) throws SQLException {
        return reviewDao.getReviewByOrderId(orderId);
    }

    @Override
    public boolean updateReview(review review) throws SQLException {
        return reviewDao.updateReview(review);
    }

    @Override
    public boolean deleteReview(int reviewId) throws SQLException {
        return reviewDao.deleteReview(reviewId);
    }
}