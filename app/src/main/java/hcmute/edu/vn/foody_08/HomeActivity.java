package hcmute.edu.vn.foody_08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_08.view.OrderAllCartActivity;
import hcmute.edu.vn.foody_08.view.RestaurantActivity;

public class HomeActivity extends AppCompatActivity {
    List listthucan;
    ImageView imageSearch;
    ImageView imageFood;
    ImageView imageCart;
    ImageView imageStore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        GridView gridViewFood = findViewById(R.id.gridfood);
        listthucan = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            listthucan.add(i);
        TemAdapter footItemAdapter = new TemAdapter(this, R.layout.activity_item_home, listthucan);
        gridViewFood.setAdapter(footItemAdapter);
        footItemAdapter.notifyDataSetChanged();

        GridView gridViewStore = findViewById(R.id.gridstore);
        gridViewStore.setAdapter(footItemAdapter);
        footItemAdapter.notifyDataSetChanged();
        setView();
        setOnclick();
    }


    protected void setView(){
        imageSearch = findViewById(R.id.image_search);
        imageFood = findViewById(R.id.image_food);
        imageCart = findViewById(R.id.image_cart);
        imageStore = findViewById(R.id.image_store);
    }
    protected void setOnclick(){
        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearch();
            }
        });

        imageStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStore();
            }
        });

        imageFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFood();
            }
        });

        imageCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCart();
            }
        });
    }

    protected void openSearch(){
        Intent intent=new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
    protected void openStore(){
        Intent intent=new Intent(this, RestaurantActivity.class);
        startActivity(intent);
    }
    protected void openFood(){
        Intent intent=new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
    protected void openCart(){
        Intent intent=new Intent(this, OrderAllCartActivity.class);
        startActivity(intent);
    }

}