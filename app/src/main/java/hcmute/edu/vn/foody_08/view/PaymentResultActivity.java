package hcmute.edu.vn.foody_08.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hcmute.edu.vn.foody_08.MainActivity;
import hcmute.edu.vn.foody_08.R;

public class PaymentResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_result_success);
    }


    public void goBackHomePage(View view) {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}