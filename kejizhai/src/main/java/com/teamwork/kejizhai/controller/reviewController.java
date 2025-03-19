package com.teamwork.kejizhai.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamwork.kejizhai.bean.review;
import com.teamwork.kejizhai.services.ReviewService;

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
    public ResponseEntity<?> getItemReviews(@PathVariable String iid) {
        try {
            List<review> reviews = reviewService.getReviewsByItemId(iid);
            return ResponseEntity.ok(successResponse(reviews));
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body(errorResponse("获取商品评论失败: " + e.getMessage()));
        }
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserReviews(@PathVariable String userId) {
        try {
            List<review> reviews = reviewService.getReviewsByUserId(userId);
            return ResponseEntity.ok(successResponse(reviews));
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body(errorResponse("获取用户评论失败: " + e.getMessage()));
        }
    }


    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderReview(@PathVariable String oid) {
        try {
            review review = reviewService.getReviewByOrderId(oid);
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
    public ResponseEntity<?> updateReview(@PathVariable String sid, @RequestBody review updatedReview) {
        try {
            updatedReview.setSid(sid);
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


    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable String rid) {
        try {
            boolean success = reviewService.deleteReview(rid);
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
