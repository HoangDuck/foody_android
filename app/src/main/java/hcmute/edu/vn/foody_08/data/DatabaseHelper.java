package hcmute.edu.vn.foody_08.data;

import static hcmute.edu.vn.foody_08.ultil.constant.TABLE_SHOP;
import static hcmute.edu.vn.foody_08.ultil.constant.TABLE_USER;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.time.LocalTime;

import hcmute.edu.vn.foody_08.ultil.MyApp;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "foody";
    private static final int DATABASE_VERSION = 5;

    private static DatabaseHelper databaseHelper;

    private DatabaseHelper() {
        super(MyApp.context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context) {
        if (databaseHelper == null) {
            synchronized (DatabaseHelper.class){ //thread safe singleton
                if (databaseHelper == null)
                    databaseHelper = new DatabaseHelper(context);
            }
        }

        return databaseHelper;
    }

    public static DatabaseHelper getInstance() {

        if (databaseHelper == null) {
            synchronized (DatabaseHelper.class){ //thread safe singleton
                if (databaseHelper == null)
                    databaseHelper = new DatabaseHelper();
            }
        }

        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_SHOP);

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Users(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name VARCHAR(50),avatar VARCHAR(150), email VARCHAR(44),password VARCHAR(30)," +
                "address VARCHAR(150), gender VARCHAR(5), phone VARCHAR(14))");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Foods(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name VARCHAR(50),image VARCHAR(150),shopId INTEGER, description VARCHAR(30),price REAL, " +
                "status VARCHAR(50))");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Shops(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name VARCHAR(50),image VARCHAR(150), description VARCHAR(30)" +
                ",address VARCHAR(100),status VARCHAR(100),rating REAL,openTime VARCHAR(100),closeTime VARCHAR(100)," +
                "phoneNumber VARCHAR(100))");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS OrderDetails(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idOrder INTEGER," +
                "idFood INTEGER, num INTEGER,priceEach REAL)");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Orders(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idUser INTEGER, status VARCHAR(250),priceTotal REAL,dateOrder VARCHAR(100)," +
                "numTotal INTEGER,address VARCHAR(100))");
        System.out.println("==================================================================================================================");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Foods");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Shops"+TABLE_SHOP);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Orders");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS OrderDetails");
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        //enable foreign key constraints like ON UPDATE CASCADE, ON DELETE CASCADE
        db.execSQL("PRAGMA foreign_keys=ON;");
    }
}
