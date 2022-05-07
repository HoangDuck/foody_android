package hcmute.edu.vn.foody_08.model.DAO;

import static hcmute.edu.vn.foody_08.ultil.constant.FOOD_DESCRIPTION;
import static hcmute.edu.vn.foody_08.ultil.constant.FOOD_ID;
import static hcmute.edu.vn.foody_08.ultil.constant.FOOD_IMAGE;
import static hcmute.edu.vn.foody_08.ultil.constant.FOOD_NAME;
import static hcmute.edu.vn.foody_08.ultil.constant.FOOD_PRICE;
import static hcmute.edu.vn.foody_08.ultil.constant.FOOD_SHOPID;
import static hcmute.edu.vn.foody_08.ultil.constant.FOOD_STATUS;

import static hcmute.edu.vn.foody_08.ultil.constant.TABLE_FOOD;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_08.data.DatabaseHelper;
import hcmute.edu.vn.foody_08.model.Food;
import hcmute.edu.vn.foody_08.model.Food;

public class FoodDAO {
    private DatabaseHelper databaseHelper;

    public FoodDAO() {
        databaseHelper = DatabaseHelper.getInstance();
    }
    public FoodDAO(Context context) {
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    public long createFood(Food food) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = getContentValuesFood(food);

        try {
            long id = sqLiteDatabase.insertOrThrow(TABLE_FOOD, null, contentValues);
            if(id>0) {
                System.out.println("successful inserted food");
                return id;
            }
        } catch (SQLiteException e){
            System.out.println(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }
        return -1;
    }
    public List<Food> getListFoods(){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(TABLE_FOOD, null,null,null,
                    null, null, null);

            if(cursor!=null && cursor.moveToFirst()) {
                List<Food> FoodList = new ArrayList<>();
                do {
                    Food rs = getFoodFromCursor(cursor);
                    FoodList.add(rs);
                }
                while (cursor.moveToNext());
                return FoodList;
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


    public List<Food> getListFoodsBy(String key, String value){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(TABLE_FOOD, null,
                    key + " =?", new String[]{String.valueOf(value)},
                    null, null, null);

            if(cursor!=null && cursor.moveToFirst()) {
                List<Food> FoodList = new ArrayList<>();
                do {
                    Food rs = getFoodFromCursor(cursor);
                    FoodList.add(rs);
                }
                while (cursor.moveToNext());
                return FoodList;
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

    private ContentValues getContentValuesFood(Food food){
        ContentValues contentValues = new ContentValues();
        contentValues.put(FOOD_NAME, food.getName());
        contentValues.put(FOOD_IMAGE, food.getImage());
        contentValues.put(FOOD_SHOPID, food.getShopId());
        contentValues.put(FOOD_DESCRIPTION, food.getDescription());
        contentValues.put(FOOD_PRICE, food.getPrice());
        contentValues.put(FOOD_STATUS, food.getStatus());

        return contentValues;
    }

    @SuppressLint("Range")
    private Food getFoodFromCursor(Cursor cursor){
        int id =  cursor.getInt(cursor.getColumnIndex(FOOD_ID));
        String name = cursor.getString(cursor.getColumnIndex(FOOD_NAME));
        String image = cursor.getString(cursor.getColumnIndex(FOOD_IMAGE));
        int FoodId = cursor.getInt(cursor.getColumnIndex(FOOD_SHOPID));
        String description = cursor.getString(cursor.getColumnIndex(FOOD_DESCRIPTION));
        Double price = cursor.getDouble(cursor.getColumnIndex(FOOD_PRICE));
        String status = cursor.getString(cursor.getColumnIndex(FOOD_STATUS));


        return new Food(id,name,image,FoodId,description,price,status);
    }

}
