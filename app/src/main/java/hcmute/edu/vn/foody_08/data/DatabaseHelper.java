package hcmute.edu.vn.foody_08.data;

import static hcmute.edu.vn.foody_08.ultil.constant.SHOP_ADDRESS;
import static hcmute.edu.vn.foody_08.ultil.constant.SHOP_CLOSE_TIME;
import static hcmute.edu.vn.foody_08.ultil.constant.SHOP_DESCRIPTION;
import static hcmute.edu.vn.foody_08.ultil.constant.SHOP_IMAGE;
import static hcmute.edu.vn.foody_08.ultil.constant.SHOP_NAME;
import static hcmute.edu.vn.foody_08.ultil.constant.SHOP_OPEN_TIME;
import static hcmute.edu.vn.foody_08.ultil.constant.SHOP_PHONE;
import static hcmute.edu.vn.foody_08.ultil.constant.SHOP_RATING;
import static hcmute.edu.vn.foody_08.ultil.constant.SHOP_STATUS;
import static hcmute.edu.vn.foody_08.ultil.constant.TABLE_SHOP;
import static hcmute.edu.vn.foody_08.ultil.constant.TABLE_USER;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_08.ultil.MyApp;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "foody";
    private static final int DATABASE_VERSION = 12;

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
        insertData(sqLiteDatabase);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        //enable foreign key constraints like ON UPDATE CASCADE, ON DELETE CASCADE
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    private void insertData(SQLiteDatabase sqLiteDatabase){
        List<String> shops = listShops();
        List<String> foods = listFoods();
        for (String item: shops
             ) {
            sqLiteDatabase.execSQL(item);
        }

        for (String item: foods
        ) {
            sqLiteDatabase.execSQL(item);
        }

    }
    private List<String> listShops(){
        List<String> shops = new ArrayList<>();
        shops.add("INSERT INTO Shops values(null,'Miền Tây Quán'," +
                "'https://drive.google.com/file/d/1aHCCMg1svlHyOgeJPDugAwl8XbXT1k_9/view'," +
                "'Quán siêu ngon bổ rẻ' ," +
                "'138 Nam Kỳ Khởi Nghĩa, Quận 1, TPHCM'," +
                "'Open','5.0'," +
                "'09:00:00','21:00:00'," +
                "'0123456789')");

        shops.add("INSERT INTO Shops values(null,'Miền Đông Quán'," +
                "'https://drive.google.com/file/d/1cVrIlCkhaHWqbCSsdB7S5tegP-NDAsP3/view'," +
                "'Quán Ăn Ngon được biết đến là một trong những địa chỉ ăn uống nổi tiếng nhất ở Sài Gòn' ," +
                "'138 Nam Kỳ Khởi Nghĩa, Quận 1, TPHCM'," +
                "'Open','4.9'," +
                "'11:00:00','21:00:00'," +
                "'0123456789')");

        shops.add("INSERT INTO Shops values(null,'Hội Ngộ Quán'," +
                "'https://drive.google.com/file/d/1i4HvBhIlQc-QL200Ok9XfwPcO3xe256X/view'," +
                "'Quán siêu ngon bổ rẻ' ," +
                "'61 Cao Thắng, Phường 3, Quận 3, TP.HCM'," +
                "'Open','5.0'," +
                "'09:00:00','21:00:00'," +
                "'0123456789')");

        shops.add("INSERT INTO Shops values(null,'Chanh sả Quán'," +
                "'https://drive.google.com/file/d/1__fCmAV6F-Hnhz6sOA6EvjZY9hQbqD07/view'," +
                "'Chanh sả Quán được biết đến là quán chuyên về lẩu cua biển Cà Mau nổi tiếng nhất tại Sài Gòn là quán lẩu cua biển Cà Mau nổi tiếng nhất Sài Gòn' ," +
                "'1067 Bình Quới, Quận Bình Thạnh, TP.HCM'," +
                "'Open','5.0'," +
                "'09:00:00','21:00:00'," +
                "'0123456789')");

        shops.add("INSERT INTO Shops values(null,'Super Idol Quán'," +
                "'https://drive.google.com/file/d/1esHuJcI6HxH34q9DG2Ab5JZeFkSfyVCU/view'," +
                "'Super Idol Quán không còn xa lạ với những người dân sinh sống tại Sài Gòn.' ," +
                "'110A số 9A, ngay khu dân cư Trung Sơn, huyện Bình Chánh, TPHCM'," +
                "'Open','5.0'," +
                "'09:00:00','21:00:00'," +
                "'0123456789')");

        shops.add("INSERT INTO Shops values(null,'Thủy Hử Quán'," +
                "'https://drive.google.com/file/d/17zS23lcrSaSVh2VuKF5P789IXorJ3Uei/view'," +
                "'Menu đồ ăn và thức uống tại đây chủ yếu là các món Âu hấp dẫn như steak, pasta, các món chế biến theo phong cách châu Âu hấp dẫn và những loại vang cao cấp đúng vị nhất' ," +
                "'74/7C Hai Bà Trưng, Quận 1, TPHCM'," +
                "'Open','4.0'," +
                "'09:00:00','21:00:00'," +
                "'0123456789')");

        shops.add("INSERT INTO Shops values(null,'Hồng Lâu Quán'," +
                "'https://drive.google.com/file/d/1hl3UhWJc-dKB0-0f2hAo1SQoYZEcxiMX/view'," +
                "'Quán siêu ngon bổ rẻ' ," +
                "'138 Nam Kỳ Khởi Nghĩa, Quận 1, TPHCM'," +
                "'Open','3.0'," +
                "'09:00:00','21:00:00'," +
                "'0123456789')");

        shops.add("INSERT INTO Shops values(null,'Khoái Quán'," +
                "'https://drive.google.com/file/d/17dgEXrovWxC2n7kWnH1jVv5R5I3yhBU1/view'," +
                "'Quán Khoái là địa chỉ chuyên phục vụ các món đặc sản của vùng đất Nha Trang như món bánh căn, món bánh bèo tôm chấy hay món bún sứa được chế biến đúng với đúng hương vị biển Nha Trang' ," +
                "'16 Lê Quý Đôn, Phường 06, Quận 03, TPHCM'," +
                "'Open','4.0'," +
                "'09:00:00','21:00:00'," +
                "'0123456789')");

        shops.add("INSERT INTO Shops values(null,'The Coffee House Quán'," +
                "'https://drive.google.com/file/d/1P5yDvWjr2-GeVfF-qrAMKxBmooxSGX0-/view'," +
                "'Quán siêu ngon bổ rẻ' ," +
                "'87 Bà Huyện Thanh Quan, Quận 3, TPHCM'," +
                "'Open','5.0'," +
                "'09:00:00','21:00:00'," +
                "'0123456789')");

        shops.add("INSERT INTO Shops values(null,'Miền Nam Quán'," +
                "'https://drive.google.com/file/d/1O4vmy_1UlyDxb1aC3fjj_oZV2pGKEpIs/view'," +
                "'Miền Nam Quán là địa chỉ quán ăn ngon ở Sài Gòn chuyên mang đến cho thực khách các món ăn hấp dẫn từ nguyên liệu chính là hải sản như cua, ghẹ, hàu hay tôm hùm đất' ," +
                "'Số 10 Nguyễn Thông, Phường 7, Quận 3, TPHCM'," +
                "'Open','5.0'," +
                "'09:00:00','21:00:00'," +
                "'0123456789')");

        return shops;
    }
    private List<String> listFoods(){
        List<String> foods = new ArrayList<>();
        //Shop 1
        foods.add("INSERT INTO Foods values(null," +
                        "'Susi Nhật bản'," +
                        "'https://drive.google.com/file/d/19DEsQgYMsTRx-F1SRFvIGSIrJwfvxSx-/view'," +
                "'1'," +
                "'Su si siêu ngon','50000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh khọt'," +
                "'https://drive.google.com/file/d/1OL_454eG4ps5YAkM_OkcmNmMC0NfjYJe/view'," +
                "'1'," +
                "'Bánh khọt siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Hamberger'," +
                "'https://drive.google.com/file/d/1FQAGCo_vQBLRt8j1qG9cS_VgvAu3p9a3/view'," +
                "'1'," +
                "'Hamberger siêu ngon','50000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bún bò huê'," +
                "'https://drive.google.com/file/d/1prnbSOH7vNbX_5q27Pl8wkZHkBCYSLyW/view'," +
                "'1'," +
                "'Bún bò siêu ngon','70000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh tuyết'," +
                "'https://drive.google.com/file/d/1wx09zo8bpZfsjbZf-tlS_2W0ttu_gKbT/view'," +
                "'1'," +
                "'Bánh tuyết siêu ngon','40000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh mì'," +
                "'https://drive.google.com/file/d/1Od5wyG1EO7thOwuY8UpugKNNFjHJhAjj/view'," +
                "'1'," +
                "'Bánh mì siêu ngon','10000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bò bít tết'," +
                "'https://drive.google.com/file/d/1hno8eWibxvhWEVN712qtOY4gbcZxyDil/view'," +
                "'1'," +
                "'Bò bít tết siêu ngon','200000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Chả lụi'," +
                "'https://drive.google.com/file/d/1-QIVWX3_qCsONBW4Q0_PD9SIAEPsBvCv/view'," +
                "'1'," +
                "'Chả lụi siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'bún Lẩu nấm'," +
                "'https://drive.google.com/file/d/1z2tx6DnCKu57ywlMndkknIriIeX8vi6v/view'," +
                "'1'," +
                "'Lẩu nấm siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Tôm nướng'," +
                "'https://drive.google.com/file/d/1eRbI8AwHY27I28J8QkfuIWQ1UhlRcPFP/view'," +
                "'1'," +
                "'Tôm nướng siêu ngon','10000.0','Vẫn còn hàng')");

        //Shop 2

        foods.add("INSERT INTO Foods values(null," +
                "'Toboki Nhật bản'," +
                "'https://drive.google.com/file/d/13TyYzwwf-0BzORZRmK_DNeD1MWV-f2Qk/view'," +
                "'2'," +
                "'Toboki siêu ngon','50000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh Xèo'," +
                "'https://drive.google.com/file/d/1r0S9B3Q1cIfC889meiBH35BHUzfZlbW3/view'," +
                "'2'," +
                "'Bánh Xèo siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Khoai tây chiên'," +
                "'https://drive.google.com/file/d/1cBhJvB40qUNbiOoSX-nhDGFaObbrnR4-/view'," +
                "'2'," +
                "'Khoai tây chiên siêu ngon','50000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'bún bò huê'," +
                "'https://drive.google.com/file/d/1zp8JC19MXyWAD1FXmAErQkuFSQrwrxJP/view'," +
                "'2'," +
                "'Bún bò siêu ngon','70000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh tuyết'," +
                "'https://drive.google.com/file/d/1i9siVLf9C4zmKWKESi8UM-3tZs6-ave1/view'," +
                "'2'," +
                "'Bánh tuyết siêu ngon','40000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh mì'," +
                "'https://drive.google.com/file/d/1e0GX0N7Nv3TcI7IxFiCiN05U7jNRtbQE/view'," +
                "'2'," +
                "'Bánh mì siêu ngon','10000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bò bít tết'," +
                "'https://drive.google.com/file/d/1QgeSPhjfedRlqTtIwUndbctD7bYwXpK8/view'," +
                "'2'," +
                "'Bò bít tết siêu ngon','200000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Chả lụi'," +
                "'https://drive.google.com/file/d/19Xa5xykGgqf7RXSWD2mbXmoNnQV6YqEi/view'," +
                "'2'," +
                "'Chả lụi siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'bún Lẩu nấm'," +
                "'https://drive.google.com/file/d/1oObX1m5wmaWpT9d1YJ5lECvuEYYqFxdU/view'," +
                "'2'," +
                "'Lẩu nấm siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Tôm nướng'," +
                "'https://drive.google.com/file/d/1isBHI8Xht6KdTOR6BJ8eLwf09Mx98wZA/view'," +
                "'2'," +
                "'Tôm nướng siêu ngon','10000.0','Vẫn còn hàng')");

        //Shop 3

        foods.add("INSERT INTO Foods values(null," +
                "'Susi Hàn'," +
                "'https://drive.google.com/file/d/11fhdx51sqO5QGNpEZjxGJfUi83dGIe-Q/view'," +
                "'3'," +
                "'Susi siêu ngon','50000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh tét'," +
                "'https://drive.google.com/file/d/1k2OVsBoiRiEtCJLUBpJ6c1LnxPl4iCR6/view'," +
                "'3'," +
                "'Bánh tét siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Hamberger'," +
                "'https://drive.google.com/file/d/1t_wJSsiSwWv4fl1JXU-Sa8LDK4sUTbtg/view'," +
                "'3'," +
                "'Hamberger siêu ngon','50000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bún riêu cua'," +
                "'https://drive.google.com/file/d/1B58OOs8acFy3d2Qh0qB1UVNpCLUvQpeE/view'," +
                "'3'," +
                "'Bún riêu cua siêu ngon','70000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh plan'," +
                "'https://drive.google.com/file/d/1Sg1_CRAeXM9BRdgER3zQJOhZv0xf7eOd/view'," +
                "'3'," +
                "'Bánh plan siêu ngon','40000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh mì'," +
                "'https://drive.google.com/file/d/1cSo07LQNZjGGwpXflM_7Ybkq6XnUQLQC/view'," +
                "'3'," +
                "'Bánh mì siêu ngon','10000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bò bít tết'," +
                "'https://drive.google.com/file/d/13-Q8h4MkM1qVhKDMtNqgbz0b9q7WOTSQ/view'," +
                "'3'," +
                "'Bò bít tết siêu ngon','200000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Chả lụi'," +
                "'https://drive.google.com/file/d/1suYtVJinrq9GPTsqOOYtcrLzaSyS858U/view'," +
                "'3'," +
                "'Chả lụi siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'bún Lẩu nấm'," +
                "'https://drive.google.com/file/d/1jHOCiiqF3JIIh4AXWP__TFKSarRr0IW4/view'," +
                "'3'," +
                "'Lẩu nấm siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Tôm nướng'," +
                "'https://drive.google.com/file/d/1Eyanhc4JEoFLHa5dR6V1uwrzPbG2UiSU/view'," +
                "'3'," +
                "'Tôm nướng siêu ngon','10000.0','Vẫn còn hàng')");

        //Shop 4

        foods.add("INSERT INTO Foods values(null," +
                "'Susi Nhật bản'," +
                "'https://drive.google.com/file/d/19DEsQgYMsTRx-F1SRFvIGSIrJwfvxSx-/view'," +
                "'4'," +
                "'Su si siêu ngon','50000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh khọt'," +
                "'https://drive.google.com/file/d/1OL_454eG4ps5YAkM_OkcmNmMC0NfjYJe/view'," +
                "'4'," +
                "'Bánh khọt siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Hamberger'," +
                "'https://drive.google.com/file/d/1FQAGCo_vQBLRt8j1qG9cS_VgvAu3p9a3/view'," +
                "'4'," +
                "'Hamberger siêu ngon','50000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bún bò huê'," +
                "'https://drive.google.com/file/d/1prnbSOH7vNbX_5q27Pl8wkZHkBCYSLyW/view'," +
                "'4'," +
                "'Bún bò siêu ngon','70000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh tuyết'," +
                "'https://drive.google.com/file/d/1wx09zo8bpZfsjbZf-tlS_2W0ttu_gKbT/view'," +
                "'4'," +
                "'Bánh tuyết siêu ngon','40000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh mì'," +
                "'https://drive.google.com/file/d/1Od5wyG1EO7thOwuY8UpugKNNFjHJhAjj/view'," +
                "'4'," +
                "'Bánh mì siêu ngon','10000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bò bít tết'," +
                "'https://drive.google.com/file/d/1hno8eWibxvhWEVN712qtOY4gbcZxyDil/view'," +
                "'4'," +
                "'Bò bít tết siêu ngon','200000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Chả lụi'," +
                "'https://drive.google.com/file/d/1-QIVWX3_qCsONBW4Q0_PD9SIAEPsBvCv/view'," +
                "'4'," +
                "'Chả lụi siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'bún Lẩu nấm'," +
                "'https://drive.google.com/file/d/1z2tx6DnCKu57ywlMndkknIriIeX8vi6v/view'," +
                "'4'," +
                "'Lẩu nấm siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Tôm nướng'," +
                "'https://drive.google.com/file/d/1eRbI8AwHY27I28J8QkfuIWQ1UhlRcPFP/view'," +
                "'4'," +
                "'Tôm nướng siêu ngon','10000.0','Vẫn còn hàng')");

        //Shop 5
        foods.add("INSERT INTO Foods values(null," +
                "'Toboki Nhật bản'," +
                "'https://drive.google.com/file/d/13TyYzwwf-0BzORZRmK_DNeD1MWV-f2Qk/view'," +
                "'5'," +
                "'Toboki siêu ngon','50000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh Xèo'," +
                "'https://drive.google.com/file/d/1r0S9B3Q1cIfC889meiBH35BHUzfZlbW3/view'," +
                "'5'," +
                "'Bánh Xèo siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Khoai tây chiên'," +
                "'https://drive.google.com/file/d/1cBhJvB40qUNbiOoSX-nhDGFaObbrnR4-/view'," +
                "'5'," +
                "'Khoai tây chiên siêu ngon','50000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'bún bò huê'," +
                "'https://drive.google.com/file/d/1zp8JC19MXyWAD1FXmAErQkuFSQrwrxJP/view'," +
                "'5'," +
                "'Bún bò siêu ngon','70000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh tuyết'," +
                "'https://drive.google.com/file/d/1i9siVLf9C4zmKWKESi8UM-3tZs6-ave1/view'," +
                "'5'," +
                "'Bánh tuyết siêu ngon','40000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh mì'," +
                "'https://drive.google.com/file/d/1e0GX0N7Nv3TcI7IxFiCiN05U7jNRtbQE/view'," +
                "'5'," +
                "'Bánh mì siêu ngon','10000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bò bít tết'," +
                "'https://drive.google.com/file/d/1QgeSPhjfedRlqTtIwUndbctD7bYwXpK8/view'," +
                "'5'," +
                "'Bò bít tết siêu ngon','200000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Chả lụi'," +
                "'https://drive.google.com/file/d/19Xa5xykGgqf7RXSWD2mbXmoNnQV6YqEi/view'," +
                "'5'," +
                "'Chả lụi siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'bún Lẩu nấm'," +
                "'https://drive.google.com/file/d/1oObX1m5wmaWpT9d1YJ5lECvuEYYqFxdU/view'," +
                "'5'," +
                "'Lẩu nấm siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Tôm nướng'," +
                "'https://drive.google.com/file/d/1isBHI8Xht6KdTOR6BJ8eLwf09Mx98wZA/view'," +
                "'5'," +
                "'Tôm nướng siêu ngon','10000.0','Vẫn còn hàng')");

        //Shop 6

        foods.add("INSERT INTO Foods values(null," +
                "'Susi Hàn'," +
                "'https://drive.google.com/file/d/11fhdx51sqO5QGNpEZjxGJfUi83dGIe-Q/view'," +
                "'6'," +
                "'Susi siêu ngon','50000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh tét'," +
                "'https://drive.google.com/file/d/1k2OVsBoiRiEtCJLUBpJ6c1LnxPl4iCR6/view'," +
                "'6'," +
                "'Bánh tét siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Hamberger'," +
                "'https://drive.google.com/file/d/1t_wJSsiSwWv4fl1JXU-Sa8LDK4sUTbtg/view'," +
                "'6'," +
                "'Hamberger siêu ngon','50000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bún riêu cua'," +
                "'https://drive.google.com/file/d/1B58OOs8acFy3d2Qh0qB1UVNpCLUvQpeE/view'," +
                "'6'," +
                "'Bún riêu cua siêu ngon','70000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh plan'," +
                "'https://drive.google.com/file/d/1Sg1_CRAeXM9BRdgER3zQJOhZv0xf7eOd/view'," +
                "'6'," +
                "'Bánh plan siêu ngon','40000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh mì'," +
                "'https://drive.google.com/file/d/1cSo07LQNZjGGwpXflM_7Ybkq6XnUQLQC/view'," +
                "'6'," +
                "'Bánh mì siêu ngon','10000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bò bít tết'," +
                "'https://drive.google.com/file/d/13-Q8h4MkM1qVhKDMtNqgbz0b9q7WOTSQ/view'," +
                "'6'," +
                "'Bò bít tết siêu ngon','200000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Chả lụi'," +
                "'https://drive.google.com/file/d/1suYtVJinrq9GPTsqOOYtcrLzaSyS858U/view'," +
                "'6'," +
                "'Chả lụi siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'bún Lẩu nấm'," +
                "'https://drive.google.com/file/d/1jHOCiiqF3JIIh4AXWP__TFKSarRr0IW4/view'," +
                "'6'," +
                "'Lẩu nấm siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Tôm nướng'," +
                "'https://drive.google.com/file/d/1Eyanhc4JEoFLHa5dR6V1uwrzPbG2UiSU/view'," +
                "'6'," +
                "'Tôm nướng siêu ngon','10000.0','Vẫn còn hàng')");


        //Shop 7
        foods.add("INSERT INTO Foods values(null," +
                "'Susi Nhật bản'," +
                "'https://drive.google.com/file/d/19DEsQgYMsTRx-F1SRFvIGSIrJwfvxSx-/view'," +
                "'7'," +
                "'Su si siêu ngon','50000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh khọt'," +
                "'https://drive.google.com/file/d/1OL_454eG4ps5YAkM_OkcmNmMC0NfjYJe/view'," +
                "'7'," +
                "'Bánh khọt siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Hamberger'," +
                "'https://drive.google.com/file/d/1FQAGCo_vQBLRt8j1qG9cS_VgvAu3p9a3/view'," +
                "'7'," +
                "'Hamberger siêu ngon','50000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bún bò huê'," +
                "'https://drive.google.com/file/d/1prnbSOH7vNbX_5q27Pl8wkZHkBCYSLyW/view'," +
                "'7'," +
                "'Bún bò siêu ngon','70000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh tuyết'," +
                "'https://drive.google.com/file/d/1wx09zo8bpZfsjbZf-tlS_2W0ttu_gKbT/view'," +
                "'7'," +
                "'Bánh tuyết siêu ngon','40000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh mì'," +
                "'https://drive.google.com/file/d/1Od5wyG1EO7thOwuY8UpugKNNFjHJhAjj/view'," +
                "'7'," +
                "'Bánh mì siêu ngon','10000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bò bít tết'," +
                "'https://drive.google.com/file/d/1hno8eWibxvhWEVN712qtOY4gbcZxyDil/view'," +
                "'7'," +
                "'Bò bít tết siêu ngon','200000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Chả lụi'," +
                "'https://drive.google.com/file/d/1-QIVWX3_qCsONBW4Q0_PD9SIAEPsBvCv/view'," +
                "'7'," +
                "'Chả lụi siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'bún Lẩu nấm'," +
                "'https://drive.google.com/file/d/1z2tx6DnCKu57ywlMndkknIriIeX8vi6v/view'," +
                "'7'," +
                "'Lẩu nấm siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Tôm nướng'," +
                "'https://drive.google.com/file/d/1eRbI8AwHY27I28J8QkfuIWQ1UhlRcPFP/view'," +
                "'7'," +
                "'Tôm nướng siêu ngon','10000.0','Vẫn còn hàng')");
        //Shop 8
        foods.add("INSERT INTO Foods values(null," +
                "'Toboki Nhật bản'," +
                "'https://drive.google.com/file/d/13TyYzwwf-0BzORZRmK_DNeD1MWV-f2Qk/view'," +
                "'8'," +
                "'Toboki siêu ngon','50000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh Xèo'," +
                "'https://drive.google.com/file/d/1r0S9B3Q1cIfC889meiBH35BHUzfZlbW3/view'," +
                "'8'," +
                "'Bánh Xèo siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Khoai tây chiên'," +
                "'https://drive.google.com/file/d/1cBhJvB40qUNbiOoSX-nhDGFaObbrnR4-/view'," +
                "'8'," +
                "'Khoai tây chiên siêu ngon','50000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'bún bò huê'," +
                "'https://drive.google.com/file/d/1zp8JC19MXyWAD1FXmAErQkuFSQrwrxJP/view'," +
                "'8'," +
                "'Bún bò siêu ngon','70000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh tuyết'," +
                "'https://drive.google.com/file/d/1i9siVLf9C4zmKWKESi8UM-3tZs6-ave1/view'," +
                "'8'," +
                "'Bánh tuyết siêu ngon','40000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh mì'," +
                "'https://drive.google.com/file/d/1e0GX0N7Nv3TcI7IxFiCiN05U7jNRtbQE/view'," +
                "'8'," +
                "'Bánh mì siêu ngon','10000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bò bít tết'," +
                "'https://drive.google.com/file/d/1QgeSPhjfedRlqTtIwUndbctD7bYwXpK8/view'," +
                "'8'," +
                "'Bò bít tết siêu ngon','200000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Chả lụi'," +
                "'https://drive.google.com/file/d/19Xa5xykGgqf7RXSWD2mbXmoNnQV6YqEi/view'," +
                "'8'," +
                "'Chả lụi siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'bún Lẩu nấm'," +
                "'https://drive.google.com/file/d/1oObX1m5wmaWpT9d1YJ5lECvuEYYqFxdU/view'," +
                "'8'," +
                "'Lẩu nấm siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Tôm nướng'," +
                "'https://drive.google.com/file/d/1isBHI8Xht6KdTOR6BJ8eLwf09Mx98wZA/view'," +
                "'8'," +
                "'Tôm nướng siêu ngon','10000.0','Vẫn còn hàng')");

        //Shop 9
        foods.add("INSERT INTO Foods values(null," +
                "'Susi Hàn'," +
                "'https://drive.google.com/file/d/11fhdx51sqO5QGNpEZjxGJfUi83dGIe-Q/view'," +
                "'9'," +
                "'Susi siêu ngon','50000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh tét'," +
                "'https://drive.google.com/file/d/1k2OVsBoiRiEtCJLUBpJ6c1LnxPl4iCR6/view'," +
                "'9'," +
                "'Bánh tét siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Hamberger'," +
                "'https://drive.google.com/file/d/1t_wJSsiSwWv4fl1JXU-Sa8LDK4sUTbtg/view'," +
                "'9'," +
                "'Hamberger siêu ngon','50000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bún riêu cua'," +
                "'https://drive.google.com/file/d/1B58OOs8acFy3d2Qh0qB1UVNpCLUvQpeE/view'," +
                "'9'," +
                "'Bún riêu cua siêu ngon','70000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh plan'," +
                "'https://drive.google.com/file/d/1Sg1_CRAeXM9BRdgER3zQJOhZv0xf7eOd/view'," +
                "'9'," +
                "'Bánh plan siêu ngon','40000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh mì'," +
                "'https://drive.google.com/file/d/1cSo07LQNZjGGwpXflM_7Ybkq6XnUQLQC/view'," +
                "'9'," +
                "'Bánh mì siêu ngon','10000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bò bít tết'," +
                "'https://drive.google.com/file/d/13-Q8h4MkM1qVhKDMtNqgbz0b9q7WOTSQ/view'," +
                "'9'," +
                "'Bò bít tết siêu ngon','200000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Chả lụi'," +
                "'https://drive.google.com/file/d/1suYtVJinrq9GPTsqOOYtcrLzaSyS858U/view'," +
                "'9'," +
                "'Chả lụi siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'bún Lẩu nấm'," +
                "'https://drive.google.com/file/d/1jHOCiiqF3JIIh4AXWP__TFKSarRr0IW4/view'," +
                "'9'," +
                "'Lẩu nấm siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Tôm nướng'," +
                "'https://drive.google.com/file/d/1Eyanhc4JEoFLHa5dR6V1uwrzPbG2UiSU/view'," +
                "'9'," +
                "'Tôm nướng siêu ngon','10000.0','Vẫn còn hàng')");

        //Shop 10
        foods.add("INSERT INTO Foods values(null," +
                "'Susi Nhật bản'," +
                "'https://drive.google.com/file/d/19DEsQgYMsTRx-F1SRFvIGSIrJwfvxSx-/view'," +
                "'10'," +
                "'Su si siêu ngon','50000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh khọt'," +
                "'https://drive.google.com/file/d/1OL_454eG4ps5YAkM_OkcmNmMC0NfjYJe/view'," +
                "'10'," +
                "'Bánh khọt siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Hamberger'," +
                "'https://drive.google.com/file/d/1FQAGCo_vQBLRt8j1qG9cS_VgvAu3p9a3/view'," +
                "'10'," +
                "'Hamberger siêu ngon','50000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bún bò huê'," +
                "'https://drive.google.com/file/d/1prnbSOH7vNbX_5q27Pl8wkZHkBCYSLyW/view'," +
                "'10'," +
                "'Bún bò siêu ngon','70000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh tuyết'," +
                "'https://drive.google.com/file/d/1wx09zo8bpZfsjbZf-tlS_2W0ttu_gKbT/view'," +
                "'10'," +
                "'Bánh tuyết siêu ngon','40000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bánh mì'," +
                "'https://drive.google.com/file/d/1Od5wyG1EO7thOwuY8UpugKNNFjHJhAjj/view'," +
                "'10'," +
                "'Bánh mì siêu ngon','10000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Bò bít tết'," +
                "'https://drive.google.com/file/d/1hno8eWibxvhWEVN712qtOY4gbcZxyDil/view'," +
                "'10'," +
                "'Bò bít tết siêu ngon','200000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Chả lụi'," +
                "'https://drive.google.com/file/d/1-QIVWX3_qCsONBW4Q0_PD9SIAEPsBvCv/view'," +
                "'10'," +
                "'Chả lụi siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'bún Lẩu nấm'," +
                "'https://drive.google.com/file/d/1z2tx6DnCKu57ywlMndkknIriIeX8vi6v/view'," +
                "'10'," +
                "'Lẩu nấm siêu ngon','30000.0','Vẫn còn hàng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Tôm nướng'," +
                "'https://drive.google.com/file/d/1eRbI8AwHY27I28J8QkfuIWQ1UhlRcPFP/view'," +
                "'10'," +
                "'Tôm nướng siêu ngon','10000.0','Vẫn còn hàng')");
        return foods;
    }
}
