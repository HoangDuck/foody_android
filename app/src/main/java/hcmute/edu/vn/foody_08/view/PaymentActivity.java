package hcmute.edu.vn.foody_08.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.adapter.CartFoodPaymentAdapter;
import hcmute.edu.vn.foody_08.model.CartItem;
import hcmute.edu.vn.foody_08.model.OrderDetail;
import hcmute.edu.vn.foody_08.service.ShareReferences;

public class PaymentActivity extends AppCompatActivity {

    ListView listView;
    TextView address_to_receive, total_money;
    Intent intent;
    CartFoodPaymentAdapter cartFoodPaymentAdapter;
    ShareReferences shareReferences;

    List<CartItem> cartItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getData();
        addControl();
        addEvent();
        setData();
    }

    private void setData() {
        totalSumMoney();
    }

    private void getData() {
        intent = getIntent();
        cartItemList = (List<CartItem>) intent.getSerializableExtra("listCartFood");
        //lay dia chi nhan

    }

    private void addEvent() {
    }

    private void addControl() {
        listView = findViewById(R.id.listViewFoodCart);
        address_to_receive = findViewById(R.id.address_to_receive);
        total_money = findViewById(R.id.total_money);
        cartFoodPaymentAdapter = new CartFoodPaymentAdapter(this, R.layout.item_cart_payment, cartItemList);
        listView.setAdapter(cartFoodPaymentAdapter);
        shareReferences=ShareReferences.getInstance(this);
    }

    public void totalSumMoney() {
        int tempPriceTotal = 0;
        for (CartItem item : cartItemList
        ) {
            tempPriceTotal += item.getPrice() * item.getQuantity();
        }
        total_money.setText(Integer.toString(tempPriceTotal) + " VND");
    }

    public void onClickToPayment(View view) {
        clearCart(view);
        Intent intent = new Intent(this, PaymentResultActivity.class);
        startActivity(intent);
    }

    public void back(View view) {
        finish();
    }
    public void clearCart(View view) {
        cartItemList.clear();
        CartItem.cartItemList.clear();
        total_money.setText("0 VND");
        shareReferences.saveData("cartItemList","");
        cartFoodPaymentAdapter.notifyDataSetChanged();
    }
}