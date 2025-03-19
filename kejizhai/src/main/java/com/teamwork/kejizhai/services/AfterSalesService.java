package com.teamwork.kejizhai.services;

import java.util.List;

import com.teamwork.kejizhai.bean.AfterSales;
import com.teamwork.kejizhai.bean.Items;
import com.teamwork.kejizhai.bean.Order;
import com.teamwork.kejizhai.bean.Users;

public interface AfterSalesService {
    void addAfterSales(Users user, Order order, Items item, String reason, String type);
    void updateAfterSales(Users user, Order order, Items item, String reason, String type);
    void finishAfterSales(Users user, Order order, Items item);
    void deleteAfterSales(Users user, Order order, Items item);
    void queryAfterSales(Users user, Order order, Items item);
    
    // 在接口中添加以下方法
    List<AfterSales> getAfterSalesByUserId(String userId);
}