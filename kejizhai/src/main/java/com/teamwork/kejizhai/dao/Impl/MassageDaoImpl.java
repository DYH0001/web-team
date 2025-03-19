package com.teamwork.kejizhai.dao.Impl;

import com.teamwork.kejizhai.bean.Massage;
import com.teamwork.kejizhai.dao.MassageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class MassageDaoImpl implements MassageDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 消息内容转换为字符串存储
    private String contentListToString(List<String> contentList) {
        if (contentList == null || contentList.isEmpty()) {
            return "";
        }
        return String.join("|||", contentList);
    }

    // 字符串转换为消息内容列表
    private List<String> stringToContentList(String content) {
        if (content == null || content.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(content.split("\\|\\|\\|"));
    }

    // 消息对象的行映射器
    private RowMapper<Massage> massageRowMapper = (ResultSet rs, int rowNum) -> {
        Massage massage = new Massage();
        massage.setSenderId(rs.getString("sender_id"));
        massage.setMassageId(rs.getString("massage_id"));
        massage.setContent(stringToContentList(rs.getString("content")));
        massage.setStartTime(rs.getTimestamp("start_time"));
        massage.setEndTime(rs.getTimestamp("end_time"));
        String receiverId = rs.getString("receiver_id");
        massage.setReceiverId(receiverId);
        massage.setMassageType(rs.getInt("massage_type"));
        return massage;
    };

    @Override
    public void sendMassage(Massage massage) throws SQLException {
        String sql = "INSERT INTO massage (sender_id, massage_id, content, start_time, end_time, receiver_id, massage_type) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        jdbcTemplate.update(sql,
            massage.getSenderId(),
            massage.getMassageId(),
            contentListToString(massage.getContent()),
            massage.getStartTime(),
            massage.getEndTime(),
            massage.getReceiverIdOrNull(),
            massage.getMassageType()
        );
    }

    @Override
    public void deleteMassage(String massageId) throws SQLException {
        String sql = "DELETE FROM massage WHERE massage_id = ?";
        jdbcTemplate.update(sql, massageId);
    }

    @Override
    public void withdrawMessage(String massageId) throws SQLException {
        // 撤回消息的实现，可以是标记消息状态或者添加撤回标记
        String sql = "UPDATE massage SET is_withdrawn = true WHERE massage_id = ?";
        jdbcTemplate.update(sql, massageId);
    }

    @Override
    public Massage getMassageByID(String massageId) throws SQLException {
        String sql = "SELECT * FROM massage WHERE massage_id = ?";
        List<Massage> massages = jdbcTemplate.query(sql, massageRowMapper, massageId);
        return massages.isEmpty() ? null : massages.get(0);
    }

    @Override
    public boolean isRead(Massage massage) throws SQLException {
        String sql = "SELECT is_read FROM massage WHERE massage_id = ?";
        Boolean isRead = jdbcTemplate.queryForObject(sql, Boolean.class, massage.getMassageId());
        return isRead != null && isRead;
    }
    
    // 额外添加的实用方法：标记消息为已读
    public void markAsRead(String massageId) throws SQLException {
        String sql = "UPDATE massage SET is_read = true WHERE massage_id = ?";
        jdbcTemplate.update(sql, massageId);
    }
    
    // 获取用户的所有消息
    public List<Massage> getUserMessages(String userId) throws SQLException {
        String sql = "SELECT * FROM massage WHERE receiver_id = ? OR sender_id = ? ORDER BY start_time DESC";
        return jdbcTemplate.query(sql, massageRowMapper, userId, userId);
    }
    
    // 获取用户的未读消息
    public List<Massage> getUnreadMessages(String userId) throws SQLException {
        String sql = "SELECT * FROM massage WHERE receiver_id = ? AND is_read = false ORDER BY start_time DESC";
        return jdbcTemplate.query(sql, massageRowMapper, userId);
    }
}