package com.teamwork.kejizhai.controller;

import com.teamwork.kejizhai.bean.Session;
import com.teamwork.kejizhai.bean.Massage;
import com.teamwork.kejizhai.bean.Users;
import com.teamwork.kejizhai.services.SessionService;
import com.teamwork.kejizhai.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;
    
    @Autowired
    private UserService userService;

    /**
     * 创建新会话
     * @param userIds 用户ID列表
     * @return 创建结果
     */
    @PostMapping
    public ResponseEntity<?> createSession(@RequestBody List<String> userIds) {
        try {
            List<Users> users = new ArrayList<>();
            for (String uid : userIds) {
                Users user = userService.getUserById(uid);
                if (user != null) {
                    users.add(user);
                }
            }
            
            if (users.size() < 2) {
                return ResponseEntity.badRequest().body(errorResponse("创建会话至少需要两个有效用户"));
            }
            
            sessionService.addSession(users);
            
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "会话创建成功");
            return ResponseEntity.ok(response);
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body(errorResponse("创建会话失败: " + e.getMessage()));
        }
    }

    /**
     * 获取会话详情
     * @param sessionId 会话ID
     * @return 会话信息
     */
    @GetMapping("/{sessionId}")
    public ResponseEntity<?> getSession(@PathVariable String sessionId) {
        try {
            Session session = sessionService.getSessionByID(sessionId);
            if (session != null) {
                return ResponseEntity.ok(successResponse(session));
            }
            return ResponseEntity.notFound().build();
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body(errorResponse("获取会话失败: " + e.getMessage()));
        }
    }

    /**
     * 关闭会话
     * @param sessionId 会话ID
     * @return 关闭结果
     */
    @DeleteMapping("/{sessionId}")
    public ResponseEntity<?> closeSession(@PathVariable String sessionId) {
        try {
            sessionService.deleteSession(sessionId);
            return ResponseEntity.ok(successResponse("会话已关闭"));
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body(errorResponse("关闭会话失败: " + e.getMessage()));
        }
    }

    /**
     * 检查会话活跃状态
     * @param sessionId 会话ID
     * @return 活跃状态
     */
    @GetMapping("/{sessionId}/status")
    public ResponseEntity<?> checkSessionStatus(@PathVariable String sessionId) {
        try {
            Session session = sessionService.getSessionByID(sessionId);
            boolean isActive = session != null && sessionService.isActive(session);
            return ResponseEntity.ok(Collections.singletonMap("isActive", isActive));
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body(errorResponse("检查会话状态失败: " + e.getMessage()));
        }
    }

    /**
     * 获取用户的所有会话
     * @param userId 用户ID
     * @return 会话列表
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserSessions(@PathVariable String userId) {
        try {
            List<Session> sessions = sessionService.getUserSessions(userId);
            return ResponseEntity.ok(successResponse(sessions));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(errorResponse("获取用户会话失败: " + e.getMessage()));
        }
    }

    /**
     * 构建成功响应
     * @param data 响应数据
     * @return 响应对象
     */
    private Map<String, Object> successResponse(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", data);
        return response;
    }

    /**
     * 构建错误响应
     * @param message 错误信息
     * @return 响应对象
     */
    private Map<String, Object> errorResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", message);
        return response;
    }
}