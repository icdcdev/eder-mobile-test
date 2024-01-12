package com.example.ordersapplication.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.ordersapplication.R;
import com.example.ordersapplication.databinding.ActivityMainBinding;
import com.example.ordersapplication.model.Order;
import com.example.ordersapplication.model.Orders;
import com.example.ordersapplication.network.ApiConnection;
import com.example.ordersapplication.utils.DatePickerFragment;
import com.example.ordersapplication.utils.Utils;
import com.example.ordersapplication.view.adapters.OrderAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

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
        orderAdapter.setiEventItem(this::assignOrder);
        binding.rvListOrder.setLayoutManager(new LinearLayoutManager(this));
        binding.rvListOrder.setAdapter(orderAdapter);
        binding.svOrder.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterOrder(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterOrder(newText);
                return false;
            }
        });
        binding.tabTypeOrder.addTab(binding.tabTypeOrder.newTab().setText(R.string.asignadas));
        binding.tabTypeOrder.addTab(binding.tabTypeOrder.newTab().setText(R.string.no_asignadas), true);
        binding.tabTypeOrder.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        if (orderList != null) {
                            List<Order> assignedList = orderList.stream().filter(order -> order.isAssigned()).collect(Collectors.toList());
                            orderAdapter.addAll(assignedList);
                        }
                        break;
                    case 1:
                        if (orderList != null) {
                            List<Order> unassignedList = orderList.stream().filter(order -> !order.isAssigned()).collect(Collectors.toList());
                            orderAdapter.addAll(unassignedList);
                        }
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
            DatePickerFragment datePickerFragment = DatePickerFragment.newInstance();
            datePickerFragment.show(getSupportFragmentManager(), "");
        });
        binding.fbSignOut.setOnClickListener(view -> logOut());
    }

    private void loadService() {
        ApiConnection.getServiceOrders().getListOrders().enqueue(new Callback<Orders>() {
            @Override
            public void onResponse(Call<Orders> call, Response<Orders> response) {
                Orders orders = response.body();
                if (orders != null) {
                    orderList = orders.getData();
                    orderAdapter.addAll(orderList);
                }
            }

            @Override
            public void onFailure(Call<Orders> call, Throwable t) {

            }
        });
    }

    private void assignOrder(Order order, int position) {
        Utils.showDialog(this, getString(R.string.deseas_asignarte_esta_orden), (dialogInterface, i) -> {
            int index = orderList.indexOf(order);
            orderList.get(index).setAssigned(true);
            orderAdapter.notifyItemRemoved(position);
        }).show();
    }

    private void logOut() {
        Utils.showDialog(this, getString(R.string.estas_seguro_de_cerrar_sesi_n), (dialogInterface, i) -> finish()).show();
    }

    private void filterOrder(String invoice) {
        orderAdapter.getFilter().filter(invoice);
    }
}