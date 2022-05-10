package hcmute.edu.vn.foody_08.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.model.Food;
import hcmute.edu.vn.foody_08.view.OrderActivity;

public class FoodRestaurantAdapter extends BaseAdapter {
    Activity context;
    int layout;
    List<Food> listFood;

    public FoodRestaurantAdapter(Activity context, int layout, List<Food> listFood) {
        this.context = context;
        this.layout = layout;
        this.listFood = listFood;
    }

    @Override
    public int getCount() {
        return listFood.size();
    }

    @Override
    public Object getItem(int position) {
        return listFood.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FoodRestaurantAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new FoodRestaurantAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            convertView.setTag(holder);
            //add control UI
            addControl(holder,convertView);
            //set data to each item
            setData(holder,listFood.get(position));
            addEvent(holder,position);
        } else {
            holder = (FoodRestaurantAdapter.ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    private void addEvent(ViewHolder holder,int position) {
        holder.btn_add_food_queue.setOnClickListener(v -> {
            Intent intent=new Intent(context,OrderActivity.class);
            intent.putExtra("food",listFood.get(position));
            context.startActivity(intent);
        });
    }

    private void setData(ViewHolder holder, Food food) {
        holder.textViewNameFood.setText(food.getName());
        holder.getTextViewFoodDescription.setText(food.getDescription());
        holder.textViewPriceFood.setText(food.getPrice().toString()+" VND");
        Picasso.get().load(food.getImage()).into(holder.imageViewFood);
    }

    private void addControl(ViewHolder holder, View convertView) {
        holder.textViewNameFood=convertView.findViewById(R.id.textViewFoodName);
        holder.getTextViewFoodDescription=convertView.findViewById(R.id.textViewDescriptionFood);
        holder.textViewPriceFood=convertView.findViewById(R.id.textViewPriceFood);
        holder.imageViewFood=convertView.findViewById(R.id.imageViewFood);
        holder.btn_add_food_queue=convertView.findViewById(R.id.btn_add_food_queue);
    }

    private class ViewHolder {
        public TextView textViewNameFood,getTextViewFoodDescription,textViewPriceFood;
        public ImageView imageViewFood,btn_add_food_queue;
    }
}
