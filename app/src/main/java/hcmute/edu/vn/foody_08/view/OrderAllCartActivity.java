package hcmute.edu.vn.foody_08.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.adapter.CartFoodAdapter;
import hcmute.edu.vn.foody_08.model.CartItem;
import hcmute.edu.vn.foody_08.model.Shop;
import hcmute.edu.vn.foody_08.service.ShareReferences;
import hcmute.edu.vn.foody_08.service.ShopSevice;

public class OrderAllCartActivity extends AppCompatActivity {
    Button btn_payment;
    ListView listViewFoodCart;
    TextView txt_restaurant_name,txt_restaurant_address;
    TextView total_money;

    List<CartItem> cartItemList;
    List<Shop> shopList;
    CartFoodAdapter cartFoodAdapter;
    Shop shop;
    ShareReferences shareReferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_all_cart);
        addControl();
        addEvent();
        setData();
    }

    private void setData() {
        txt_restaurant_name.setText(shop.getName());
        txt_restaurant_address.setText("Địa chỉ: "+shop.getAddress());
        totalSumMoney();
    }

    public void totalSumMoney(){
        int tempPriceTotal = 0;
        for (CartItem item: cartItemList
             ) {
            tempPriceTotal+=item.getPrice()*item.getQuantity();
        }
        total_money.setText(Integer.toString(tempPriceTotal)+" VND");
    }

    private void addEvent() {
    }

    private void addControl() {
        shareReferences = ShareReferences.getInstance(this);
        btn_payment=findViewById(R.id.btn_payment);
        listViewFoodCart=findViewById(R.id.listViewFoodCart);
        txt_restaurant_address=findViewById(R.id.txt_restaurant_address);
        txt_restaurant_name=findViewById(R.id.txt_restaurant_name);
        total_money=findViewById(R.id.total_money);
        cartItemList=new ArrayList<>();
        cartItemList=CartItem.cartItemList;
        cartFoodAdapter=new CartFoodAdapter(this,R.layout.item_cart_food, cartItemList);
        listViewFoodCart.setAdapter(cartFoodAdapter);
        shopList=new ArrayList<>();
        ShopSevice shopSevice=new ShopSevice(this);
        shopList=shopSevice.getAllShops();
        findShopObject();
    }

    private void findShopObject() {
        for (Shop shop: shopList
             ) {
            if(shop.getId()==CartItem.cartItemList.get(0).getShopId()){
                this.shop=shop;
                return;
            }
        }
    }

    public void paymentYourCart(View view) {
        Intent intent=new Intent(OrderAllCartActivity.this,PaymentActivity.class);
        intent.putExtra("listCartFood", (Serializable) cartItemList);
        startActivity(intent);
    }

    public void closePagePaymentCart(View view) {
        finish();
    }

    public void clearCart(View view) {
        cartItemList.clear();
        CartItem.cartItemList.clear();
        txt_restaurant_name.setText("Giỏ hàng của bạn đang trống");
        txt_restaurant_address.setText("");
        total_money.setText("0 VND");
        shareReferences.saveData("cartItemList","");
        cartFoodAdapter.notifyDataSetChanged();
    }
}