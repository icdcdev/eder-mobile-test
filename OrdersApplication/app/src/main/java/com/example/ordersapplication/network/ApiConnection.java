package com.example.ordersapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConnection {
    public static IOrderServices getServiceOrders() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://5a5q7x88fb.execute-api.us-west-2.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(IOrderServices.class);
    }
}
