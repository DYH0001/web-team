package com.teamwork.kejizhai.bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class postcomments {
    private int commentId;// 评论id
    private BufferedReader commentContent;// 评论内容
    private String commentAuthor;// 评论作者
    private int likeNum = 0;// 点赞数
    private int commentNum = 0;// 评论数

    public postcomments(int commentId, BufferedReader commentContent, String commentAuthor, int likeNum,
            int commentNum) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.commentAuthor = commentAuthor;
        this.likeNum = likeNum;
        this.commentNum = commentNum;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public BufferedReader getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(BufferedReader commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void 点赞数加一(int likeNum) {
        this.likeNum++;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void 评论数加一(int commentNum) {
        this.commentNum++;
    }

}
