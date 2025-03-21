package com.teamwork.kejizhai.bean;

import lombok.Setter;

public class post {
    @Setter
    private String postId; // 帖子的id
    @Setter
    private String postTitle; // 帖子标题
    @Setter
    private String postContent; // 帖子内容
    @Setter
    private String postAuthor; // 帖子作者
    private int likeNum = 0; // 点赞数
    private int commentNum = 0; // 评论数

    public post(String postId, String postTitle, String postAuthor, int likeNum, int commentNum, String postCotent) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postAuthor = postAuthor;
        this.likeNum = likeNum;
        this.commentNum = commentNum;

    }

    public String getPostId() {
        return postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void 点赞数加一() {
        this.likeNum++;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void 评论数加一() {
        this.commentNum++;
    }

}
