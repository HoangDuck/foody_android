package hcmute.edu.vn.foody_08;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hcmute.edu.vn.foody_08.fragment.AccountFragment;
import hcmute.edu.vn.foody_08.fragment.CartFragment;
import hcmute.edu.vn.foody_08.fragment.HomeFragment;
import hcmute.edu.vn.foody_08.fragment.OrderFragment;
import hcmute.edu.vn.foody_08.model.CartItem;
import hcmute.edu.vn.foody_08.model.DAO.UserDAO;
import hcmute.edu.vn.foody_08.model.DTO.payload.LoginDTO;
import hcmute.edu.vn.foody_08.model.Food;
import hcmute.edu.vn.foody_08.model.Order;
import hcmute.edu.vn.foody_08.model.OrderDetail;
import hcmute.edu.vn.foody_08.model.Shop;
import hcmute.edu.vn.foody_08.model.User;
import hcmute.edu.vn.foody_08.service.FoodService;
import hcmute.edu.vn.foody_08.service.OrderDetailService;
import hcmute.edu.vn.foody_08.service.OrderService;
import hcmute.edu.vn.foody_08.service.PaymentService;
import hcmute.edu.vn.foody_08.service.ShareReferences;
import hcmute.edu.vn.foody_08.service.ShopSevice;
import hcmute.edu.vn.foody_08.service.UserService;
import hcmute.edu.vn.foody_08.view.LoginRegisterActivity;
import hcmute.edu.vn.foody_08.view.OrderActivity;
import hcmute.edu.vn.foody_08.view.OrderAllCartActivity;
import hcmute.edu.vn.foody_08.view.PaymentActivity;
import hcmute.edu.vn.foody_08.view.RestaurantActivity;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigation;
    private ShopSevice shopSevice = new ShopSevice(this);
    private FoodService foodService = new FoodService(this);
    private OrderService orderService = new OrderService(this);
    private OrderDetailService orderDetailService = new OrderDetailService(this);
    private UserService userService = new UserService(this);
    private PaymentService paymentService = new PaymentService(this);

    private NavigationBarView.OnItemSelectedListener navListener = item -> {
        Fragment selectedFragment;
        System.out.println(item.getItemId());
        switch (item.getItemId()) {
            case R.id.home: {
                selectedFragment = new HomeFragment();
                break;
            }
            case R.id.cart: {
                selectedFragment=new CartFragment();
                break;
            }
            case R.id.order: {
                selectedFragment=new OrderFragment();
                break;
            }
            case R.id.account: {
                selectedFragment = new AccountFragment();
                break;
            }
            default:
                selectedFragment = new AccountFragment();
                break;

        }
        System.out.println(selectedFragment);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flFragment, selectedFragment).commit();
        return true;
    };


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setOnItemSelectedListener(navListener);
        bottomNavigation.setSelectedItemId(R.id.home);
        System.out.println("==================================================================================================================");

//        Shop shop = new Shop("apple", "url", "des", "address", "status", 20.0, LocalTime.now(), LocalTime.now(), "phone");
//        Shop shop2 = new Shop("apple2", "url", "des", "address", "status", 20.0, LocalTime.now(), LocalTime.now(), "phone");
//        shopSevice.createShop(shop);
//        shopSevice.createShop(shop2);
//        shopSevice.getAllShops();
//        Food food = new Food("bún bò huê", "url", 1, "ngon lắm", 20000.0, "còn nhiều");
//        Food food2 = new Food("Hủ tíu", "url", 2, "ngon lắm", 20000.0, "còn nhiều");
//        foodService.createFood(food);
//        foodService.createFood(food2);
//        Order order = new Order(1, "mới mua", 180000.0, LocalDateTime.now(), 10, "tam hà");
//        User user = new User("String name", "String avatar", "String email", "String password", "String address", "String gender", " String phone");
//        userService.createUser(user);
//        int idorder = orderService.createOrder(order);
//        List<OrderDetail> list = new ArrayList<>();
//        OrderDetail orderDetail = new OrderDetail(idorder, 1, 10, 1000.0);
//        OrderDetail orderDetail2 = new OrderDetail(idorder, 2, 5, 5000.0);
//        OrderDetail orderDetail3 = new OrderDetail(idorder, 2, 6, 6000.0);
//        OrderDetail orderDetail4 = new OrderDetail(idorder, 1, 1, 1000.0);
//        list.add(orderDetail);
//        list.add(orderDetail2);
//        list.add(orderDetail3);
//        list.add(orderDetail4);
//        orderDetailService.createListOrderDetail(list);
        User user = userService.Login(new LoginDTO("String email", "String password"));
        ShareReferences shareReferences = ShareReferences.getInstance(this);
        //shareReferences.SaveGlobalUser(user);
        Gson gson=new Gson();
        Type listType = new TypeToken<ArrayList<CartItem>>(){}.getType();
        CartItem.cartItemList = gson.fromJson(shareReferences.getData("cartItemList"), listType);
        if(CartItem.cartItemList==null){
            CartItem.cartItemList=new ArrayList<>();
        }
        System.out.println("========================================================================================================");

    }

    private void test() {

    }

}