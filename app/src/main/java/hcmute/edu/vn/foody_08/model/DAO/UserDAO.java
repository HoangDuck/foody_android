package hcmute.edu.vn.foody_08.model.DAO;
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

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_08.data.DatabaseHelper;
import hcmute.edu.vn.foody_08.model.DTO.payload.LoginDTO;
import hcmute.edu.vn.foody_08.model.User;

public class UserDAO {
    private DatabaseHelper databaseHelper;

    public UserDAO() {
        databaseHelper = DatabaseHelper.getInstance();
    }
    public UserDAO(Context context) {
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    public long createUser(User user) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = getContentValuesUser(user);

        try {
            long id = sqLiteDatabase.insertOrThrow(TABLE_USER, null, contentValues);
            if(id>0) {
                System.out.println("successful inserted user");
                return id;
            }
        } catch (SQLiteException e){
            System.out.println(e.getMessage());
        } finally {
            sqLiteDatabase.close();
        }
        return -1;
    }

    public User CheckLogin(LoginDTO user){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.query(TABLE_USER, null,
                    USER_EMAIL + " =? AND "+USER_PASSWORD +" =?", new String[]{String.valueOf(user.getEmail()),String.valueOf(user.getPassword())},
                    null, null, null);

            if(cursor!=null && cursor.moveToFirst()) {
                User rs = getUserFromCursor(cursor);
                return rs;
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

    private ContentValues getContentValuesForLogin(LoginDTO user){
        ContentValues contentValues = new ContentValues();

        contentValues.put(USER_EMAIL, user.getEmail());
        contentValues.put(USER_PASSWORD, user.getPassword());

        return contentValues;
    }

    private ContentValues getContentValuesUser(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, user.getName());
        contentValues.put(USER_AVATAR, user.getAvatar());
        contentValues.put(USER_EMAIL, user.getEmail());
        contentValues.put(USER_PASSWORD, user.getPassword());
        contentValues.put(USER_ADDRESS, user.getAddress());
        contentValues.put(USER_GENDER, user.getGender());
        contentValues.put(USER_PHONE, user.getPhone());

        return contentValues;
    }
    @SuppressLint("Range")
    private User getUserFromCursor(Cursor cursor){
        int id = cursor.getInt(cursor.getColumnIndex(USER_ID));
        String name = cursor.getString(cursor.getColumnIndex(USER_NAME));
        String avatar = cursor.getString(cursor.getColumnIndex(USER_AVATAR));
        String email =cursor.getString(cursor.getColumnIndex(USER_EMAIL));
        String password =cursor.getString(cursor.getColumnIndex(USER_PASSWORD));
        String address=cursor.getString(cursor.getColumnIndex(USER_ADDRESS));
        String gender = cursor.getString(cursor.getColumnIndex(USER_GENDER));
        String phone =cursor.getString(cursor.getColumnIndex(USER_PHONE));


        return new User(id, name, avatar,email, password,address,gender,phone);
    }
}
