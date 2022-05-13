package hcmute.edu.vn.foody_08.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.SearchActivity;
import hcmute.edu.vn.foody_08.adapter.FoodRestaurantAdapter;
import hcmute.edu.vn.foody_08.model.Food;
import hcmute.edu.vn.foody_08.model.Shop;
import hcmute.edu.vn.foody_08.service.FoodService;
import hcmute.edu.vn.foody_08.service.ShareReferences;

public class RestaurantActivity extends AppCompatActivity {

    TextView textViewRestaurantNameHeader, textViewRestaurantName, textViewStatus, textViewOpeningTime, textViewAddress;
    ImageView imageViewRestaurant;
    ListView listViewFoodRestaurant;

    Shop shop;
    List<Food> listFoodByShopId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        getData();
        addControl();
        setData();
        addEvent();
    }

    private void setData() {
        Picasso.get().load(shop.getImage()).into(imageViewRestaurant);
        textViewRestaurantNameHeader.setText(shop.getName());
        textViewRestaurantName.setText(shop.getName());
        textViewStatus.setText(shop.getStatus());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        textViewOpeningTime.setText(shop.getOpenTime().format(dtf) +"-"+ shop.getCloseTime().format(dtf));
        textViewAddress.setText(shop.getAddress());

        FoodRestaurantAdapter foodRestaurantAdapter=new FoodRestaurantAdapter(this,R.layout.item_food_restaurant,listFoodByShopId);
        listViewFoodRestaurant.setAdapter(foodRestaurantAdapter);
        foodRestaurantAdapter.notifyDataSetChanged();
    }

    private void getData() {
        //init list food
        listFoodByShopId=new ArrayList<>();
        //get data from previous page => shop
        Intent intent=getIntent();
        shop= (Shop) intent.getSerializableExtra("shop");
        int tempId=shop.getId();
        FoodService foodService=new FoodService(this);
        listFoodByShopId=foodService.getAllFoodsByIdShop(tempId);
    }

    private void addEvent() {

    }

    private void addControl() {
        textViewRestaurantNameHeader=findViewById(R.id.textViewRestaurantNameHeader);
        textViewRestaurantName=findViewById(R.id.textViewRestaurantName);
        textViewStatus=findViewById(R.id.textViewStatus);
        textViewOpeningTime=findViewById(R.id.textViewOpeningTime);
        textViewAddress=findViewById(R.id.textViewAddress);
        imageViewRestaurant=findViewById(R.id.imageViewRestaurant);
        listViewFoodRestaurant=findViewById(R.id.listViewFoodRestaurant);

    }

    public void closeRestaurantPage(View view) {
        finish();
    }

    public void goToYourCart(View view) {
        Intent intent;
        if(checkLogin()){
            intent = new Intent(this, OrderAllCartActivity.class);
        }
        else {
            intent = new Intent(this, LoginRegisterActivity.class);
        }
        //truyền đi và nhận lại dữ liệu qua intent dạng json
        startActivity(intent);

    }

    public void openSearchView(View view) {
        Intent intent = new Intent(RestaurantActivity.this, SearchActivity.class);
        startActivity(intent);
    }
    private boolean checkLogin() {
        ShareReferences shareReferences=ShareReferences.getInstance(this);
        try{
            String user=shareReferences.getData("user");
            if(user.equals("")){
                return false;
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }
}