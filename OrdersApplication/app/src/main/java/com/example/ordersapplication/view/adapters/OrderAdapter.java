package com.example.ordersapplication.view.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;

import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordersapplication.R;
import com.example.ordersapplication.databinding.RowOrderDataBinding;
import com.example.ordersapplication.model.IEventItem;
import com.example.ordersapplication.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> implements Filterable {

    private List<Order> orderList;
    private List<Order> filteredList;
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
        Order order = filteredList.get(position);
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
        return filteredList == null ? 0 : filteredList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addAll(List<Order> orderList) {
        this.orderList = orderList;
        this.filteredList = orderList;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charSequenceString = charSequence.toString();
                if (charSequenceString.isEmpty()) {
                    filteredList = orderList;
                } else {
                    List<Order> filterList = new ArrayList<>();
                    for (Order item : orderList) {
                        if (item.getOrderId().toLowerCase().contains(charSequenceString.toLowerCase())) {
                            filterList.add(item);
                        }
                        filteredList = filterList;
                    }

                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (List<Order>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        RowOrderDataBinding rowOrderDataBinding;

        public OrderViewHolder(@NonNull RowOrderDataBinding itemView) {
            super(itemView.getRoot());
            rowOrderDataBinding = itemView;
        }
    }
}
