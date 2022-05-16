package hcmute.edu.vn.foody_08.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.model.Order;
import hcmute.edu.vn.foody_08.service.OrderService;

public class OrderAdapter extends BaseAdapter {

    Activity context;
    int layout;
    List<Order> listOrders;

    public OrderAdapter(Activity context, int layout, List<Order> listOrders) {
        this.context = context;
        this.layout = layout;
        this.listOrders = listOrders;
    }

    @Override
    public int getCount() {
        return listOrders.size();
    }

    @Override
    public Object getItem(int position) {
        return listOrders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new OrderAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            convertView.setTag(holder);
            //add control UI
            addControl(holder, convertView);
            //get data
            getData();
            //set data to each item
            setData(holder, listOrders.get(position));
            //add event
            addEvent(holder,listOrders.get(position));
        } else {
            holder = (OrderAdapter.ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    private void addEvent(ViewHolder holder, Order order) {
    }

    private void setData(ViewHolder holder, Order order) {
        holder.textViewOrderId.setText(Integer.toString(order.getId()));
        holder.textViewOrderStatus.setText(order.getStatus());
        holder.textViewTotalPriceOrder.setText(order.getPriceTotal().toString()+" VND");
        holder.textViewTotalQuantityOrder.setText(Integer.toString(order.getNumTotal()));
    }

    private void getData() {
    }

    private void addControl(ViewHolder holder, View convertView) {
        holder.textViewOrderId=convertView.findViewById(R.id.textViewOrderId);
        holder.textViewOrderStatus=convertView.findViewById(R.id.textViewOrderStatus);
        holder.textViewTotalPriceOrder=convertView.findViewById(R.id.textViewTotalPriceOrder);
        holder.textViewTotalQuantityOrder=convertView.findViewById(R.id.textViewTotalQuantityOrder);
    }

    private class ViewHolder {
        public TextView textViewOrderId, textViewOrderStatus, textViewTotalPriceOrder,textViewTotalQuantityOrder;
    }

}
