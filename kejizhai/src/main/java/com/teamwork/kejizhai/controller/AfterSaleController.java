package com.teamwork.kejizhai.controller;

import com.teamwork.kejizhai.bean.AfterSales;
import com.teamwork.kejizhai.bean.Items;
import com.teamwork.kejizhai.bean.Order;
import com.teamwork.kejizhai.bean.Users;
import com.teamwork.kejizhai.services.AfterSalesService;
import com.teamwork.kejizhai.services.ItemService;
import com.teamwork.kejizhai.services.OrderService;
import com.teamwork.kejizhai.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/aftersales")
public class AfterSaleController {

    @Autowired
    private AfterSalesService afterSalesService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private ItemService itemService;

    /**
     * 创建售后申请
     */
    @PostMapping
    public ResponseEntity<?> createAfterSales(@RequestBody Map<String, String> request) {
        try {
            String uid = request.get("uid");
            String oid = request.get("oid");
            String Iid = request.get("Iid");
            String reason = request.get("reason");
            String type = request.get("type");
            
            // 获取相关实体
            Users user = userService.getUserById(uid);
            
            // 从列表中获取单个订单
            List<Order> orders = orderService.getOrders(oid);
            if (orders == null || orders.isEmpty()) {
                return ResponseEntity.badRequest().body(errorResponse("订单不存在"));
            }
            Order order = orders.get(0);
            
            // 从列表中获取单个商品
            Items item = itemService.getItemById(Iid);
            if (item == null) {
                return ResponseEntity.badRequest().body(errorResponse("商品不存在"));
            }
            
            if (user == null) {
                return ResponseEntity.badRequest().body(errorResponse("用户信息不存在"));
            }
            
            afterSalesService.addAfterSales(user, order, item, reason, type);
            return ResponseEntity.ok(successResponse("售后申请创建成功"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse("创建售后申请失败: " + e.getMessage()));
        }
    }

    /**
     * 更新售后申请
     */
    @PutMapping("/{userId}/{orderId}/{itemId}")
    public ResponseEntity<?> updateAfterSales(
            @PathVariable String userId,
            @PathVariable String orderId,
            @PathVariable String itemId,
            @RequestBody Map<String, String> request) {
        try {
            String reason = request.get("reason");
            String type = request.get("type");
            
            // 获取相关实体
            Users user = userService.getUserById(userId);
            
            // 从列表中获取单个订单
            List<Order> orders = orderService.getOrders(orderId);
            if (orders == null || orders.isEmpty()) {
                return ResponseEntity.badRequest().body(errorResponse("订单不存在"));
            }
            Order order = orders.get(0);
            

            Items item = itemService.getItemById(itemId);
            if (item == null) {
                return ResponseEntity.badRequest().body(errorResponse("商品不存在"));
            }

            
            if (user == null) {
                return ResponseEntity.badRequest().body(errorResponse("用户信息不存在"));
            }
            
            afterSalesService.updateAfterSales(user, order, item, reason, type);
            return ResponseEntity.ok(successResponse("售后申请更新成功"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse("更新售后申请失败: " + e.getMessage()));
        }
    }

    /**
     * 完成售后申请
     */
    @PutMapping("/{userId}/{orderId}/{itemId}/finish")
    public ResponseEntity<?> finishAfterSales(
            @PathVariable String userId,
            @PathVariable String orderId,
            @PathVariable String itemId) {
        try {
            // 获取相关实体
            Users user = userService.getUserById(userId);
            Order order = orderService.getOrderById(orderId);
            Items item = itemService.getItemById(itemId);
            
            if (user == null || order == null || item == null) {
                return ResponseEntity.badRequest().body(errorResponse("用户、订单或商品信息不存在"));
            }
            
            afterSalesService.finishAfterSales(user, order, item);
            return ResponseEntity.ok(successResponse("售后申请已完成"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse("完成售后申请失败: " + e.getMessage()));
        }
    }

    /**
     * 删除售后申请
     */
    @DeleteMapping("/{userId}/{orderId}/{itemId}")
    public ResponseEntity<?> deleteAfterSales(
            @PathVariable String userId,
            @PathVariable String orderId,
            @PathVariable String itemId) {
        try {
            // 获取相关实体
            Users user = userService.getUserById(userId);
            Order order = orderService.getOrderById(orderId);
            Items item = itemService.getItemById(itemId);
            
            if (user == null || order == null || item == null) {
                return ResponseEntity.badRequest().body(errorResponse("用户、订单或商品信息不存在"));
            }
            
            afterSalesService.deleteAfterSales(user, order, item);
            return ResponseEntity.ok(successResponse("售后申请已删除"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse("删除售后申请失败: " + e.getMessage()));
        }
    }

    /**
     * 查询售后申请
     */
    @GetMapping("/{userId}/{orderId}/{itemId}")
    public ResponseEntity<?> getAfterSales(
            @PathVariable String userId,
            @PathVariable String orderId,
            @PathVariable String itemId) {
        try {
            // 获取相关实体
            Users user = userService.getUserById(userId);
            Order order = orderService.getOrderById(orderId);
            Items item = itemService.getItemById(itemId);
            
            if (user == null || order == null || item == null) {
                return ResponseEntity.badRequest().body(errorResponse("用户、订单或商品信息不存在"));
            }
            
            afterSalesService.queryAfterSales(user, order, item);
            return ResponseEntity.ok(successResponse("售后申请查询成功"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse("查询售后申请失败: " + e.getMessage()));
        }
    }

    /**
     * 获取用户所有售后申请
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserAfterSales(@PathVariable String userId) {
        try {
            // 获取用户
            Users user = userService.getUserById(userId);
            
            if (user == null) {
                return ResponseEntity.badRequest().body(errorResponse("用户不存在"));
            }
            
            // 这里需要在AfterSalesService中添加获取用户所有售后申请的方法
            // 暂时使用DAO层的方法直接获取
            List<AfterSales> afterSalesList = afterSalesService.getAfterSalesByUserId(userId);
            return ResponseEntity.ok(successResponse(afterSalesList));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse("获取用户售后申请列表失败: " + e.getMessage()));
        }
    }

    /**
     * 构建成功响应
     */
    private Map<String, Object> successResponse(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", data);
        return response;
    }

    /**
     * 构建错误响应
     */
    private Map<String, Object> errorResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", message);
        return response;
    }
}
