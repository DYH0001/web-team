package com.teamwork.kejizhai.dao;

import java.sql.SQLException;
import java.util.List;

import com.teamwork.kejizhai.bean.AfterSales;

public interface AfterSalesDao {
    void addAfterSales(AfterSales afterSales) throws SQLException;
    void updateAfterSales(AfterSales afterSales) throws SQLException;
    void deleteAfterSales(String uid, String oid, String Iid) throws SQLException;
    AfterSales getAfterSales(String uid, String oid, String Iid) throws SQLException;
    List<AfterSales> getAfterSalesByUserId(String uid) throws SQLException;
}