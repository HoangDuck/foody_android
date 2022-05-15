package hcmute.edu.vn.foody_08.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.model.CartItem;
import hcmute.edu.vn.foody_08.model.Food;
import hcmute.edu.vn.foody_08.service.FoodService;

public class CartFoodPaymentAdapter extends BaseAdapter {
    Activity context;
    int layout;
    List<CartItem> listCartItem;

    List<Food> listFood;
    FoodService foodService;

    public CartFoodPaymentAdapter(Activity context, int layout, List<CartItem> listCartItem) {
        this.context = context;
        this.layout = layout;
        this.listCartItem = listCartItem;
    }

    @Override
    public int getCount() {
        return listCartItem.size();
    }

    @Override
    public Object getItem(int position) {
        return listCartItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CartFoodPaymentAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new CartFoodPaymentAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            convertView.setTag(holder);
            //add control UI
            addControl(holder, convertView);
            //get data
            getData();
            //set data to each item
            setData(holder, listCartItem.get(position));
            //add event
            addEvent(holder,listCartItem.get(position));
        } else {
            holder = (CartFoodPaymentAdapter.ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    private void getData() {
        listFood = new ArrayList<>();
        foodService = new FoodService(this.context);
        listFood = foodService.getAllFoods();
    }

    private void setData(CartFoodPaymentAdapter.ViewHolder holder, CartItem cartItem) {

        for (Food food : listFood
        ) {
            if (food.getId() == cartItem.getId()) {
                holder.textViewFoodName.setText(food.getName());
                holder.textViewPriceFood.setText(food.getPrice().toString()+" VND");
                holder.textViewQuantity.setText(Integer.toString(cartItem.getQuantity()));

                break;
            }
        }

    }

    private void addEvent(CartFoodPaymentAdapter.ViewHolder holder, CartItem cartItem) {
    }

    private void addControl(CartFoodPaymentAdapter.ViewHolder holder, View convertView) {
        holder.textViewFoodName = convertView.findViewById(R.id.textViewFoodName);
        holder.textViewQuantity = convertView.findViewById(R.id.textViewQuantity);
        holder.textViewPriceFood = convertView.findViewById(R.id.textViewPriceFood);
    }

    private class ViewHolder {
        public TextView textViewFoodName, textViewPriceFood, textViewQuantity;
    }
}
