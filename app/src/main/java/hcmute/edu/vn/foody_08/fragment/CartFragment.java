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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.model.Food;
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
    TextView textViewEmptyCart;

    List<Shop> listAllShop;
    List<Food> listAllFood;
    List<Shop> listShop;
    List<Food> listFood;

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
//        if(checkLogin()){
//            addControl(view);
//            getData();
//            setData();
//            addEvent(view);
//        }
        addControl(view);
        getData();
        setData();
        addEvent(view);

    }

    private boolean checkLogin() {
        ShareReferences shareReferences=ShareReferences.getInstance(getContext());
        try{
            String user=shareReferences.getData("user");
            if(user==""){
                Intent intent=new Intent(getContext(), LoginRegisterActivity.class);
                startActivity(intent);
                return false;
            }
        }catch (Exception e){
            Intent intent=new Intent(getContext(), LoginRegisterActivity.class);
            startActivity(intent);
            return false;
        }
        return true;
    }

    private void setData() {
        if(listShop.size()>0){
            listViewFoodCart.setVisibility(View.VISIBLE);
            textViewEmptyCart.setVisibility(View.INVISIBLE);
        }
        else {
            textViewEmptyCart.setVisibility(View.VISIBLE);
            //listViewFoodCart.setVisibility(View.INVISIBLE);
        }
    }

    private void getData() {
        //get shop list
        listAllShop=new ArrayList<>();
        ShopSevice shopSevice=new ShopSevice(getContext());
        listAllShop=shopSevice.getAllShops();
        //get all food list
        listAllFood=new ArrayList<>();
        FoodService foodService=new FoodService(getContext());
        listAllFood=foodService.getAllFoods();
        //get food list from shared preferences

        listFood=new ArrayList<>();
        listShop=new ArrayList<>();
        try {
            String listFoodIdString=shareReferences.getData("listFood");
            List<String> listFoodId;
            listFoodId= new ArrayList<>(Arrays.asList(listFoodIdString.split(",")));
            mappingListFood(listFoodId);
            mappingListShop(listFood);
            sortFoodListByIdShop();
        }catch (Exception e){

        }
    }

    private void mappingListShop(List<Food> listFood) {
        for(int i=0;i<listFood.size();i++){
            for(int j=0;j<listAllShop.size();j++){
                if(listFood.get(i).getShopId()==listAllShop.get(j).getId()){
                    listShop.add(listAllShop.get(j));
                }
            }
        }
    }

    private void mappingListFood(List<String> listFoodId) {
        for(int i=0;i<listFoodId.size();i++){
            for(int j=0;j<listAllFood.size();j++){
                if(Integer.parseInt(listFoodId.get(i))==listAllFood.get(j).getId()){
                    listFood.add(listAllFood.get(j));
                }
            }
        }
    }

    private void sortFoodListByIdShop(){
        Collections.sort(listFood, Food.FoodRollno);
    }


    private void addEvent(View view) {
        btn_payment.setOnClickListener(v -> {
            if(totalPrice>0){
                Intent intent=new Intent(view.getContext(), PaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControl(View view) {
        btn_payment=view.findViewById(R.id.btn_payment);
        listViewFoodCart=view.findViewById(R.id.listViewFoodRestaurant);
        textViewEmptyCart=view.findViewById(R.id.textViewEmptyCart);
    }
}