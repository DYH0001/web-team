package com.teamwork.kejizhai.services.Impl;

import com.teamwork.kejizhai.bean.*;
import com.teamwork.kejizhai.services.ItemService;
import com.teamwork.kejizhai.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLException; 
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    
    @Autowired
    private ItemDao itemDao;

    @Override
    public Items getItemByName(String iid) throws SQLException {
        try {
            return itemDao.getItemsByName(iid);
        } catch (SQLException e) {
            throw new SQLException("获取商品信息失败: " + e.getMessage());
        }
    }
    @Override
    public boolean addItem(Items items) throws SQLException {
        try {
            return itemDao.addItem(items);
        } catch (SQLException e) {
            throw new SQLException("添加商品失败: " + e.getMessage());
        }
    }

    @Override
    public boolean updateItem(Items items) throws SQLException {
        try {
            return itemDao.updateItem(items);
        } catch (SQLException e) {
            throw new SQLException("更新商品信息失败: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteItem(String iid) throws SQLException {
        try {
            return itemDao.deleteItem(iid);
        } catch (SQLException e) {
            throw new SQLException("下架商品失败: " + e.getMessage());
        }
    }

    @Override
    public int setIstatus(String iid) {
        try {
            return itemDao.setIstatus(iid);
        } catch (Exception e) {
            return 0; // 设置状态失败返回0
        }
    }
    @Override
    public Items getItemById(String iid) throws SQLException {
        try {
            return itemDao.getItemById(iid);
        } catch (SQLException e) {
            throw new SQLException("获取商品信息失败: " + e.getMessage());
        }
    }
    @Override
    public List<Items> getAllItems() throws SQLException {
        try {
            return itemDao.getAllItems();
        } catch (SQLException e) {
            throw new SQLException("获取所有商品信息失败: " + e.getMessage());
        }
    }
}