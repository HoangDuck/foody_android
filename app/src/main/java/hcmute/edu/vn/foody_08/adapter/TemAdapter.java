package hcmute.edu.vn.foody_08.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import hcmute.edu.vn.foody_08.model.Shop;
import hcmute.edu.vn.foody_08.view.RestaurantActivity;
//Tem adapter is adapter for gridview item food
public class TemAdapter extends BaseAdapter {
    private Activity context;
    private int layout;
    private List<Shop> listRestaurant;

    public TemAdapter(Activity context, int layout, List<Shop> listRestaurant) {
        this.context = context;
        this.layout = layout;
        this.listRestaurant = listRestaurant;
    }

    public TemAdapter() {
        super();
    }

    @Override
    public int getCount() {
        return listRestaurant.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            view.setTag(holder);
            //add control UI
            addControl(holder,view);
            //set data to each item
            setData(holder,listRestaurant.get(i));
            holder.linearLayout.setOnClickListener(view1 -> {
                Intent intent=new Intent(context, RestaurantActivity.class);
                intent.putExtra("shop",listRestaurant.get(i));
                context.startActivity(intent);
            });
        } else {
            holder = (ViewHolder) view.getTag();
        }
        return view;
    }

    private void addControl(ViewHolder viewHolder,View view){
        viewHolder.linearLayout=view.findViewById(R.id.item_grid_view);
        viewHolder.textViewNameRestaurant=view.findViewById(R.id.textViewRestaurantName);
        viewHolder.getTextViewRestaurantDescription=view.findViewById(R.id.textViewRestaurantDescription);
        viewHolder.imageViewRestaurant=view.findViewById(R.id.imageViewRestaurant);
    }

    private void setData(ViewHolder viewHolder,Shop shop){
        viewHolder.textViewNameRestaurant.setText(shop.getName());
        viewHolder.getTextViewRestaurantDescription.setText(shop.getDescription().substring(0,20)+"...");
        //set Image
        Picasso.get().load(shop.getImage()).into(viewHolder.imageViewRestaurant);
    }

    private class ViewHolder {
        public TextView textViewNameRestaurant,getTextViewRestaurantDescription;
        public ImageView imageViewRestaurant;
        public LinearLayout linearLayout;

    }
}
