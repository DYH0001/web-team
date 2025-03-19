package com.teamwork.kejizhai.services.Impl;

import com.teamwork.kejizhai.bean.post;
import com.teamwork.kejizhai.bean.postcomments;
import com.teamwork.kejizhai.dao.ForumDao;
import com.teamwork.kejizhai.services.ForumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class ForumServiceImpl implements ForumService {

    @Autowired
    private ForumDao forumDao;

    @Override
    @Transactional
    public List<post> getPosts() throws SQLException {
        return forumDao.getPosts();
    }

    @Override
    @Transactional
    public post getPost(String pid) throws SQLException {
        List<post> posts = forumDao.getPost(pid);
        if (posts != null && !posts.isEmpty()) {
            return posts.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public List<postcomments> getPostComments(String pid) throws SQLException {
        return forumDao.getPostComments(pid);
    }

    @Override
    @Transactional
    public boolean addPost(post post) throws SQLException {
        return forumDao.addPost(post);
    }

    @Override
    @Transactional
    public boolean addPostComment(postcomments postcomments) throws SQLException {
        return forumDao.addPostComment(postcomments);
    }

    @Override
    @Transactional
    public boolean likePost(String pid) throws SQLException {
        return forumDao.likePost(pid);
    }

    @Override
    @Transactional
    public boolean dislikePost(String pid) throws SQLException {
        return forumDao.dislikePost(pid);
    }

    @Override
    @Transactional
    public boolean likeComment(String cid) throws SQLException {
        return forumDao.likeComment(cid);
    }

    @Override
    @Transactional
    public boolean dislikeComment(String cid) throws SQLException {
        return forumDao.dislikeComment(cid);
    }
}
