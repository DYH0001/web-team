package com.teamwork.kejizhai.dao;

import com.teamwork.kejizhai.bean.Users;
import com.teamwork.kejizhai.bean.post;
import com.teamwork.kejizhai.bean.postcomments;

import java.sql.SQLException;
import java.util.List;

public interface ForumDao {
    List<post> getPosts() throws SQLException;

    List<post> getPost(String pid) throws SQLException;

    List<postcomments> getPostComments(String pid) throws SQLException;

    boolean addPost(post post) throws SQLException;

    boolean addPostComment(postcomments postcomments) throws SQLException;

    boolean likePost(String pid) throws SQLException;

    boolean dislikePost(String pid) throws SQLException;

    boolean likeComment(String cid) throws SQLException;

    boolean dislikeComment(String cid) throws SQLException;
}
