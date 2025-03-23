package com.teamwork.kejizhai.bean;

import com.teamwork.kejizhai.bean.Users;
import lombok.Getter;

import java.util.Date;
import java.util.List;

public class Session{
    @Getter
    private int sessionId;
    @Getter
    private List<Users> users;
    @Getter
    private Date startTime;
    @Getter
    private Date endTime;
    private boolean isActive;
    // 添加获取消息列表的方法
    @Getter
    private List<Massage> massages;

    public Session(){}

    public Session(int sessionId, List<Users> users, Date startTime, Date endTime, boolean isActive, List<Massage> massages){
        this.sessionId = sessionId;
        this.users = users;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isActive = isActive;
        this.massages = massages;
    }

    public void setSessionId(int sessionId){
        this.sessionId = sessionId;
    }

    public void setUsers(List<Users> users){
        this.users = users;
    }

    public void setStartTime(Date startTime){
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime){
        this.endTime = endTime;
    }
    public boolean getIsActive(){
        return isActive;
    }
    public void setIsActive(boolean isActive){
        this.isActive = isActive;
    }

    // 添加设置消息列表的方法
    public void setMassages(List<Massage> massages) {
        this.massages = massages;
    }
}