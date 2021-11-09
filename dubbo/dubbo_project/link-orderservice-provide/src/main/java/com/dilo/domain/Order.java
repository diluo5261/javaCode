package com.dilo.domain;

import java.io.Serializable;

public class Order implements Serializable {
    //在序列化的时候。=生成一个唯一标识
    private static final long serialVersionUID = 1639914964966783607L;
    private String id;
    private String goodName;
    private float price;
    private int amount;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", goodName='" + goodName + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
