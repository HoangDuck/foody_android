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

import com.squareup.picasso.Picasso;

import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.model.Food;

public class OrderActivity extends AppCompatActivity {
    ImageView imageViewFood;
    TextView textViewFoodName,textViewPriceFood,textViewDescriptionFood;

    private int quantity=1;
    TextView txt_quantity;
    ImageButton btn_increase, btn_decrease;
    Food food;
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
        Intent intent=getIntent();
        food= (Food) intent.getSerializableExtra("food");
    }

    private void addEvent() {
        btn_increase.setOnClickListener(view -> {
            quantity++;
            txt_quantity.setText(Integer.toString(quantity));
        });
        btn_decrease.setOnClickListener(view -> {
            if(quantity!=1){
                quantity--;
                txt_quantity.setText(Integer.toString(quantity));
            }
        });
    }

    private void addControl() {
        txt_quantity=findViewById(R.id.txt_quantity);
        btn_increase=findViewById(R.id.btn_increase_quantity);
        btn_decrease=findViewById(R.id.btn_decrease_quantity);
        imageViewFood=findViewById(R.id.imageViewFood);
        textViewFoodName=findViewById(R.id.textViewFoodName);
        textViewPriceFood=findViewById(R.id.textViewPriceFood);
        textViewDescriptionFood=findViewById(R.id.textViewDescriptionFood);
    }

    public void goToYourCart(View view) {
        Intent intent=new Intent(this,OrderAllCartActivity.class);
        //truyền đi và nhận lại dữ liệu qua intent dạng json
        startActivity(intent);
    }

    public void closeOrderPage(View view) {
        finish();
    }

    public void addToCartAndFinish(View view) {
        finish();
    }
}