package com.teamwork.kejizhai.bean;

import com.teamwork.kejizhai.bean.Order;
import com.teamwork.kejizhai.bean.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;


public class Users {
    // 添加缺失的 setter 方法
    @Setter
    @Getter
    private String Uid;
    @Getter
    private String Uname;
    @Getter
    private int age;
    private String password_Hash;
    @Getter
    private String telephone;
    private enum role {
        guest,
        user,
        admin,
        support,}

    private String addressID;

    private boolean isPhoneVerified;
    private boolean isEmailVerified;
    @Setter
    @Getter
    private Date CreateTime;
    @Setter
    @Getter
    private Date UpdateTime;
    @Setter
    @Getter
    private List<Order> userOrders;

public Users() {
    }
public Users(String Uid, String Uname, int age, String address, String telephone, String password_Hash,String addressID, boolean isPhoneVerified, boolean isEmailVerified, Date CreateTime, Date UpdateTime) { 
        this.Uid = Uid;
        this.Uname = Uname;
        this.age = age;
        this.addressID = addressID;
        this.password_Hash = password_Hash;
        this.telephone = telephone;
        this.isPhoneVerified = isPhoneVerified;
        this.isEmailVerified = isEmailVerified;
        this.CreateTime = CreateTime;
        this.UpdateTime = UpdateTime;
        this.userOrders = userOrders;
    }


    public void setUname(String Uname) {
}

    public void setAge(int age) {

}
public String getAddress() {
    return addressID;
}
public void setAddress(String address) {
}

    public void setTelephone(String telephone) {
}
public String getPassword() {
    return password_Hash;
}
public void setPassword(String password) {
}

    public boolean isPhoneVerified() {
    return isPhoneVerified;
}
public boolean isEmailVerified() {
    return isEmailVerified;
}

    public void setPhoneVerified(boolean isPhoneVerified) {
    this.isPhoneVerified = isPhoneVerified;
}

public void setEmailVerified(boolean isEmailVerified) {
    this.isEmailVerified = isEmailVerified;
}
}