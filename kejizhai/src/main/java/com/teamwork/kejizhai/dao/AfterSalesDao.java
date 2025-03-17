package com.teamwork.kejizhai.dao;

import com.teamwork.kejizhai.bean.AfterSales;
import java.sql.SQLException;
import java.util.List;

public interface AfterSalesDao {
    void addAfterSales(AfterSales afterSales) throws SQLException;
    void updateAfterSales(AfterSales afterSales) throws SQLException;
    void deleteAfterSales(String userId, String orderId, String itemId) throws SQLException;
    AfterSales getAfterSales(String userId, String orderId, String itemId) throws SQLException;
    List<AfterSales> getAfterSalesByUserId(String userId) throws SQLException;
}