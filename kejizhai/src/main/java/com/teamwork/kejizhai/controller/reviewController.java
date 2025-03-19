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


    @GetMapping("/item/{itemId}")
    public ResponseEntity<?> getItemReviews(@PathVariable int itemId) {
        try {
            List<review> reviews = ReviewService.getReviewsByItemId(itemId);
            return ResponseEntity.ok(successResponse(reviews));
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body(errorResponse("获取商品评论失败: " + e.getMessage()));
        }
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserReviews(@PathVariable String userId) {
        try {
            List<review> reviews = ReviewService.getReviewsByUserId(userId);
            return ResponseEntity.ok(successResponse(reviews));
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body(errorResponse("获取用户评论失败: " + e.getMessage()));
        }
    }


    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderReview(@PathVariable int orderId) {
        try {
            review review = ReviewService.getReviewByOrderId(orderId);
            if (review != null) {
                return ResponseEntity.ok(successResponse(review));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body(errorResponse("获取订单评论失败: " + e.getMessage()));
        }
    }


    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable int reviewId, @RequestBody review updatedReview) {
        try {
            updatedReview.setSid(reviewId);
            boolean success = ReviewService.updateReview(updatedReview);
            if (success) {
                return ResponseEntity.ok(successResponse("评论更新成功"));
            } else {
                return ResponseEntity.badRequest().body(errorResponse("评论更新失败"));
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body(errorResponse("更新评论失败: " + e.getMessage()));
        }
    }


    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable String reviewId) {
        try {
            boolean success = ReviewService.deleteReview(reviewId);
            if (success) {
                return ResponseEntity.ok(successResponse("评论删除成功"));
            } else {
                return ResponseEntity.badRequest().body(errorResponse("评论删除失败"));
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body(errorResponse("删除评论失败: " + e.getMessage()));
        }
    }


    private Map<String, Object> successResponse(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", data);
        return response;
    }


    private Map<String, Object> errorResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", message);
        return response;
    }
}
