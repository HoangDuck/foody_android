package hcmute.edu.vn.foody_08.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.model.CartItem;
import hcmute.edu.vn.foody_08.model.Food;
import hcmute.edu.vn.foody_08.model.OrderDetail;
import hcmute.edu.vn.foody_08.model.User;
import hcmute.edu.vn.foody_08.service.ShareReferences;

public class OrderActivity extends AppCompatActivity {
    ImageView imageViewFood;
    TextView textViewFoodName, textViewPriceFood, textViewDescriptionFood;

    private int quantity = 1;
    TextView txt_quantity;
    ImageButton btn_increase, btn_decrease;
    Food food;
    CartItem cartItem;
    ShareReferences shareReferences;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getData();
        addControl();
        setData();
        addEvent();
    }

    private void setData() {
        textViewFoodName.setText(food.getName());
        textViewPriceFood.setText(food.getPrice() + " VND");
        textViewDescriptionFood.setText(food.getDescription());//set Image
        Picasso.get().load(food.getImage()).into(imageViewFood);
    }

    private void getData() {
        gson = new Gson();
        shareReferences = ShareReferences.getInstance(this);
        Intent intent = getIntent();
        food = (Food) intent.getSerializableExtra("food");
        cartItem = new CartItem(food.getId(), food.getName(), food.getShopId(), food.getPrice(), 1);
    }

    private void addEvent() {
        btn_increase.setOnClickListener(view -> {
            quantity++;
            cartItem.setQuantity(quantity);
            txt_quantity.setText(Integer.toString(quantity));
        });
        btn_decrease.setOnClickListener(view -> {
            if (quantity != 1) {
                quantity--;
                cartItem.setQuantity(quantity);
                txt_quantity.setText(Integer.toString(quantity));
            }
        });
    }

    private void addControl() {
        txt_quantity = findViewById(R.id.txt_quantity);
        btn_increase = findViewById(R.id.btn_increase_quantity);
        btn_decrease = findViewById(R.id.btn_decrease_quantity);
        imageViewFood = findViewById(R.id.imageViewFood);
        textViewFoodName = findViewById(R.id.textViewFoodName);
        textViewPriceFood = findViewById(R.id.textViewPriceFood);
        textViewDescriptionFood = findViewById(R.id.textViewDescriptionFood);
    }

    public void goToYourCart(View view) {
        Intent intent;
        if (checkLogin()) {
            intent = new Intent(this, OrderAllCartActivity.class);
            intent.putExtra("cartItemList", (Serializable) CartItem.cartItemList);
        } else {
            intent = new Intent(this, LoginRegisterActivity.class);
        }
        //truyền đi và nhận lại dữ liệu qua intent dạng json
        startActivity(intent);
    }

    public void closeOrderPage(View view) {
        finish();
    }

    public void addToCartAndFinish(View view) {
        Intent intent;
        if (checkLogin()) {
            addFoodToListCart();
            shareReferences.saveData("cartItemList", gson.toJson(CartItem.cartItemList));
            intent = new Intent(this, OrderAllCartActivity.class);
        } else {
            intent = new Intent(this, LoginRegisterActivity.class);
        }
        startActivity(intent);
    }

    public void addFoodToListCart() {
        try{
            if(cartItem.getShopId()!=CartItem.cartItemList.get(0).getShopId()){
                CartItem.cartItemList.clear();
                CartItem.cartItemList.add(cartItem);
                return;
            }
        }catch (Exception e){

        }
        if (!isCartItemInCart()) {
            CartItem.cartItemList.add(cartItem);
        }else {
            int tempIndex=findIndexOfItemInCart();
            int tempQuantity=CartItem.cartItemList.get(tempIndex).getQuantity();
            tempQuantity++;
            CartItem.cartItemList.get(tempIndex).setQuantity(tempQuantity);
        }
    }
    private int findIndexOfItemInCart(){
        int tempLength=CartItem.cartItemList.size();
        for(int i=0;i<tempLength;i++){
            if(cartItem.getId()==CartItem.cartItemList.get(i).getId())
                return i;
        }
        return 0;
    }

    private boolean isCartItemInCart(){
        for (CartItem cartItemFood: CartItem.cartItemList
             ) {
            if(cartItemFood.getId()==cartItem.getId())
                return true;
        }
        return false;
    }

    private boolean checkLogin() {
        try {
            User user = shareReferences.getGlobalUser();
            if (user==null) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}