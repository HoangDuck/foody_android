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
import hcmute.edu.vn.foody_08.service.ShareReferences;

public class OrderActivity extends AppCompatActivity {
    ImageView imageViewFood;
    TextView textViewFoodName, textViewPriceFood, textViewDescriptionFood;

    private int quantity = 1;
    TextView txt_quantity;
    ImageButton btn_increase, btn_decrease;
    Food food;
    CartItem cartItem;
    List<CartItem> cartItemList;
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
        cartItemList = gson.fromJson(shareReferences.getData("cartItemList"), (Type) CartItem[].class);
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
            intent.putExtra("cartItemList", (Serializable) cartItemList);
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
            cartItemList.add(cartItem);
            shareReferences.saveData("cartItemList", gson.toJson(cartItemList));
            intent = new Intent(this, OrderAllCartActivity.class);
        } else {
            intent = new Intent(this, LoginRegisterActivity.class);
        }
        startActivity(intent);
    }

    private boolean checkLogin() {
        try {
            String user = shareReferences.getData("user");
            if (user.equals("")) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}