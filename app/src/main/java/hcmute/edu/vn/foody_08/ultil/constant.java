package hcmute.edu.vn.foody_08.ultil;

import java.time.LocalTime;

public class constant {

    //User
    public static final String TABLE_USER = "Users";
    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";
    public static final String USER_EMAIL = "email";
    public static final String USER_AVATAR = "avatar";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ADDRESS = "address";
    public static final String USER_GENDER= "gender";
    public static final String USER_PHONE = "phone";

    //Shop

    public static final String TABLE_SHOP = "Shops";
    public static final String SHOP_ID = "id";
    public static final String SHOP_NAME = "name";
    public static final String SHOP_IMAGE = "image";
    public static final String SHOP_DESCRIPTION = "description";
    public static final String SHOP_ADDRESS = "address";
    public static final String SHOP_STATUS = "status";
    public static final String SHOP_RATING = "rating";
    public static final String SHOP_OPEN_TIME = "openTime";
    public static final String SHOP_CLOSE_TIME = "closeTime";
    public static final String SHOP_PHONE = "phoneNumber";


    //Foods
    public static final String TABLE_FOOD = "Foods";
    public static final String FOOD_ID = "id";
    public static final String FOOD_NAME = "name";
    public static final String FOOD_IMAGE = "image";
    public static final String FOOD_SHOPID = "shopId";
    public static final String FOOD_DESCRIPTION = "description";
    public static final String FOOD_PRICE = "price";
    public static final String FOOD_STATUS = "status";

    //Order Detail
    public static final String TABLE_ORDER_DETAIL = "OrderDetails";
    public static final String ORDER_DETAIL_ID="id";
    public static final String ORDER_DETAIL_ORDERID="idOrder";
    public static final String ORDER_DETAIL_FOODID="idFood";
    public static final String ORDER_DETAIL_NUM = "num";
    public static final String ORDER_DETAIL_PRICE_EACH = "priceEach";

    //Order
    public static final String TABLE_ORDER = "Orders";
    public static final String ORDER_ID="id";
    public static final String ORDER_USERID="idUser";
    public static final String ORDER_STATUS="status";
    public static final String ORDER_PRICETOTAL = "priceTotal";
    public static final String ORDER_DATE_ORDER = "dateOrder";
    public static final String ORDERL_NUM_TOTAL = "numTotal";
    public static final String ORDER_ADDRESS= "address";

    //Shared References
    //User Global
    public static final String GLOBAL_USER_ID = "id";
    public static final String GLOBAL_USER_NAME = "name";
    public static final String GLOBAL_USER_EMAIL = "email";
    public static final String GLOBAL_USER_PASSWORD = "password";
    public static final String GLOBAL_USER_AVATAR = "avatar";
    public static final String GLOBAL_USER_ADDRESS = "address";
    public static final String GLOBAL_USER_GENDER= "gender";
    public static final String GLOBAL_USER_PHONE = "phone";
}
