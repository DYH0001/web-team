package com.teamwork.kejizhai.bean;
import java.util.List;
import java.util.Date;

public class review{
    private String RId;
    private String Iid;
    private String Oid;
    private String sid;
    private int Ireview;//0-5rank;
    private String Icomment;
    private String Uid;
    private Date reviewDate;

public review(){}


public void setSid(String sid) {
    this.sid = sid;
}

public void setIid(String Iid) {
    this.Iid = Iid;
}

public void setOid(String oid) {
    this.Oid = oid;
}

public void setIreview(int ireview) {
    this.Ireview = ireview;
}

public void setUid(String uid) {
    this.Uid = uid;
}

// 修正setIcomment方法
public void setIcomment(String icomment) {
    this.Icomment = icomment;
}

// 修正构造函数
public review(String Iid, String Oid, String sid, int Ireview, String Icomment, String Uid) {
    this.Iid = Iid;
    this.Oid = Oid;
    this.sid = sid;
    this.Ireview = Ireview;  
    this.Icomment = Icomment;
    this.Uid = Uid;
}

public String getIid(){
    return Iid;
}


public String getOid(){
    return Oid;
}


public String getSid(){
    return sid;
}
public int getIreview(){
    return Ireview;
}
public String getIcomment(){
    return Icomment;
}
public String getUid(){
    return Uid;
}
public void setIcomment(String Iid,String Oid,String Uid){
    this.Iid=Iid;
    this.Oid=Oid;
    this.Uid=Uid;
}
public Date getReviewDate() {
    return reviewDate;
}

public void setReviewDate(Date reviewDate) {
    this.reviewDate = reviewDate;
}
public String getRId() {
    return RId;
}

public void setRId(String RId) {
    this.RId = RId;
}
}
