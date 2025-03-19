package com.teamwork.kejizhai.controller;

import com.teamwork.kejizhai.bean.Items;
import com.teamwork.kejizhai.bean.Order;
import com.teamwork.kejizhai.bean.Users;
import com.teamwork.kejizhai.bean.review;
import com.teamwork.kejizhai.dao.AnalyticsDao;
import com.teamwork.kejizhai.services.AnalyticsService;
import com.teamwork.kejizhai.services.OrderService;
import com.teamwork.kejizhai.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;
    
    @Autowired
    private AnalyticsDao analyticsDao;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private OrderService orderService;

    /**
     * 获取用户购买行为分析
     * @param userId 用户ID
     * @return 用户购买行为分析数据
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserAnalytics(@PathVariable String userId) {
        try {
            Users user = userService.getUserById(userId);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            
            // 获取用户评价数据
            List<review> reviews = analyticsDao.getUserReviews(userId);
            
            // 获取用户购买的商品
            List<Items> purchasedItems = analyticsDao.getUserPurchasedItems(userId);
            
            // 获取订单服务列表
            List<OrderService> orderServices = Collections.singletonList(orderService);
            
            // 计算各项指标
            double avgRating = analyticsService.calculate_UserRating(reviews, user);
            double avgPrice = analyticsService.calculate_UserPrice(purchasedItems, user);
            double purchaseQuantity = analyticsService.calculate_UserQuantity(orderServices, user);
            double repeatRate = analyticsService.calculate_UserRepeat_Rate(orderServices, user);
            double totalRevenue = analyticsService.calculate_UserRevenue(orderServices, user);
            double profitRate = analyticsService.calculate_UserProfit(orderServices, user);
            
            // 构建响应数据
            Map<String, Object> result = new HashMap<>();
            result.put("userId", userId);
            result.put("username", user.getUname());
            result.put("averageRating", avgRating);
            result.put("averagePrice", avgPrice);
            result.put("purchaseQuantity", purchaseQuantity);
            result.put("repeatPurchaseRate", repeatRate);
            result.put("totalRevenue", totalRevenue);
            result.put("profitRate", profitRate);
            
            return ResponseEntity.ok(result);
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body(errorResponse("获取用户分析数据失败: " + e.getMessage()));
        }
    }
    

    @GetMapping("/user/{userId}/orders")
    public ResponseEntity<?> getUserOrderAnalytics(
            @PathVariable String userId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        try {
            Users user = userService.getUserById(userId);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            
            List<Order> orders = analyticsDao.getOrdersByDateRange(userId, startDate, endDate);
            double totalRevenue = analyticsDao.getTotalRevenue(userId);
            double averageOrderValue = analyticsDao.getAverageOrderValue(userId);
            
            Map<String, Object> result = new HashMap<>();
            result.put("userId", userId);
            result.put("orderCount", orders.size());
            result.put("totalRevenue", totalRevenue);
            result.put("averageOrderValue", averageOrderValue);
            result.put("startDate", startDate);
            result.put("endDate", endDate);
            
            return ResponseEntity.ok(result);
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body(errorResponse("获取用户订单分析失败: " + e.getMessage()));
        }
    }
    

    @GetMapping("/user/{userId}/items")
    public ResponseEntity<?> getUserItemsAnalytics(@PathVariable String userId) {
        try {
            Users user = userService.getUserById(userId);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            
            List<Items> items = analyticsDao.getUserPurchasedItems(userId);
            
            // 计算商品类别分布
            Map<String, Integer> categoryDistribution = new HashMap<>();
            for (Items item : items) {
                String category = item.getCategory();
                categoryDistribution.put(category, categoryDistribution.getOrDefault(category, 0) + 1);
            }
            
            // 计算价格区间分布
            Map<String, Integer> priceRangeDistribution = new HashMap<>();
            for (Items item : items) {
                double price = item.getPrice();
                String priceRange;
                
                if (price < 50) {
                    priceRange = "0-50";
                } else if (price < 100) {
                    priceRange = "50-100";
                } else if (price < 200) {
                    priceRange = "100-200";
                } else if (price < 500) {
                    priceRange = "200-500";
                } else {
                    priceRange = "500+";
                }
                
                priceRangeDistribution.put(priceRange, priceRangeDistribution.getOrDefault(priceRange, 0) + 1);
            }
            
            Map<String, Object> result = new HashMap<>();
            result.put("userId", userId);
            result.put("totalItems", items.size());
            result.put("categoryDistribution", categoryDistribution);
            result.put("priceRangeDistribution", priceRangeDistribution);
            
            return ResponseEntity.ok(result);
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body(errorResponse("获取用户商品分析失败: " + e.getMessage()));
        }
    }
    

    private Map<String, Object> errorResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", message);
        return response;
    }
}
