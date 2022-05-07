package hcmute.edu.vn.foody_08.service;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;

import hcmute.edu.vn.foody_08.model.DAO.OrderDAO;
import hcmute.edu.vn.foody_08.model.Order;

@RequiresApi(api = Build.VERSION_CODES.O)
public class OrderService {
    private OrderDAO oderDAO;

    public OrderService(Context context){
        oderDAO = new OrderDAO(context);
    }

    public List<Order> getAllOrders(){
        return oderDAO.getListOrders();
    }

    public List<Order> getAllOrdersBy(String key, String value){
        return oderDAO.getListOrdersBy(key,value);
    }

    public List<Order> getAllOrdersByIdUser(int id){
        return oderDAO.getListOrdersBy("idUser",String.valueOf(id));
    }

    public int createOrder(Order Order){
        return (int)oderDAO.createOrder(Order);
    }
}
