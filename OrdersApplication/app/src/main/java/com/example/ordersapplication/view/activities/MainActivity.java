package com.example.ordersapplication.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.example.ordersapplication.R;
import com.example.ordersapplication.databinding.ActivityMainBinding;
import com.example.ordersapplication.model.Order;
import com.example.ordersapplication.model.Orders;
import com.example.ordersapplication.network.ApiConnection;
import com.example.ordersapplication.utils.DatePickerFragment;
import com.example.ordersapplication.view.adapters.OrderAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ActivityMainBinding binding;
    private List<Order> orderList;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        configureView();
        loadService();
    }

    public void initView() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void configureView() {
        orderAdapter = new OrderAdapter();
        orderAdapter.setiEventItem((order, position) -> {
            int index = orderList.indexOf(order);
            orderList.get(index).setAssigned(true);
            orderAdapter.notifyItemRemoved(position);
        });
        binding.rvListOrder.setLayoutManager(new LinearLayoutManager(this));
        binding.rvListOrder.setAdapter(orderAdapter);
        binding.tabTypeOrder.addTab(binding.tabTypeOrder.newTab().setText(R.string.asignadas));
        binding.tabTypeOrder.addTab(binding.tabTypeOrder.newTab().setText(R.string.no_asignadas), true);
        binding.tabTypeOrder.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        List<Order> assignedList = orderList.stream().filter(order -> order.isAssigned()).collect(Collectors.toList());
                        orderAdapter.addAll(assignedList);
                        break;
                    case 1:
                        List<Order> unassignedList = orderList.stream().filter(order -> !order.isAssigned()).collect(Collectors.toList());
                        orderAdapter.addAll(unassignedList);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        binding.btnFilterOrder.setOnClickListener(view -> {
            DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(MainActivity.this);
            datePickerFragment.show(getSupportFragmentManager(), "");
        });
        binding.fbSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }

    private void loadService() {
        ApiConnection.getServiceOrders().getListOrders().enqueue(new Callback<Orders>() {
            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {
                Orders orders = response.body();
                if (orders != null) {
                    Log.d(MainActivity.class.getSimpleName(), "lista de ordenes " + orders.getData().size());
                    orderList = orders.getData();
                    orderAdapter.addAll(orderList);
                }
            }

            @Override
            public void onFailure(Call<Orders> call, Throwable t) {

            }
        });
    }
}