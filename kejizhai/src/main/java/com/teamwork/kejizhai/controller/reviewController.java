package com.teamwork.kejizhai.controller;

import com.teamwork.kejizhai.bean.review;
import com.teamwork.kejizhai.services.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("/api/reviews")
public class reviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * 添加商品评论
     * @param review 评论信息
     * @return 添加结果
     */
    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody review review) {
        try {
            boolean success = reviewService.addReview(review);
            if (success) {
                return ResponseEntity.ok(successResponse("评论添加成功"));
            } else {
                return ResponseEntity.badRequest().body(errorResponse("评论添加失败"));
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body(errorResponse("添加评论失败: " + e.getMessage()));
        }
    }

    /**
     * 获取商品的所有评论
     * @param itemId 商品ID
     * @return 评论列表
     */
    @GetMapping("/item/{itemId}")
    public ResponseEntity<?> getItemReviews(@PathVariable int itemId) {
        try {
            List<review> reviews = reviewService.getReviewsByItemId(itemId);
            return ResponseEntity.ok(successResponse(reviews));
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body(errorResponse("获取商品评论失败: " + e.getMessage()));
        }
    }

    /**
     * 获取用户的所有评论
     * @param userId 用户ID
     * @return 评论列表
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserReviews(@PathVariable String userId) {
        try {
            List<review> reviews = reviewService.getReviewsByUserId(userId);
            return ResponseEntity.ok(successResponse(reviews));
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body(errorResponse("获取用户评论失败: " + e.getMessage()));
        }
    }

    /**
     * 获取订单的评论
     * @param orderId 订单ID
     * @return 评论信息
     */
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderReview(@PathVariable int orderId) {
        try {
            review review = reviewService.getReviewByOrderId(orderId);
            if (review != null) {
                return ResponseEntity.ok(successResponse(review));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body(errorResponse("获取订单评论失败: " + e.getMessage()));
        }
    }

    /**
     * 更新评论
     * @param reviewId 评论ID
     * @param updatedReview 更新的评论信息
     * @return 更新结果
     */
    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable int reviewId, @RequestBody review updatedReview) {
        try {
            updatedReview.setSid(reviewId);
            boolean success = reviewService.updateReview(updatedReview);
            if (success) {
                return ResponseEntity.ok(successResponse("评论更新成功"));
            } else {
                return ResponseEntity.badRequest().body(errorResponse("评论更新失败"));
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body(errorResponse("更新评论失败: " + e.getMessage()));
        }
    }

    /**
     * 删除评论
     * @param reviewId 评论ID
     * @return 删除结果
     */
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable int reviewId) {
        try {
            boolean success = reviewService.deleteReview(reviewId);
            if (success) {
                return ResponseEntity.ok(successResponse("评论删除成功"));
            } else {
                return ResponseEntity.badRequest().body(errorResponse("评论删除失败"));
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body(errorResponse("删除评论失败: " + e.getMessage()));
        }
    }

    /**
     * 构建成功响应
     * @param data 响应数据
     * @return 响应对象
     */
    private Map<String, Object> successResponse(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", data);
        return response;
    }

    /**
     * 构建错误响应
     * @param message 错误信息
     * @return 响应对象
     */
    private Map<String, Object> errorResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", message);
        return response;
    }
}
