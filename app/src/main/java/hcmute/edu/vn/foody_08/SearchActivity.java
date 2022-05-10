package hcmute.edu.vn.foody_08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_08.adapter.FoodAddOrderAdapter;
import hcmute.edu.vn.foody_08.adapter.TemAdapter;
import hcmute.edu.vn.foody_08.model.Food;
import hcmute.edu.vn.foody_08.model.Shop;
import hcmute.edu.vn.foody_08.service.FoodService;
import hcmute.edu.vn.foody_08.service.ShopSevice;

public class SearchActivity extends AppCompatActivity {
    List<Food> listFood;
    GridView gridView;
    ImageView btn_search;
    EditText editTextSearch;
    Boolean editTextVisible=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        addControl();
        addEvent();
        listFood = new ArrayList<>();
        getAllFoodData();
        FoodAddOrderAdapter footItemAdapter = new FoodAddOrderAdapter(this, R.layout.activity_item_search,listFood);
        gridView.setAdapter(footItemAdapter);
        footItemAdapter.notifyDataSetChanged();
    }

    private void addEvent() {
        btn_search.setOnClickListener(v -> {
            if(editTextVisible)
                editTextSearch.setVisibility(View.INVISIBLE);
            else
                editTextSearch.setVisibility(View.VISIBLE);
            editTextVisible=!editTextVisible;
        });
    }

    private void addControl() {
        gridView = findViewById(R.id.grid);
        btn_search=findViewById(R.id.btn_search);
        editTextSearch=findViewById(R.id.editTextSearch);
    }

    private void getAllFoodData() {
        FoodService foodService=new FoodService(this);
        listFood= foodService.getAllFoods();
    }

    public void back(View view) {
        finish();
    }
}