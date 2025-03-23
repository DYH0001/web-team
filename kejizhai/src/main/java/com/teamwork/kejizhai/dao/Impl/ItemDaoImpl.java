package com.teamwork.kejizhai.dao.Impl;

import com.teamwork.kejizhai.bean.Items;
import com.teamwork.kejizhai.dao.ItemDao;
import com.teamwork.kejizhai.config.CustomBeanPropertyRowMapper;
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
    public Items getItemsByName(String Iname) throws SQLException {
        String sql = "SELECT * FROM items WHERE Iname = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Items.class), Iname);
        } catch (Exception e) {
            throw new SQLException("数据库查询失败", e);
        }
    }

    @Override
    public boolean addItem(Items items) throws SQLException {
        String sql = "INSERT INTO items (Iname, price, description, info, iimage, category, shop, uptime, istatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
        String sql = "UPDATE items SET Iname=?, price=?, description=?, info=?, iimage=?, category=?, shop=?, uptime=?, istatus=? WHERE Iid=?";
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
    public Items getItemById(String Iid) throws SQLException {
        // 修改SQL语句中的字段名为大写I开头
        String sql = "SELECT * FROM items WHERE Iid = ?";  // 改为大写Iid
        try {
            // 使用自定义的RowMapper解决大小写问题
            return jdbcTemplate.queryForObject(sql, CustomBeanPropertyRowMapper.newInstance(Items.class), Iid);
        } catch (Exception e) {
            throw new SQLException("数据库查询失败", e);
        }
    }

    @Override
    public boolean deleteItem(String Iid) throws SQLException {
        // 修改SQL语句中的字段名为大写I开头
        String sql = "DELETE FROM items WHERE Iid = ?";  // 改为大写Iid
        try {
            int result = jdbcTemplate.update(sql, Iid);
            return result > 0;
        } catch (Exception e) {
            throw new SQLException("数据库删除失败", e);
        }
    }

    @Override
    public int setIstatus(String Iid) throws SQLException {
        // 修改SQL语句中的字段名为大写I开头
        String sql = "UPDATE items SET Istatus = 1 WHERE Iid = ?";  // 改为大写Iid和Istatus
        try {
            return jdbcTemplate.update(sql, Iid);
        } catch (Exception e) {
            throw new SQLException("更新商品状态失败", e);
        }
    }
    public List<Items> getAllItems() throws SQLException {
        String sql = "SELECT * FROM items";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Items.class));
        } catch (Exception e) {
            throw new SQLException("数据库查询失败", e);
        }
    }
}