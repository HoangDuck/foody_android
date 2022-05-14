package hcmute.edu.vn.foody_08.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.model.OrderDetail;

public class PaymentActivity extends AppCompatActivity {

    List<OrderDetail> orderDetailList;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        addControl();
        addEvent();
        getData();
        setData();
    }

    private void setData() {
        intent=getIntent();
        Object data=intent.getSerializableExtra("listOrderDetails");
    }

    private void getData() {
    }

    private void addEvent() {
    }

    private void addControl() {
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