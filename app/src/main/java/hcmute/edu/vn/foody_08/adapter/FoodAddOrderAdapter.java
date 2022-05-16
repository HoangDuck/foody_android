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
import hcmute.edu.vn.foody_08.view.LoginRegisterActivity;
import hcmute.edu.vn.foody_08.view.OrderActivity;
import hcmute.edu.vn.foody_08.view.RestaurantActivity;

public class FoodAddOrderAdapter extends BaseAdapter {
    Activity context;
    int layout;
    List<Food> listFood;
    public FoodAddOrderAdapter(Activity context, int layout, List<Food> listFood) {
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
        FoodAddOrderAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new FoodAddOrderAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            convertView.setTag(holder);
            //add control UI
            addControl(holder,convertView);
            //set data to each item
            setData(holder,listFood.get(position));
            holder.linearLayout.setOnClickListener(view1 -> {
                Intent intent=new Intent(context, OrderActivity.class);
                intent.putExtra("food",listFood.get(position));
                context.startActivity(intent);
            });
        } else {
            holder = (FoodAddOrderAdapter.ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    private void addControl(FoodAddOrderAdapter.ViewHolder viewHolder, View view){
        viewHolder.linearLayout=view.findViewById(R.id.itemFood);
        viewHolder.textViewNameFood=view.findViewById(R.id.textViewFoodName);
        viewHolder.getTextViewFoodDescription=view.findViewById(R.id.textViewDescriptionFood);
        viewHolder.textViewPriceFood=view.findViewById(R.id.textViewPriceFood);
        viewHolder.imageViewFood=view.findViewById(R.id.imageViewFood);
    }

    private void setData(FoodAddOrderAdapter.ViewHolder viewHolder, Food food){
        viewHolder.textViewNameFood.setText(food.getName());
        viewHolder.textViewPriceFood.setText(food.getPrice().toString()+" VND");
        try{
            viewHolder.getTextViewFoodDescription.setText(food.getDescription().substring(0,20)+"...");
        }catch (Exception e){
            viewHolder.getTextViewFoodDescription.setText(food.getDescription().substring(0,10)+"...");
        }
        //set Image
        Picasso.get().load(food.getImage()).into(viewHolder.imageViewFood);
    }

    public void updateListItem(List<Food> listFood){
        this.listFood.clear();
        this.listFood.addAll(listFood);
        this.notifyDataSetChanged();
    }

    private class ViewHolder {
        public TextView textViewNameFood,getTextViewFoodDescription,textViewPriceFood;
        public ImageView imageViewFood;
        public LinearLayout linearLayout;
    }
}
