package hcmute.edu.vn.foody_08.model.DAO;

import static hcmute.edu.vn.foody_08.ultil.constant.FOOD_DESCRIPTION;
import static hcmute.edu.vn.foody_08.ultil.constant.FOOD_ID;
import static hcmute.edu.vn.foody_08.ultil.constant.FOOD_IMAGE;
import static hcmute.edu.vn.foody_08.ultil.constant.FOOD_NAME;
import static hcmute.edu.vn.foody_08.ultil.constant.FOOD_PRICE;
import static hcmute.edu.vn.foody_08.ultil.constant.FOOD_SHOPID;
import static hcmute.edu.vn.foody_08.ultil.constant.FOOD_STATUS;
import static hcmute.edu.vn.foody_08.ultil.constant.ORDERL_NUM_TOTAL;
import static hcmute.edu.vn.foody_08.ultil.constant.ORDER_ADDRESS;
import static hcmute.edu.vn.foody_08.ultil.constant.ORDER_DATE_ORDER;
import static hcmute.edu.vn.foody_08.ultil.constant.ORDER_ID;
import static hcmute.edu.vn.foody_08.ultil.constant.ORDER_PRICETOTAL;
import static hcmute.edu.vn.foody_08.ultil.constant.ORDER_STATUS;
import static hcmute.edu.vn.foody_08.ultil.constant.ORDER_USERID;
import static hcmute.edu.vn.foody_08.ultil.constant.TABLE_FOOD;
import static hcmute.edu.vn.foody_08.ultil.constant.TABLE_ORDER;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hcmute.edu.vn.foody_08.data.DatabaseHelper;
import hcmute.edu.vn.foody_08.model.Order;
import hcmute.edu.vn.foody_08.model.Order;

@RequiresApi(api = Build.VERSION_CODES.O)
public class OrderDAO {
    private DatabaseHelper databaseHelper;

    public OrderDAO() {
        databaseHelper = DatabaseHelper.getInstance();
    }
    public OrderDAO(Context context) {
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    public long createOrder(Order order) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = getContentValuesOrder(order);

        try {
            long id = sqLiteDatabase.insertOrThrow(TABLE_ORDER, null, contentValues);
            if(id>0) {
                System.out.println("successful inserted order");
                return id;
            }
        } catch (SQLiteException e){
            System.out.println(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }
        return -1;
    }
    public List<Order> getListOrders(){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(TABLE_ORDER, null,null,null,
                    null, null, null);

            if(cursor!=null && cursor.moveToFirst()) {
                List<Order> OrderList = new ArrayList<>();
                do {
                    Order rs = getOrderFromCursor(cursor);
                    OrderList.add(rs);
                }
                while (cursor.moveToNext());
                return OrderList;
            }
            else
                return null;

        } catch (Exception e){
            return null;
        } finally {
            sqLiteDatabase.close();
            if(cursor!=null)
                cursor.close();
        }
    }


    public List<Order> getListOrdersBy(String key, String value){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(TABLE_ORDER, null,
                    key + " =?", new String[]{String.valueOf(value)},
                    null, null, null);

            if(cursor!=null && cursor.moveToFirst()) {
                List<Order> OrderList = new ArrayList<>();
                do {
                    Order rs = getOrderFromCursor(cursor);
                    OrderList.add(rs);
                }
                while (cursor.moveToNext());
                return OrderList;
            }
            else
                return null;

        } catch (Exception e){
            return null;
        } finally {
            sqLiteDatabase.close();
            if(cursor!=null)
                cursor.close();
        }
    }

    private ContentValues getContentValuesOrder(Order order){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ORDER_USERID, order.getIdUser());
        contentValues.put(ORDER_STATUS, order.getStatus());
        contentValues.put(ORDER_PRICETOTAL, order.getPriceTotal());
        contentValues.put(ORDER_DATE_ORDER, order.getDateOrder().toString());
        contentValues.put(ORDERL_NUM_TOTAL, order.getNumTotal());
        contentValues.put(ORDER_ADDRESS, order.getAddress());

        return contentValues;
    }


    @SuppressLint("Range")
    private Order getOrderFromCursor(Cursor cursor){
        int id =  cursor.getInt(cursor.getColumnIndex(ORDER_ID));
        int userId = cursor.getInt(cursor.getColumnIndex(ORDER_USERID));
        String status = cursor.getString(cursor.getColumnIndex(ORDER_STATUS));
        Double priceTotal = cursor.getDouble(cursor.getColumnIndex(ORDER_PRICETOTAL));
        LocalDateTime orderDate =LocalDateTime.parse(cursor.getString(cursor.getColumnIndex(ORDER_DATE_ORDER)));
        int numTotal = cursor.getInt(cursor.getColumnIndex(ORDERL_NUM_TOTAL));
        String address = cursor.getString(cursor.getColumnIndex(ORDER_ADDRESS));


        return new Order(id,userId,status,priceTotal,orderDate,numTotal,address);
    }
}
