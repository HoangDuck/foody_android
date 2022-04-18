package hcmute.edu.vn.foody_08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hcmute.edu.vn.foody_08.view.LoginRegisterActivity;
import hcmute.edu.vn.foody_08.view.OrderActivity;
import hcmute.edu.vn.foody_08.view.OrderAllCartActivity;
import hcmute.edu.vn.foody_08.view.PaymentActivity;
import hcmute.edu.vn.foody_08.view.RestaurantActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openPage(View view) {
        Intent intent=new Intent(MainActivity.this, LoginRegisterActivity.class);
        startActivity(intent);
    }
}