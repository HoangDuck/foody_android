package hcmute.edu.vn.foody_08.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hcmute.edu.vn.foody_08.R;

public class PaymentResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_result_success);
    }

    public void closeResultPage(View view) {
        //Call to homepage
        Intent intent=new Intent();
        startActivity(intent);
    }

    public void goBackHomePage(View view) {
        //Call to homepage
        Intent intent=new Intent();
        startActivity(intent);
    }
}