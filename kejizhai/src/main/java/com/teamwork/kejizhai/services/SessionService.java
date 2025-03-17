package com.teamwork.kejizhai.services;

import com.teamwork.kejizhai.bean.Session;
import com.teamwork.kejizhai.bean.Massage;
import com.teamwork.kejizhai.bean.Users;

import java.sql.SQLException;
import java.util.List;

public interface SessionService {
    public void addSession(List<Users> users) throws SQLException;
    public void deleteSession(String sessionId) throws SQLException;
    public Session getSessionByID(String sessionId) throws SQLException;
    public boolean isActive(Session session) throws SQLException;
    }
    
 
    

