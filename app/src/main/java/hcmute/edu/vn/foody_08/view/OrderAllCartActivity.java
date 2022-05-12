package hcmute.edu.vn.foody_08.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.service.ShareReferences;

public class OrderAllCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_all_cart);
        checkLogin();
    }

    private boolean checkLogin() {
        ShareReferences shareReferences=ShareReferences.getInstance(this);
        try{
            String user=shareReferences.getData("user");
            if(user==""){
                Intent intent=new Intent(this, LoginRegisterActivity.class);
                startActivity(intent);
                return false;
            }
        }catch (Exception e){
            Intent intent=new Intent(this, LoginRegisterActivity.class);
            startActivity(intent);
            return false;
        }
        return true;
    }

    public void paymentYourCart(View view) {
        Intent intent=new Intent(OrderAllCartActivity.this,PaymentActivity.class);
        startActivity(intent);
        //truyền nhận trả dữ liệu qua json sử dụng intent string ấy
    }

    public void closePagePaymentCart(View view) {
        finish();
    }
}