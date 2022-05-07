package hcmute.edu.vn.foody_08.model.DAO;



import static hcmute.edu.vn.foody_08.ultil.constant.ORDER_DETAIL_FOODID;
import static hcmute.edu.vn.foody_08.ultil.constant.ORDER_DETAIL_ID;
import static hcmute.edu.vn.foody_08.ultil.constant.ORDER_DETAIL_NUM;
import static hcmute.edu.vn.foody_08.ultil.constant.ORDER_DETAIL_ORDERID;
import static hcmute.edu.vn.foody_08.ultil.constant.ORDER_DETAIL_PRICE_EACH;
import static hcmute.edu.vn.foody_08.ultil.constant.TABLE_ORDER_DETAIL;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;


import hcmute.edu.vn.foody_08.data.DatabaseHelper;
import hcmute.edu.vn.foody_08.model.OrderDetail;


public class OrderDetailDAO {
    private DatabaseHelper databaseHelper;

    public OrderDetailDAO() {
        databaseHelper = DatabaseHelper.getInstance();
    }
    public OrderDetailDAO(Context context) {
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    public long createOrderDetail(OrderDetail food) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = getContentValuesOrderDetail(food);

        try {
            long id = sqLiteDatabase.insertOrThrow(TABLE_ORDER_DETAIL, null, contentValues);
            if(id>0) {
                System.out.println("successful inserted order detail");
                return id;
            }
        } catch (SQLiteException e){
            System.out.println(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }
        return 0;
    }
    public List<OrderDetail> getListOrderDetails(){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(TABLE_ORDER_DETAIL, null,null,null,
                    null, null, null);

            if(cursor!=null && cursor.moveToFirst()) {
                List<OrderDetail> OrderDetailList = new ArrayList<>();
                do {
                    OrderDetail rs = getOrderDetailFromCursor(cursor);
                    OrderDetailList.add(rs);
                }
                while (cursor.moveToNext());
                return OrderDetailList;
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


    public List<OrderDetail> getListOrderDetailsBy(String key, String value){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(TABLE_ORDER_DETAIL, null,
                    key + " =?", new String[]{String.valueOf(value)},
                    null, null, null);

            if(cursor!=null && cursor.moveToFirst()) {
                List<OrderDetail> OrderDetailList = new ArrayList<>();
                do {
                    OrderDetail rs = getOrderDetailFromCursor(cursor);
                    OrderDetailList.add(rs);
                }
                while (cursor.moveToNext());
                return OrderDetailList;
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

    private ContentValues getContentValuesOrderDetail(OrderDetail orderDetail){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ORDER_DETAIL_FOODID, orderDetail.getIdFood());
        contentValues.put(ORDER_DETAIL_ORDERID, orderDetail.getIdOrder());
        contentValues.put(ORDER_DETAIL_NUM, orderDetail.getNum());
        contentValues.put(ORDER_DETAIL_PRICE_EACH, orderDetail.getPriceEach());

        return contentValues;
    }

    @SuppressLint("Range")
    private OrderDetail getOrderDetailFromCursor(Cursor cursor){
        int id =  cursor.getInt(cursor.getColumnIndex(ORDER_DETAIL_ID));
        int idOrder =  cursor.getInt(cursor.getColumnIndex(ORDER_DETAIL_ORDERID));
        int idFood =  cursor.getInt(cursor.getColumnIndex(ORDER_DETAIL_FOODID));
        int num =  cursor.getInt(cursor.getColumnIndex(ORDER_DETAIL_NUM));
        Double priceEach = cursor.getDouble(cursor.getColumnIndex(ORDER_DETAIL_PRICE_EACH));

        return new OrderDetail(id,idOrder,idFood,num,priceEach);
    }
}
