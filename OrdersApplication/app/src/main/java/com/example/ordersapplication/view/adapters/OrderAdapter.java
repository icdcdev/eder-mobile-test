package com.example.ordersapplication.view.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordersapplication.R;
import com.example.ordersapplication.databinding.RowOrderDataBinding;
import com.example.ordersapplication.model.IEventItem;
import com.example.ordersapplication.model.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<Order> orderList;
    private IEventItem iEventItem;

    public void setiEventItem(IEventItem iEventItem) {
        this.iEventItem = iEventItem;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowOrderDataBinding rowOrderDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_order_data, parent, false);
        return new OrderViewHolder(rowOrderDataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.rowOrderDataBinding.setData(order);
        changeStatusStyle(holder,order);
        holder.rowOrderDataBinding.getRoot().setOnClickListener(view -> {
            if (iEventItem != null) {
                iEventItem.onClickItem(order, position);
            }
        });
    }
    private void changeStatusStyle(OrderViewHolder holder, Order order){
        switch (order.getOrderStatusId()){
            case 1:
                holder.rowOrderDataBinding.cvStatusOrder.setCardBackgroundColor(ContextCompat.getColor(holder.rowOrderDataBinding.getRoot().getContext(),R.color.not_assigned_status_color));
                break;
            case 2:
                holder.rowOrderDataBinding.cvStatusOrder.setCardBackgroundColor(ContextCompat.getColor(holder.rowOrderDataBinding.getRoot().getContext(),R.color.black_color));
                break;
            case 3:
                holder.rowOrderDataBinding.cvStatusOrder.setCardBackgroundColor(ContextCompat.getColor(holder.rowOrderDataBinding.getRoot().getContext(),R.color.uninitiated_status_color));
                break;
            case 4:
                holder.rowOrderDataBinding.cvStatusOrder.setCardBackgroundColor(ContextCompat.getColor(holder.rowOrderDataBinding.getRoot().getContext(),R.color.detained_status_color));
                break;
            default:
                holder.rowOrderDataBinding.cvStatusOrder.setCardBackgroundColor(ContextCompat.getColor(holder.rowOrderDataBinding.getRoot().getContext(),R.color.default_status_color));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return orderList == null ? 0 : orderList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addAll(List<Order> orderList) {
        this.orderList = orderList;
        notifyDataSetChanged();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        RowOrderDataBinding rowOrderDataBinding;

        public OrderViewHolder(@NonNull RowOrderDataBinding itemView) {
            super(itemView.getRoot());
            rowOrderDataBinding = itemView;
        }
    }
}
