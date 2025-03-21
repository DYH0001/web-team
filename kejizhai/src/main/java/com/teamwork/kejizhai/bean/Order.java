package com.teamwork.kejizhai.bean;

import java.util.Date;
import java.util.List;

import com.teamwork.kejizhai.bean.Items;
import com.teamwork.kejizhai.bean.Address;
import com.teamwork.kejizhai.bean.Users;
import lombok.Getter;
import lombok.Setter;

/**
 * 订单类
 * @author Administrator
 *
 */
public class Order {
    @Setter
    @Getter
    private String Oid; //订单号
    @Setter
    @Getter
    private Date orderDate;
    private String Uid; //userId
    @Setter
    @Getter
    private String orderNumber; //订单号
    @Setter
    @Getter
    private List<Items> orderItems;
    @Getter
    @Setter
    private double totalPrice;
    private Address orderAddress;
    @Setter
    @Getter
    private String payType;  //weixin,ali, bank
    @Getter
    private int payStatus;  //paid=0,unpaid=1
    @Setter
    @Getter
    private String paymentId;  // 支付流水号
    @Getter
    private String paymentResponse; // 支付响应信息 // 支付时间
    @Getter
    private String deliveryStatus; //物流信息
    @Getter
    private String deliveryCompany;//快递公司
    @Getter
    private String deliveryNumber; //快递单号
    @Getter
    private Date payDate;  //支付日期
    @Getter
    private Date receiveDate; //收货日期


    public Order(){}
    public Order(String Oid, Date orderDate, String userId, String orderNumber, List<Items> orderItems, double totalPrice, Address orderAddress, String payType, int payStatus, String deliveryStatus, String deliveryCompany, String deliveryNumber, Date payDate, Date receiveDate) {
        super();
        this.Oid = Oid;
        this.orderDate = orderDate;
        this.Uid = userId;
        this.orderNumber = orderNumber;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
        this.orderAddress = orderAddress;
        this.payType = payType;
        this.payStatus = payStatus;
        this.deliveryStatus = deliveryStatus;
        this.deliveryCompany = deliveryCompany;
        this.deliveryNumber = deliveryNumber;
        this.payDate = payDate;
        this.receiveDate = receiveDate;
    }

    public String getUserId() {
        return Uid;
    }
    public void setUserId(String Uid) {
        this.Uid = Uid;
    }

    public Address getAddress() {
        return orderAddress;
    }
    public void setAddress(Address orderAddress) {
        this.orderAddress = orderAddress;
    }

    public void setPaymentResponse(){
    }

    public void setPayStatus() {
    }

    public void setDeliveryStatus() {
    }

    public void setDeliveryCompany() {
    }

    public void setDeliveryNumber() {
    }

    public void setPayDate() {
    }

    public void setReceiveDate() {
    }
}