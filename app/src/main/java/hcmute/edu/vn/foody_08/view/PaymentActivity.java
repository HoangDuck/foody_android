package hcmute.edu.vn.foody_08.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hcmute.edu.vn.foody_08.R;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }

    public void onClickToPayment(View view) {
        Intent intent=new Intent(this,PaymentResultActivity.class);
        startActivity(intent);
    }

    public void payment(View view) {
        Intent intent=new Intent(PaymentActivity.this,PaymentResultActivity.class);
        startActivity(intent);
    }

    public void back(View view) {
        finish();
    }
}