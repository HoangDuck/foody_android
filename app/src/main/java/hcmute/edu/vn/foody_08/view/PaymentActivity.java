package hcmute.edu.vn.foody_08.view;

import static hcmute.edu.vn.foody_08.ultil.constant.GLOBAL_USER_ADDRESS;
import static hcmute.edu.vn.foody_08.ultil.constant.GLOBAL_USER_ID;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.util.List;

import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.adapter.CartFoodPaymentAdapter;
import hcmute.edu.vn.foody_08.model.CartItem;
import hcmute.edu.vn.foody_08.model.Order;
import hcmute.edu.vn.foody_08.model.OrderDetail;
import hcmute.edu.vn.foody_08.service.OrderDetailService;
import hcmute.edu.vn.foody_08.service.OrderService;
import hcmute.edu.vn.foody_08.service.ShareReferences;

public class PaymentActivity extends AppCompatActivity {

    ListView listView;
    TextView address_to_receive, total_money;
    Intent intent;
    CartFoodPaymentAdapter cartFoodPaymentAdapter;
    ShareReferences shareReferences;

    List<CartItem> cartItemList;
    String addressUser;
    OrderService orderService;
    OrderDetailService orderDetailService;
    int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        shareReferences = ShareReferences.getInstance(this);
        getData();
        addControl();
        addEvent();
        setData();
    }

    private void setData() {
        address_to_receive.setText("Địa chỉ: " + addressUser);
        totalSumMoney();
    }

    private void getData() {
        intent = getIntent();
        cartItemList = (List<CartItem>) intent.getSerializableExtra("listCartFood");
        addressUser = shareReferences.getData(GLOBAL_USER_ADDRESS);
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
        orderService = new OrderService(this);
        orderDetailService = new OrderDetailService(this);
        shareReferences = ShareReferences.getInstance(this);
    }

    public void totalSumMoney() {
        int totalPrice = 0;
        for (CartItem item : cartItemList
        ) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        total_money.setText(Integer.toString(totalPrice) + " VND");
    }

    public void onClickToPayment(View view) {
        addFoodCartToDB();
        clearCart(view);
        Intent intent = new Intent(this, PaymentResultActivity.class);
        startActivity(intent);
    }

    private void addFoodCartToDB() {
        Order order = new Order(Integer.parseInt(shareReferences.getData(GLOBAL_USER_ID)), "Đã thanh toán", (double) totalPrice, LocalDateTime.now(), 10, shareReferences.getData(GLOBAL_USER_ADDRESS));
        int tempIdOrder = orderService.createOrder(order);
        int listCartItemLength=cartItemList.size();
        for(int i=0;i<listCartItemLength;i++){
            OrderDetail orderDetail=new OrderDetail(tempIdOrder,cartItemList.get(i).getId(),cartItemList.get(i).getQuantity(),cartItemList.get(i).getPrice());
            orderDetailService.createOrderDetail(orderDetail);
        }
    }

    public void back(View view) {
        finish();
    }

    public void clearCart(View view) {
        cartItemList.clear();
        CartItem.cartItemList.clear();
        total_money.setText("0 VND");
        shareReferences.saveData("cartItemList", "");
        cartFoodPaymentAdapter.notifyDataSetChanged();
    }
}