package com.teamwork.kejizhai.services.Impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamwork.kejizhai.bean.AfterSales;
import com.teamwork.kejizhai.bean.Items;
import com.teamwork.kejizhai.bean.Order;
import com.teamwork.kejizhai.bean.Users;
import com.teamwork.kejizhai.dao.AfterSalesDao;
import com.teamwork.kejizhai.services.AfterSalesService;
@Service
@Transactional
public class AfterSalesServiceImpl implements AfterSalesService {
    
    @Autowired
    private AfterSalesDao afterSalesDao;

    @Override
    public void addAfterSales(Users user, Order order, Items item, String reason, String type) {
        try {
            AfterSales afterSales = new AfterSales();
            afterSales.setUserId(user.getUid());
            afterSales.setOrderId(order.getOid());
            afterSales.setItemId(item.getIid());
            afterSales.setReason(reason);
            afterSales.setType(type);
            afterSales.setStatus("待处理");
            afterSales.setCreateTime(new Date());
            
            afterSalesDao.addAfterSales(afterSales);
        } catch (SQLException e) {
            throw new RuntimeException("创建售后申请失败: " + e.getMessage());
        }
    }

    @Override
    public void updateAfterSales(Users user, Order order, Items item, String reason, String type) {
        try {
            AfterSales afterSales = afterSalesDao.getAfterSales(user.getUid(), order.getOid(), item.getIid());
            if (afterSales != null) {
                afterSales.setReason(reason);
                afterSales.setType(type);
                afterSales.setUpdateTime(new Date());
                afterSalesDao.updateAfterSales(afterSales);
            }
        } catch (SQLException e) {
            throw new RuntimeException("更新售后申请失败: " + e.getMessage());
        }
    }

    @Override
    public void finishAfterSales(Users user, Order order, Items item) {
        try {
            AfterSales afterSales = afterSalesDao.getAfterSales(user.getUid(), order.getOid(), item.getIid());
            if (afterSales != null) {
                afterSales.setStatus("已完成");
                afterSales.setFinishTime(new Date());
                afterSalesDao.updateAfterSales(afterSales);
            }
        } catch (SQLException e) {
            throw new RuntimeException("完成售后申请失败: " + e.getMessage());
        }
    }

    @Override
    public void deleteAfterSales(Users user, Order order, Items item) {
        try {
            afterSalesDao.deleteAfterSales(user.getUid(), order.getOid(), item.getIid());
        } catch (SQLException e) {
            throw new RuntimeException("删除售后申请失败: " + e.getMessage());
        }
    }

    @Override
    public void queryAfterSales(Users user, Order order, Items item) {
        try {
            AfterSales afterSales = afterSalesDao.getAfterSales(user.getUid(), order.getOid(), item.getIid());
            if (afterSales == null) {
                throw new RuntimeException("未找到相关售后申请");
            }
        } catch (SQLException e) {
            throw new RuntimeException("查询售后申请失败: " + e.getMessage());
        }
    }
    
    // 在实现类中添加以下方法
    @Override
    public List<AfterSales> getAfterSalesByUserId(String uid) {
        try {
            return afterSalesDao.getAfterSalesByUserId(uid);
        } catch (SQLException e) {
            throw new RuntimeException("获取用户售后申请列表失败: " + e.getMessage());
        }
    }
}