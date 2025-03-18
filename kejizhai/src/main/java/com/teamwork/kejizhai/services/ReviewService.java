package com.teamwork.kejizhai.services;

import com.teamwork.kejizhai.bean.review;
import java.sql.SQLException;
import java.util.List;

public interface ReviewService {
    /**
     * 添加评论
     * @param review 评论信息
     * @return 是否添加成功
     */
    boolean addReview(review review) throws SQLException;
    
    /**
     * 根据商品ID获取评论列表
     * @param itemId 商品ID
     * @return 评论列表
     */
    List<review> getReviewsByItemId(int itemId) throws SQLException;
    
    /**
     * 根据用户ID获取评论列表
     * @param userId 用户ID
     * @return 评论列表
     */
    List<review> getReviewsByUserId(String userId) throws SQLException;
    
    /**
     * 根据订单ID获取评论
     * @param orderId 订单ID
     * @return 评论信息
     */
    review getReviewByOrderId(int orderId) throws SQLException;
    
    /**
     * 更新评论
     * @param review 更新的评论信息
     * @return 是否更新成功
     */
    boolean updateReview(review review) throws SQLException;
    
    /**
     * 删除评论
     * @param reviewId 评论ID
     * @return 是否删除成功
     */
    boolean deleteReview(int reviewId) throws SQLException;
}