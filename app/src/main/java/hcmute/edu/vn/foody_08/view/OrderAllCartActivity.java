package hcmute.edu.vn.foody_08.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.service.ShareReferences;

public class OrderAllCartActivity extends AppCompatActivity {
    Button btn_payment;
    ListView listViewFoodCart;
    TextView txt_restaurant_name,txt_restaurant_address;
    TextView total_money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_all_cart);
        addControl();
    }

    private void addControl() {
        btn_payment=findViewById(R.id.btn_payment);
        listViewFoodCart=findViewById(R.id.listViewFoodRestaurant);
        txt_restaurant_address=findViewById(R.id.txt_restaurant_address);
        txt_restaurant_name=findViewById(R.id.txt_restaurant_name);
        total_money=findViewById(R.id.total_money);
    }

//    private boolean checkLogin() {
//        ShareReferences shareReferences=ShareReferences.getInstance(this);
//        try{
//            String user=shareReferences.getData("user");
//            if(user==""){
//                Intent intent=new Intent(this, LoginRegisterActivity.class);
//                startActivity(intent);
//                return false;
//            }
//        }catch (Exception e){
//            Intent intent=new Intent(this, LoginRegisterActivity.class);
//            startActivity(intent);
//            return false;
//        }
//        return true;
//    }

    public void paymentYourCart(View view) {
        Intent intent=new Intent(OrderAllCartActivity.this,PaymentActivity.class);
        startActivity(intent);
        //truyền nhận trả dữ liệu qua json sử dụng intent string ấy
    }

    public void closePagePaymentCart(View view) {
        finish();
    }
}