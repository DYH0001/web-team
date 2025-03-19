package com.teamwork.kejizhai.dao.Impl;

import com.teamwork.kejizhai.bean.Items;
import com.teamwork.kejizhai.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ItemDaoImpl implements ItemDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Items> getItems(String iid) throws SQLException {
        String sql = "SELECT * FROM items WHERE iid = ?";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Items.class), iid);
        } catch (Exception e) {
            throw new SQLException("数据库查询失败", e);
        }
    }

    @Override
    public boolean addItem(Items items) throws SQLException {
        String sql = "INSERT INTO items (iname, price, description, info, iimage, category, shop, uptime, istatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            int result = jdbcTemplate.update(sql,
                items.getIname(""),
                items.getPrice(),
                items.getDescription(),
                items.getInfo(),
                items.getIimage(),
                items.getCategory(),
                items.getShop(),
                items.getUptime(),
                items.getIstatus()
            );
            return result > 0;
        } catch (Exception e) {
            throw new SQLException("数据库插入失败", e);
        }
    }

    @Override
    public boolean updateItem(Items items) throws SQLException {
        String sql = "UPDATE items SET iname=?, price=?, description=?, info=?, iimage=?, category=?, shop=?, uptime=?, istatus=? WHERE iid=?";
        try {
            int result = jdbcTemplate.update(sql,
                items.getIname(""),
                items.getPrice(),
                items.getDescription(),
                items.getInfo(),
                items.getIimage(),
                items.getCategory(),
                items.getShop(),
                items.getUptime(),
                items.getIstatus(),
                items.getIid()
            );
            return result > 0;
        } catch (Exception e) {
            throw new SQLException("数据库更新失败", e);
        }
    }

    @Override
    public boolean deleteItem(String iid) throws SQLException {
        String sql = "DELETE FROM items WHERE iid = ?";
        try {
            int result = jdbcTemplate.update(sql, iid);
            return result > 0;
        } catch (Exception e) {
            throw new SQLException("数据库删除失败", e);
        }
    }

    @Override
    public List<Items> getItems() throws SQLException {
        String sql = "SELECT * FROM items";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Items.class));
        } catch (Exception e) {
            throw new SQLException("数据库查询失败", e);
        }
    }

    @Override
    public int setIstatus(String iid) throws SQLException {
        String sql = "UPDATE items SET istatus = 1 WHERE iid = ?";
        try {
            return jdbcTemplate.update(sql, iid);
        } catch (Exception e) {
            throw new SQLException("更新商品状态失败", e);
        }
    }
    @Override
    public Items getItemById(String iid) throws SQLException {
        String sql = "SELECT * FROM items WHERE iid = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Items.class), iid);
        } catch (Exception e) {
            throw new SQLException("数据库查询失败", e);
        }
    }
}