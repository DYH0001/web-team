package com.teamwork.kejizhai.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class Items {
    @Getter
    @Setter
    @JsonProperty("iid")
    private String Iid;

    @Setter
    @JsonProperty("iname")
    private String Iname;
    @Setter
    @Getter
    private double price;
    @Setter
    @Getter
    private String description; // 商品描述
    @Setter
    @Getter
    private String info; // 商品参数，对于不同分类的商品需要填写的参数应该不同，可能需要单独分个类？
    @Getter
    @Setter
    private String Iimage;
    @Setter
    @Getter
    private String category; // 要不要做个list或者枚举类？
    @Getter
    private String shop;
    @Getter
    private String uptime;
    @Setter
    @Getter
    private int Istatus; // show=0;hide=1;undercarriage=3;
    @Setter
    @Getter
    private String imageHash;

    public Items() {
    };

    public Items(String Iid, String Iname, double price, String description, String Iimage, String category,
            String shop, String uptime, int Istatus) {
        this.Iid = Iid;
        this.Iname = Iname;
        this.price = price;
        this.description = description;
        this.Iimage = Iimage;
        this.category = category;
        this.shop = shop;
        this.uptime = uptime;
        this.Istatus = Istatus;// 图片MD5哈希值
    }
    public String getIname(String s) {
        return Iname;
    }
}