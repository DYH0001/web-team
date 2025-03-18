package com.teamwork.kejizhai.control;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamwork.kejizhai.bean.Order;
import com.teamwork.kejizhai.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 获取用户订单列表
     * @param uid 用户ID
     * @return 订单列表
     */
    @GetMapping("/user/{uid}")
    public ResponseEntity<?> getUserOrders(@PathVariable String uid) {
        try {
            List<Order> orders = orderService.getOrders(uid);
            return ResponseEntity.ok(orders);
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("获取订单列表失败: " + e.getMessage());
        }
    }

    /**
     * 创建新订单
     * @param order 订单信息
     * @return 创建结果
     */
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        try {
            boolean success = orderService.addOrder(order);
            if (success) {
                return ResponseEntity.ok("订单创建成功");
            } else {
                return ResponseEntity.badRequest().body("订单创建失败");
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("创建订单失败: " + e.getMessage());
        }
    }

    /**
     * 修改订单状态
     * @param order 更新的订单信息
     * @return 更新结果
     */
    @PutMapping("/{oid}")
    public ResponseEntity<?> updateOrder(@RequestBody Order order) {
        try {
            boolean success = orderService.changeOrder(order);
            if (success) {
                return ResponseEntity.ok("订单更新成功");
            } else {
                return ResponseEntity.badRequest().body("订单更新失败");
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("更新订单失败: " + e.getMessage());
        }
    }

    /**
     * 删除订单
     * @param oid 订单ID
     * @return 删除结果
     */
    @DeleteMapping("/{oid}")
    public ResponseEntity<?> deleteOrder(@PathVariable String oid) {
        try {
            boolean success = orderService.deleteOrder(oid);
            if (success) {
                return ResponseEntity.ok("订单删除成功");
            } else {
                return ResponseEntity.badRequest().body("订单删除失败");
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("删除订单失败: " + e.getMessage());
        }
    }

    /**
     * 搜索订单
     * @param keyword 搜索关键词
     * @return 搜索结果
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchOrders(@RequestParam String keyword) {
        try {
            List<Order> orders = orderService.searchOrders(keyword);
            return ResponseEntity.ok(orders);
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("搜索订单失败: " + e.getMessage());
        }
    }
}
