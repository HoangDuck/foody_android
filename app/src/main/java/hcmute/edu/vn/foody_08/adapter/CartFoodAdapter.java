package hcmute.edu.vn.foody_08.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.model.CartItem;
import hcmute.edu.vn.foody_08.model.Food;
import hcmute.edu.vn.foody_08.model.OrderDetail;
import hcmute.edu.vn.foody_08.service.FoodService;
import hcmute.edu.vn.foody_08.view.OrderActivity;

public class CartFoodAdapter extends BaseAdapter {
    Activity context;
    int layout;
    List<CartItem> listCartItem;

    List<Food> listFood;
    FoodService foodService;

    public CartFoodAdapter(Activity context, int layout, List<CartItem> listCartItem) {
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
        CartFoodAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new CartFoodAdapter.ViewHolder();
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
            holder = (CartFoodAdapter.ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    private void getData() {
        listFood = new ArrayList<>();
        foodService = new FoodService(this.context);
        listFood = foodService.getAllFoods();
    }

    private void setData(ViewHolder holder, CartItem cartItem) {

        for (Food food : listFood
        ) {
            if (food.getId() == cartItem.getId()) {
                holder.textViewFoodName.setText(food.getName());
                holder.textViewPriceFood.setText(food.getPrice().toString());
                holder.textViewQuantity.setText(Integer.toString(cartItem.getQuantity()));

                break;
            }
        }

    }

    private void addEvent(ViewHolder holder, CartItem cartItem) {
        holder.btn_decrease_quantity.setOnClickListener(v -> {
            int temp=cartItem.getQuantity();
            temp--;
            cartItem.setQuantity(temp);
            holder.textViewQuantity.setText(temp);
        });
        holder.btn_increase_quantity.setOnClickListener(v -> {
            int temp=cartItem.getQuantity();
            temp++;
            cartItem.setQuantity(temp);
            holder.textViewQuantity.setText(temp);
        });
    }

    private void addControl(ViewHolder holder, View convertView) {
        holder.textViewFoodName = convertView.findViewById(R.id.textViewFoodName);
        holder.textViewQuantity = convertView.findViewById(R.id.textViewQuantity);
        holder.textViewPriceFood = convertView.findViewById(R.id.textViewPriceFood);
        holder.btn_decrease_quantity = convertView.findViewById(R.id.btn_decrease_quantity);
        holder.btn_increase_quantity = convertView.findViewById(R.id.btn_increase_quantity);
    }

    private class ViewHolder {
        public TextView textViewFoodName, textViewPriceFood, textViewQuantity;
        public ImageButton btn_decrease_quantity, btn_increase_quantity;
    }
}
