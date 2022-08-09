package com.example.financial.bean;

import java.io.Serializable;

public class OutpayBean implements Serializable {
    private int id;
    private String time;
    private String type;
    private String payee;
    private String remark;
    private double money;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    public OutpayBean(int id, String time, String type, String payee, String remarl, double money) {
        this.id = id;
        this.time = time;
        this.type = type;
        this.payee = payee;
        this.remark = remarl;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
