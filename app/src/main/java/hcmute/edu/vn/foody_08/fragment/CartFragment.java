package hcmute.edu.vn.foody_08.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.adapter.CartFoodAdapter;
import hcmute.edu.vn.foody_08.model.Food;
import hcmute.edu.vn.foody_08.model.OrderDetail;
import hcmute.edu.vn.foody_08.model.Shop;
import hcmute.edu.vn.foody_08.service.FoodService;
import hcmute.edu.vn.foody_08.service.ShareReferences;
import hcmute.edu.vn.foody_08.service.ShopSevice;
import hcmute.edu.vn.foody_08.view.LoginRegisterActivity;
import hcmute.edu.vn.foody_08.view.PaymentActivity;
import hcmute.edu.vn.foody_08.view.PaymentResultActivity;


public class CartFragment extends Fragment {
    Button btn_payment;
    ListView listViewFoodCart;
    TextView txt_restaurant_name,txt_restaurant_address;
    TextView total_money;
    List<OrderDetail> listOrderDetail;
    List<Shop> shopList;
    Shop shop;
    Gson gson;

    int totalPrice=0;

    ShareReferences shareReferences=ShareReferences.getInstance(getContext());
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addControl(view);
        addEvent(view);
        if(checkLogin()){
            getData();
            setData();
        }
        else {
            Intent intent=new Intent(getContext(), LoginRegisterActivity.class);
            startActivity(intent);
        }
    }

    private boolean checkLogin() {
        try{
            String user=shareReferences.getData("user");
            if(user.equals("")){
                return false;
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    private void setData() {
        CartFoodAdapter cartFoodAdapter=new CartFoodAdapter(getActivity(), R.layout.item_cart_food,listOrderDetail);
        listViewFoodCart.setAdapter(cartFoodAdapter);
        txt_restaurant_name.setText(shop.getName());
        txt_restaurant_address.setText(shop.getAddress());
    }

    private void getData() {
        gson=new Gson();
        listOrderDetail=new ArrayList<>();
        //get list order detail
        shopList=new ArrayList<>();
        ShopSevice shopSevice=new ShopSevice(getContext());
        shopList=shopSevice.getAllShops();
        shop=shopList.get(0);
    }


    private void addEvent(View view) {
        btn_payment.setOnClickListener(v -> {
            if(totalPrice>0){
                Intent intent=new Intent(view.getContext(), PaymentActivity.class);
                intent.putExtra("listOrderDetails", gson.toJson(listOrderDetail));
                startActivity(intent);
            }
        });
        if(totalPrice==0){
            txt_restaurant_name.setText("Giỏ hàng của bạn đang trống");
            txt_restaurant_address.setForegroundGravity(17);
            txt_restaurant_address.setText("");
        }
    }

    private void addControl(View view) {
        btn_payment=view.findViewById(R.id.btn_payment);
        listViewFoodCart=view.findViewById(R.id.listViewFoodRestaurant);
        txt_restaurant_address=view.findViewById(R.id.txt_restaurant_address);
        txt_restaurant_name=view.findViewById(R.id.txt_restaurant_name);
        total_money=view.findViewById(R.id.total_money);
    }
}