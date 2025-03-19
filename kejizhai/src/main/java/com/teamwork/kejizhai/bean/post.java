package com.teamwork.kejizhai.bean;

public class post {
    private String postId; // 帖子的id
    private String postTitle; // 帖子标题
    private String postContent; // 帖子内容
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

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(String postAuthor) {
        this.postAuthor = postAuthor;
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
