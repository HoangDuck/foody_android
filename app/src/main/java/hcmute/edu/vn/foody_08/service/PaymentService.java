package hcmute.edu.vn.foody_08.service;

import static hcmute.edu.vn.foody_08.ultil.constant.FOOD_ID;
import static hcmute.edu.vn.foody_08.ultil.constant.ORDER_DETAIL_ORDERID;
import static hcmute.edu.vn.foody_08.ultil.constant.ORDER_ID;
import static hcmute.edu.vn.foody_08.ultil.constant.ORDER_USERID;
import static hcmute.edu.vn.foody_08.ultil.constant.SHOP_ID;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import hcmute.edu.vn.foody_08.model.DAO.FoodDAO;
import hcmute.edu.vn.foody_08.model.DAO.OrderDAO;
import hcmute.edu.vn.foody_08.model.DAO.OrderDetailDAO;
import hcmute.edu.vn.foody_08.model.DAO.ShopDAO;
import hcmute.edu.vn.foody_08.model.DTO.response.PaymentDTO;
import hcmute.edu.vn.foody_08.model.DTO.response.PaymentFoodDTO;
import hcmute.edu.vn.foody_08.model.DTO.response.PaymentShopDTO;
import hcmute.edu.vn.foody_08.model.Food;
import hcmute.edu.vn.foody_08.model.Order;
import hcmute.edu.vn.foody_08.model.OrderDetail;
import hcmute.edu.vn.foody_08.model.Shop;

@RequiresApi(api = Build.VERSION_CODES.O)
public class PaymentService {
    private FoodDAO foodDAO;
    private ShopDAO shopDAO;
    private OrderDAO oderDAO;
    private OrderDetailDAO orderDetailDAO;

    public PaymentService(Context context) {
        shopDAO = new ShopDAO(context);
        oderDAO = new OrderDAO(context);
        foodDAO = new FoodDAO(context);
        orderDetailDAO = new OrderDetailDAO(context);

    }


    private PaymentDTO getPaymentByOrder(Order order){

        List<OrderDetail> orderDetailList = orderDetailDAO.getListOrderDetailsBy(ORDER_DETAIL_ORDERID,String.valueOf(order.getId()));
        List<PaymentShopDTO> shopList = new ArrayList<>();
        if(orderDetailList == null)
            return null;
        for(OrderDetail item : orderDetailList){
            for(Food fooditem : foodDAO.getListFoodsBy(FOOD_ID,String.valueOf(item.getIdFood()))){

                PaymentShopDTO paymentShopDTO = shopList.stream()
                        .filter(shopitem -> shopitem.getId() == fooditem.getShopId())
                        .findFirst().orElse(null);

                if(paymentShopDTO != null){
                    PaymentFoodDTO temp = new PaymentFoodDTO(fooditem,item.getNum());
                    paymentShopDTO.getPaymentFoodDTOList().add(temp);
                }
                else{
                    PaymentFoodDTO temp = new PaymentFoodDTO(fooditem,item.getNum());
                    Shop shop = shopDAO.getListShopsBy(SHOP_ID,String.valueOf(fooditem.getShopId())).stream().findFirst().get();
                    paymentShopDTO = new PaymentShopDTO(shop.getId(),shop.getName(),shop.getAddress());
                    paymentShopDTO.getPaymentFoodDTOList().add(temp);
                    shopList.add(paymentShopDTO);
                }
            }
        }
        PaymentDTO paymentDTO = new PaymentDTO(order.getAddress(),shopList,order.getPriceTotal(),order.getNumTotal());
        return paymentDTO;
    }

    public List<PaymentDTO> getPayments(int id){
        List<Order> orders = oderDAO.getListOrdersBy(ORDER_USERID,String.valueOf(id));
        if(orders == null)
            return null;
        else
            return orders.stream()
                    .map(order->getPaymentByOrder(order))
                    .collect(Collectors.toList());
    }
}
