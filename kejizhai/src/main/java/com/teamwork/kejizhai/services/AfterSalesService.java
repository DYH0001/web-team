package com.teamwork.kejizhai.services;

import com.teamwork.kejizhai.bean.Items;
import com.teamwork.kejizhai.bean.Order;
import com.teamwork.kejizhai.bean.Users;

public interface AfterSalesService {
    void addAfterSales(Users user, Order order, Items item, String reason, String type);
    void updateAfterSales(Users user, Order order, Items item, String reason, String type);
    void finishAfterSales(Users user, Order order, Items item);
    void deleteAfterSales(Users user, Order order, Items item);
    void queryAfterSales(Users user, Order order, Items item);
}