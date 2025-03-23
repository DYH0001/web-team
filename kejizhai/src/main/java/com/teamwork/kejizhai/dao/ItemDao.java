package com.teamwork.kejizhai.dao;

import com.teamwork.kejizhai.bean.Items;
import java.sql.SQLException;
import java.util.List;

public interface ItemDao {
    boolean addItem(Items items) throws SQLException;
    boolean updateItem(Items items) throws SQLException;
    boolean deleteItem(String Iid) throws SQLException;
    Items getItemsByName(String Iname) throws SQLException;
    int setIstatus(String Iid) throws SQLException;
    Items getItemById(String Iid) throws SQLException;
    List<Items> getAllItems() throws SQLException;
}