package com.teamwork.kejizhai.bean;
public class shop {
    private String sid;
    private String uid;
    private String Iid;
    private int count;
    private int inum;

    public shop() {
    }

public shop(String sid, String uid, String Iid, int count, int inum) {
        this.sid = sid;
        this.uid = uid;
        this.Iid = Iid;
        this.count = count;
        this.inum = inum;
    }


    public String getsid() {
        return sid;
    }
    public String getUid(){
        return uid;
    }
    public String getIid(){
        return Iid;
    }
    public int getCount(){
        return count;
    }
    public int getInum(){
        return inum;
    }
    }
