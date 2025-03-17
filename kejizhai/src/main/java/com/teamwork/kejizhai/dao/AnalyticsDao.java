package com.teamwork.kejizhai.dao;

import com.teamwork.kejizhai.bean.Items;
import com.teamwork.kejizhai.bean.Users;
import com.teamwork.kejizhai.bean.review;
import com.teamwork.kejizhai.bean.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Date;

public interface AnalyticsDao {
    List<review> getUserReviews(String userId) throws SQLException;
    List<Items> getUserPurchasedItems(String userId) throws SQLException;
    List<Order> getUserOrders(String userId) throws SQLException;
    double getItemCost(String itemId) throws SQLException;
    List<Order> getOrdersByDateRange(String userId, Date startDate, Date endDate) throws SQLException;
    int getRepeatPurchaseCount(String userId, String itemId) throws SQLException;
    double getTotalRevenue(String userId) throws SQLException;
    double getAverageOrderValue(String userId) throws SQLException;
}