package com.teamwork.kejizhai.services.Impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamwork.kejizhai.bean.review;
import com.teamwork.kejizhai.dao.ReviewDao;
import com.teamwork.kejizhai.services.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public boolean addReview(review review) throws SQLException {
        return reviewDao.addReview(review);
    }

    @Override
    public List<review> getReviewsByItemId(String iid) throws SQLException {
        return reviewDao.getReviewsByItemId(iid);
    }

    @Override
    public List<review> getReviewsByUserId(String uid) throws SQLException {
        return reviewDao.getReviewsByUserId(uid);
    }

    @Override
    public review getReviewByOrderId(String oid) throws SQLException {
        return reviewDao.getReviewByOrderId(oid);
    }

    @Override
    public boolean updateReview(review review) throws SQLException {
        return reviewDao.updateReview(review);
    }

    @Override
    public boolean deleteReview(String rid) throws SQLException {
        return reviewDao.deleteReview(rid);
    }
}