package com.example.ordersapplication.network;

import com.example.ordersapplication.model.Orders;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IOrderServices {

    @GET("orders")
    Call<Orders> getListOrders();
}
