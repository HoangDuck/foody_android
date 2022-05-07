package hcmute.edu.vn.foody_08.model.DAO;

import static hcmute.edu.vn.foody_08.ultil.constant.SHOP_ADDRESS;
import static hcmute.edu.vn.foody_08.ultil.constant.SHOP_CLOSE_TIME;
import static hcmute.edu.vn.foody_08.ultil.constant.SHOP_DESCRIPTION;
import static hcmute.edu.vn.foody_08.ultil.constant.SHOP_ID;
import static hcmute.edu.vn.foody_08.ultil.constant.SHOP_IMAGE;
import static hcmute.edu.vn.foody_08.ultil.constant.SHOP_NAME;
import static hcmute.edu.vn.foody_08.ultil.constant.SHOP_OPEN_TIME;
import static hcmute.edu.vn.foody_08.ultil.constant.SHOP_PHONE;
import static hcmute.edu.vn.foody_08.ultil.constant.SHOP_RATING;
import static hcmute.edu.vn.foody_08.ultil.constant.SHOP_STATUS;
import static hcmute.edu.vn.foody_08.ultil.constant.TABLE_SHOP;
import static hcmute.edu.vn.foody_08.ultil.constant.TABLE_USER;
import static hcmute.edu.vn.foody_08.ultil.constant.USER_ADDRESS;
import static hcmute.edu.vn.foody_08.ultil.constant.USER_AVATAR;
import static hcmute.edu.vn.foody_08.ultil.constant.USER_EMAIL;
import static hcmute.edu.vn.foody_08.ultil.constant.USER_GENDER;
import static hcmute.edu.vn.foody_08.ultil.constant.USER_ID;
import static hcmute.edu.vn.foody_08.ultil.constant.USER_NAME;
import static hcmute.edu.vn.foody_08.ultil.constant.USER_PASSWORD;
import static hcmute.edu.vn.foody_08.ultil.constant.USER_PHONE;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import hcmute.edu.vn.foody_08.data.DatabaseHelper;
import hcmute.edu.vn.foody_08.model.Food;
import hcmute.edu.vn.foody_08.model.Shop;
import hcmute.edu.vn.foody_08.model.User;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ShopDAO {
    private DatabaseHelper databaseHelper;
    public ShopDAO() {
        databaseHelper = DatabaseHelper.getInstance();
    }
    public ShopDAO(Context context) {
        databaseHelper = DatabaseHelper.getInstance(context);
    }
    public long createShop(Shop shop) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = getContentValuesShop(shop);

        try {
            long id = sqLiteDatabase.insertOrThrow(TABLE_SHOP, null, contentValues);
            if(id>0) {
                System.out.println("successful inserted shop");
                return id;
            }
        } catch (SQLiteException e){
            System.out.println(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }
        return -1;
    }
    public List<Shop> getListShops(){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(TABLE_SHOP, null,null,null,
                    null, null, null);

            if(cursor!=null && cursor.moveToFirst()) {
                List<Shop> ShopList = new ArrayList<>();
                do {
                    Shop rs = getShopFromCursor(cursor);
                    ShopList.add(rs);
                }
                while (cursor.moveToNext());
                return ShopList;
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


    public List<Shop> getListShopsBy(String key, String value){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(TABLE_SHOP, null,
                    key + " =?", new String[]{String.valueOf(value)},
                    null, null, null);

            if(cursor!=null && cursor.moveToFirst()) {
                List<Shop> ShopList = new ArrayList<>();
                do {
                    Shop rs = getShopFromCursor(cursor);
                    ShopList.add(rs);
                }
                while (cursor.moveToNext());
                return ShopList;
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

    private ContentValues getContentValuesShop(Shop shop){
        ContentValues contentValues = new ContentValues();
        contentValues.put(SHOP_NAME, shop.getName());
        contentValues.put(SHOP_IMAGE, shop.getImage());
        contentValues.put(SHOP_DESCRIPTION, shop.getDescription());
        contentValues.put(SHOP_ADDRESS, shop.getAddress());
        contentValues.put(SHOP_STATUS, shop.getAddress());
        contentValues.put(SHOP_RATING, shop.getRating());
        contentValues.put(SHOP_OPEN_TIME, shop.getOpenTime().toString());
        contentValues.put(SHOP_CLOSE_TIME, shop.getCloseTime().toString());
        contentValues.put(SHOP_PHONE, shop.getPhoneNumber());

        return contentValues;
    }

    @SuppressLint("Range")
    private Shop getShopFromCursor(Cursor cursor){
        int id = cursor.getInt(cursor.getColumnIndex(SHOP_ID));
        String name =cursor.getString(cursor.getColumnIndex(SHOP_NAME));
        String image = cursor.getString(cursor.getColumnIndex(SHOP_IMAGE));
        String description = cursor.getString(cursor.getColumnIndex(SHOP_DESCRIPTION));
        String address =cursor.getString(cursor.getColumnIndex(SHOP_ADDRESS));
        String status =cursor.getString(cursor.getColumnIndex(SHOP_STATUS));
        Double rating=cursor.getDouble(cursor.getColumnIndex(SHOP_RATING));
        LocalTime openTime = LocalTime.parse(cursor.getString(cursor.getColumnIndex(SHOP_OPEN_TIME)));
        LocalTime closeTime =LocalTime.parse(cursor.getString(cursor.getColumnIndex(SHOP_CLOSE_TIME)));
        String phoneNumber =cursor.getString(cursor.getColumnIndex(SHOP_PHONE));

        return new Shop(id, name, image,description, address,status,rating,openTime,closeTime,phoneNumber);
    }
}
