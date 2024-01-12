package com.example.ordersapplication.model;

import java.util.List;

public class Orders {
    private String message;
    private List<Order> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Order> getData() {
        return data;
    }

    public void setData(List<Order> data) {
        this.data = data;
    }
}
