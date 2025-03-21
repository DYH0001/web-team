package com.teamwork.kejizhai.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Items {
    @JsonProperty("Iid")
    private String iid;
    
    @JsonProperty("Iname")
    private String Iname;
    private double price;
    private String description; //商品描述
    private String info;  //商品参数，对于不同分类的商品需要填写的参数应该不同，可能需要单独分个类？
    private String Iimage;
    private String category;  //要不要做个list或者枚举类？
    private String shop;
    private String uptime;
    private int Istatus;  //show=0;hide=1;undercarriage=3;
    private String imageHash;

    public Items() {};
public Items(String iid, String Iname, double price, String description, String Iimage, String category, String shop, String uptime, int Istatus) {
    this.iid = iid;
    this.Iname = Iname;
    this.price = price;
    this.description = description;
    this.Iimage = Iimage;
    this.category = category;
    this.shop = shop;
    this.uptime = uptime;
    this.Istatus = Istatus;// 图片MD5哈希值
    }    
    // 添加getter和setter
    public String getImageHash() {
        return imageHash;
    }
    
    public void setImageHash(String imageHash) {
        this.imageHash = imageHash;
    }

    public String getIid() {
        return iid;
    }
    public String getIname(String Iid){
        return Iname;
    }
    public void setIname(String Iid){}
    public double getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
public void setDescription(String description) {
    this.description = description;
}
public String getIimage() {
    return Iimage;
}
public void setIimage(String Iimage) {
    this.Iimage = Iimage;
}
public String getCategory() {
    return category;
}
public void setCategory(String category) {
    this.category = category;
}
public String getShop() {
    return shop;
}
public String getUptime() {
    return uptime;
}
public int getIstatus() {
    return Istatus;
}
public void setIstatus(int Istatus) {
    this.Istatus = Istatus;
}
public String getInfo() {
    return info;
}
public void setInfo(String info) {
    this.info = info;
}
}
