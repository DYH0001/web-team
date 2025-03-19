package com.teamwork.kejizhai.dao.Impl;

import com.teamwork.kejizhai.bean.post;
import com.teamwork.kejizhai.bean.postcomments;
import com.teamwork.kejizhai.dao.ForumDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Date;

@Repository
public class ForumDaoImpl implements ForumDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public List<post> getPosts() throws SQLException {
        String sql = "SELECT * FROM post";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(post.class));
        } catch (Exception e) {
            throw new SQLException("数据库查询失败", e);
        }
    }

    @Override
    @Transactional
    public List<post> getPost(String pid) throws SQLException {
        String sql = "SELECT * FROM post WHERE pid = ?";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(post.class), pid);
        } catch (Exception e) {
            throw new SQLException("数据库查询失败", e);
        }
    }

    @Override
    @Transactional
    public List<postcomments> getPostComments(String pid) throws SQLException {
        String sql = "SELECT * FROM postcomments WHERE pid = ?";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(postcomments.class), pid);
        } catch (Exception e) {
            throw new SQLException("数据库查询失败", e);
        }
    }

    @Override
    @Transactional
    public boolean addPost(post post) throws SQLException {
        String sql = "INSERT INTO post (uid, title, content, ptime) VALUES (?, ?, ?, ?)";
        try {
            int result = jdbcTemplate.update(sql,
                    post.getPostAuthor(),
                    post.getPostTitle(),
                    post.getPostContent(),
                    new Date());
            return result > 0;
        } catch (Exception e) {
            throw new SQLException("数据库插入失败", e);
        }
    }

    @Override
    @Transactional
    public boolean addPostComment(postcomments postcomments) throws SQLException {
        String sql = "INSERT INTO postcomments (pid, uid, content, ctime) VALUES (?, ?, ?, ?)";
        try {
            int result = jdbcTemplate.update(sql,
                    postcomments.getCommentId(),
                    postcomments.getCommentAuthor(),
                    postcomments.getCommentContent(),
                    new Date());
            return result > 0;
        } catch (Exception e) {
            throw new SQLException("数据库插入失败", e);
        }
    }

    @Override
    @Transactional
    public boolean likePost(String pid) throws SQLException {
        String sql = "UPDATE post SET likeNum = likeNum + 1 WHERE pid = ?";
        try {
            int result = jdbcTemplate.update(sql, pid);
            return result > 0;
        } catch (Exception e) {
            throw new SQLException("点赞失败", e);
        }
    }

    @Override
    @Transactional
    public boolean dislikePost(String pid) throws SQLException {
        String sql = "UPDATE post SET likeNum = likeNum - 1 WHERE pid = ?";
        try {
            int result = jdbcTemplate.update(sql, pid);
            return result > 0;
        } catch (Exception e) {
            throw new SQLException("取消点赞失败", e);
        }
    }

    @Override
    @Transactional
    public boolean likeComment(String cid) throws SQLException {
        String sql = "UPDATE postcomments SET likeNum = likeNum + 1 WHERE cid = ?";
        try {
            int result = jdbcTemplate.update(sql, cid);
            return result > 0;
        } catch (Exception e) {
            throw new SQLException("点赞评论失败", e);
        }
    }

    @Override
    @Transactional
    public boolean dislikeComment(String cid) throws SQLException {
        String sql = "UPDATE postcomments SET likeNum = likeNum - 1 WHERE cid = ?";
        try {
            int result = jdbcTemplate.update(sql, cid);
            return result > 0;
        } catch (Exception e) {
            throw new SQLException("取消点赞评论失败", e);
        }
    }
}