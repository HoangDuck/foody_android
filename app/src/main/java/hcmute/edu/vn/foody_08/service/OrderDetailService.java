package hcmute.edu.vn.foody_08.service;

import android.content.Context;

import java.util.List;

import hcmute.edu.vn.foody_08.model.DAO.FoodDAO;
import hcmute.edu.vn.foody_08.model.DAO.OrderDetailDAO;
import hcmute.edu.vn.foody_08.model.OrderDetail;

public class OrderDetailService {
    private OrderDetailDAO oderDetailDAO;
    public OrderDetailService(Context context){
        oderDetailDAO = new OrderDetailDAO(context);
    }

    public List<OrderDetail> getAllOrderDetails(){
        return oderDetailDAO.getListOrderDetails();
    }

    public List<OrderDetail> getAllOrderDetailsBy(String key, String value){
        return oderDetailDAO.getListOrderDetailsBy(key,value);
    }

    public List<OrderDetail> getAllOrderDetailsByIdOrder(int id){
        return oderDetailDAO.getListOrderDetailsBy("idOrder",String.valueOf(id));
    }

    public void createOrderDetail(OrderDetail orderDetail){
        oderDetailDAO.createOrderDetail(orderDetail);
    }
    public void createListOrderDetail(List<OrderDetail> orderDetails){
        for (OrderDetail item: orderDetails
             ) {
            oderDetailDAO.createOrderDetail(item);
        }

    }
}
