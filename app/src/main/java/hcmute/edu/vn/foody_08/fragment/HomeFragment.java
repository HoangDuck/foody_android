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
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.SearchActivity;
import hcmute.edu.vn.foody_08.adapter.TemAdapter;
import hcmute.edu.vn.foody_08.model.Shop;
import hcmute.edu.vn.foody_08.service.ShopSevice;
import hcmute.edu.vn.foody_08.view.OrderAllCartActivity;
import hcmute.edu.vn.foody_08.view.RestaurantActivity;

public class HomeFragment extends Fragment {
    List<Shop> listRestaurant;
    LinearLayout search_bar;
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
        listRestaurant = new ArrayList<>();
        getAllShopData();
        TemAdapter footItemAdapter = new TemAdapter(getActivity(), R.layout.activity_item_home, listRestaurant);
        GridView gridViewStore = view.findViewById(R.id.gridstore);
        gridViewStore.setAdapter(footItemAdapter);
        footItemAdapter.notifyDataSetChanged();
        setView(view);
        setOnclick(view);
    }

    private void getAllShopData() {
        ShopSevice shopSevice=new ShopSevice(getContext());
        listRestaurant= shopSevice.getAllShops();
    }

    protected void setView(View view){
        search_bar=view.findViewById(R.id.search_bar);
    }
    protected void setOnclick(View viewFragment){
        search_bar.setOnClickListener(this::openSearchView);
    }

    private void openSearchView(View viewFragment) {
        Intent intent=new Intent(viewFragment.getContext(),SearchActivity.class);
        startActivity(intent);
    }
}