package hcmute.edu.vn.foody_08.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.adapter.OrderAdapter;
import hcmute.edu.vn.foody_08.model.Order;
import hcmute.edu.vn.foody_08.model.User;
import hcmute.edu.vn.foody_08.service.OrderService;
import hcmute.edu.vn.foody_08.service.ShareReferences;
import hcmute.edu.vn.foody_08.view.LoginRegisterActivity;

public class OrderFragment extends Fragment {
    ShareReferences shareReferences;
    List<Order> orderList;
    OrderService orderService;
    ListView listViewOrder;
    OrderAdapter orderAdapter;
    public OrderFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shareReferences=ShareReferences.getInstance(this.getContext());
        addControl(view);
        addEvent(view);
        if(/*checkLogin()*/true){
            getData();
            setData();
        }
        else {
            Intent intent=new Intent(getContext(), LoginRegisterActivity.class);
            startActivity(intent);
        }
    }

    private void setData() {
        orderAdapter=new OrderAdapter(getActivity(),R.layout.item_order,orderList);
        listViewOrder.setAdapter(orderAdapter);
    }

    private void getData() {
        orderList=new ArrayList<>();
        orderService=new OrderService(this.getContext());
        orderList=orderService.getAllOrders();
        if(orderList==null){
            orderList=new ArrayList<>();
        }

    }

    private void addEvent(View view) {

    }

    private void addControl(View view) {
        listViewOrder=view.findViewById(R.id.listViewOrder);
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
}