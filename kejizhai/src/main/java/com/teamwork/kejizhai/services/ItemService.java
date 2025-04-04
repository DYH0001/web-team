package com.teamwork.kejizhai.services;

import com.teamwork.kejizhai.bean.*;
import java.util.List;
import java.sql.SQLException;  // 添加这个导入

public interface ItemService {

    public boolean addItem(Items items) throws SQLException;
    public boolean updateItem(Items items) throws SQLException;
    public boolean deleteItem(String Iid) throws SQLException;
    public Items getItemByName(String Iname) throws SQLException;
    public int setIstatus(String Iid) throws SQLException;
    public Items getItemById(String Iid) throws SQLException;
    public List<Items> getAllItems() throws SQLException;
}