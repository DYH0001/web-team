package com.teamwork.kejizhai.dao.Impl;

import com.teamwork.kejizhai.bean.review;
import com.teamwork.kejizhai.dao.ReviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReviewDaoImpl implements ReviewDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    // 评论RowMapper
    private RowMapper<review> reviewRowMapper = (ResultSet rs, int rowNum) -> {
        review r = new review();
        r.setSid(rs.getString("sid"));
        r.setIid(rs.getString("Iid"));
        r.setOid(rs.getString("oid"));
        r.setUid(rs.getString("uid"));
        r.setIreview(rs.getInt("ireview"));
        r.setIcomment(rs.getString("content"));
        r.setReviewDate(rs.getTimestamp("review_date"));
        return r;
    };

    @Override
    public boolean addReview(review review) throws SQLException {
        String sql = "INSERT INTO reviews (Iid, oid, uid, ireview, content, review_date) VALUES (?, ?, ?, ?, ?, NOW())";
        int rowsAffected = jdbcTemplate.update(
            sql, 
            review.getIid(), 
            review.getOid(), 
            review.getUid(), 
            review.getIreview(), 
            review.getIcomment()
        );
        return rowsAffected > 0;
    }

    @Override
    public List<review> getReviewsByItemId(String Iid) throws SQLException {
        String sql = "SELECT * FROM reviews WHERE Iid = ? ORDER BY review_date DESC";
        return jdbcTemplate.query(sql, reviewRowMapper, Iid);
    }

    @Override
    public List<review> getReviewsByUserId(String uid) throws SQLException {
        String sql = "SELECT * FROM reviews WHERE uid = ? ORDER BY review_date DESC";
        return jdbcTemplate.query(sql, reviewRowMapper, uid);
    }

    @Override
    public review getReviewByOrderId(String oid) throws SQLException {
        String sql = "SELECT * FROM reviews WHERE oid = ?";
        List<review> reviews = jdbcTemplate.query(sql, reviewRowMapper, oid);
        return reviews.isEmpty() ? null : reviews.get(0);
    }

    @Override
    public boolean updateReview(review review) throws SQLException {
        String sql = "UPDATE reviews SET ireview = ?, content = ? WHERE sid = ?";
        int rowsAffected = jdbcTemplate.update(
            sql, 
            review.getIreview(), 
            review.getIcomment(), 
            review.getSid()
        );
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteReview(String rid) throws SQLException {
        String sql = "DELETE FROM reviews WHERE sid = ?";
        int rowsAffected = jdbcTemplate.update(sql, rid);
        return rowsAffected > 0;
    }
}