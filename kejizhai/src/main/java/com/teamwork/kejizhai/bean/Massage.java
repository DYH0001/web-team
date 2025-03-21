package com.teamwork.kejizhai.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Massage {
    @Setter
    @Getter
    private String senderId;
    @Setter
    @Getter
    private String massageId;
    @Setter
    @Getter
    private List<String> Content;
    @Setter
    @Getter
    private Date startTime;
    @Setter
    @Getter
    private Date endTime;
    private Optional<String> receiverId;
    @Setter
    @Getter
    private int MassageType;  //以后做消息内容识别判断是string还是int

    public Massage(){}

    public Massage(String senderId, String massageId, List<String> Content, Date startTime, Date endTime, Optional<String> receiverId, int MassageType){
        this.senderId = senderId;
        this.massageId = massageId;
        this.Content = Content;
        this.startTime = startTime;
        this.receiverId = receiverId;
        this.MassageType = MassageType;
    }

    public String getReceiverIdOrNull(){
        return receiverId.orElse(null);
    }   
    public void setReceiverId(String receiverId){
        this.receiverId = Optional.ofNullable(receiverId);
    }
}