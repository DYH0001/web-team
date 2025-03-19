package com.teamwork.kejizhai.controller;

import com.teamwork.kejizhai.bean.post;
import com.teamwork.kejizhai.bean.postcomments;
import com.teamwork.kejizhai.services.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * ForumController 是论坛的控制层，负责处理与帖子和评论相关的 HTTP 请求。
 */
@RestController
@RequestMapping("/forum")
public class ForumController {

    @Autowired
    private ForumService forumService;

    /**
     * 获取所有帖子
     * @return 帖子列表
     * @throws SQLException 数据库异常
     */
    @GetMapping("/posts")
    public List<post> getAllPosts() throws SQLException {
        return forumService.getPosts();
    }

    /**
     * 根据帖子 ID 获取单个帖子
     * @param pid 帖子 ID
     * @return 帖子对象
     * @throws SQLException 数据库异常
     */
    @GetMapping("/post/{pid}")
    public post getPostById(@PathVariable String pid) throws SQLException {
        return forumService.getPost(pid);
    }

    /**
     * 根据帖子 ID 获取该帖子的所有评论
     * @param pid 帖子 ID
     * @return 评论列表
     * @throws SQLException 数据库异常
     */
    @GetMapping("/post/{pid}/comments")
    public List<postcomments> getCommentsByPostId(@PathVariable String pid) throws SQLException {
        return forumService.getPostComments(pid);
    }

    /**
     * 添加新帖子
     * @param post 帖子对象
     * @return 操作是否成功
     * @throws SQLException 数据库异常
     */
    @PostMapping("/post")
    public boolean addPost(@RequestBody post post) throws SQLException {
        return forumService.addPost(post);
    }

    /**
     * 添加新评论
     * @param postcomments 评论对象
     * @return 操作是否成功
     * @throws SQLException 数据库异常
     */
    @PostMapping("/post/comment")
    public boolean addComment(@RequestBody postcomments postcomments) throws SQLException {
        return forumService.addPostComment(postcomments);
    }

    /**
     * 点赞帖子
     * @param pid 帖子 ID
     * @return 操作是否成功
     * @throws SQLException 数据库异常
     */
    @PostMapping("/post/{pid}/like")
    public boolean likePost(@PathVariable String pid) throws SQLException {
        return forumService.likePost(pid);
    }

    /**
     * 取消点赞帖子
     * @param pid 帖子 ID
     * @return 操作是否成功
     * @throws SQLException 数据库异常
     */
    @PostMapping("/post/{pid}/dislike")
    public boolean dislikePost(@PathVariable String pid) throws SQLException {
        return forumService.dislikePost(pid);
    }

    /**
     * 点赞评论
     * @param cid 评论 ID
     * @return 操作是否成功
     * @throws SQLException 数据库异常
     */
    @PostMapping("/comment/{cid}/like")
    public boolean likeComment(@PathVariable String cid) throws SQLException {
        return forumService.likeComment(cid);
    }

    /**
     * 取消点赞评论
     * @param cid 评论 ID
     * @return 操作是否成功
     * @throws SQLException 数据库异常
     */
    @PostMapping("/comment/{cid}/dislike")
    public boolean dislikeComment(@PathVariable String cid) throws SQLException {
        return forumService.dislikeComment(cid);
    }
}