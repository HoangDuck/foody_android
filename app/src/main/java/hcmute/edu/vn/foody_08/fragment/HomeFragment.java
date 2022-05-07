package hcmute.edu.vn.foody_08.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.SearchActivity;
import hcmute.edu.vn.foody_08.TemAdapter;
import hcmute.edu.vn.foody_08.view.OrderAllCartActivity;
import hcmute.edu.vn.foody_08.view.RestaurantActivity;

public class HomeFragment extends Fragment {
    List listthucan;
    ImageView imageSearch;
    ImageView imageFood;
    ImageView imageCart;
    ImageView imageStore;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GridView gridViewFood = view.findViewById(R.id.gridfood);
        listthucan = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            listthucan.add(i);
        TemAdapter footItemAdapter = new TemAdapter(getActivity(), R.layout.activity_item_home, listthucan);
        gridViewFood.setAdapter(footItemAdapter);
        footItemAdapter.notifyDataSetChanged();

        GridView gridViewStore = view.findViewById(R.id.gridstore);
        gridViewStore.setAdapter(footItemAdapter);
        footItemAdapter.notifyDataSetChanged();
        setView(view);
        setOnclick();
    }
    protected void setView(View view){
        imageSearch = view.findViewById(R.id.image_search);
        //imageFood = view.findViewById(R.id.image_food);
        //imageCart = view.findViewById(R.id.image_cart);
        //imageStore = view.findViewById(R.id.image_store);
    }
    protected void setOnclick(){
        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearch(view);
            }
        });

//        imageStore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openStore(view);
//            }
//        });

//        imageFood.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openFood(view);
//            }
//        });

//        imageCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openCart(view);
//            }
//        });
    }

    protected void openSearch(View view){
        Intent intent=new Intent(view.getContext(), SearchActivity.class);
        startActivity(intent);
    }
    protected void openStore(View view){
        Intent intent=new Intent(view.getContext(), RestaurantActivity.class);
        startActivity(intent);
    }
    protected void openFood(View view){
        Intent intent=new Intent(view.getContext(), SearchActivity.class);
        startActivity(intent);
    }
    protected void openCart(View view){
        Intent intent=new Intent(view.getContext(), OrderAllCartActivity.class);
        startActivity(intent);
    }
}