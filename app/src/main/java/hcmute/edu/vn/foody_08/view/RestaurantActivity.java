package hcmute.edu.vn.foody_08.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.SearchActivity;

public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
    }

    public void closeRestaurantPage(View view) {
        finish();
    }

    public void goToYourCart(View view) {
        Intent intent=new Intent(this,OrderAllCartActivity.class);
        //truyền đi và nhận lại dữ liệu qua intent dạng json
        startActivity(intent);

    }

    public void openSearchView(View view) {
        Intent intent=new Intent(RestaurantActivity.this, SearchActivity.class);
        startActivity(intent);
    }
}