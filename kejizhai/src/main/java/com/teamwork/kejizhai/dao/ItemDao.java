package com.teamwork.kejizhai.dao;

import com.teamwork.kejizhai.bean.Items;
import java.sql.SQLException;
import java.util.List;

public interface ItemDao {
    List<Items> getItems(String iid) throws SQLException;
    boolean addItem(Items items) throws SQLException;
    boolean updateItem(Items items) throws SQLException;
    boolean deleteItem(String iid) throws SQLException;
    List<Items> getItems() throws SQLException;
    int setIstatus(String iid) throws SQLException;
    Items getItemById(String iid) throws SQLException;
}