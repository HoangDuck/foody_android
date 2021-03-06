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
    private static final int DATABASE_VERSION = 18;

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
        shops.add("INSERT INTO Shops values(null,'Mi???n T??y Qu??n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/Quan%2F1.jpg?alt=media&token=401b38c0-7e36-4a49-bb09-a7befc33be12'," +
                "'Qu??n si??u ngon b??? r???' ," +
                "'138 Nam K??? Kh???i Ngh??a, Qu???n 1, TPHCM'," +
                "'Open','5.0'," +
                "'09:00:00','21:00:00'," +
                "'0123456789')");

        shops.add("INSERT INTO Shops values(null,'Mi???n ????ng Qu??n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/Quan%2F2.jpg?alt=media&token=0e4e015e-1ccd-48ab-9adc-d8c6c64b7178'," +
                "'Qu??n ??n Ngon ???????c bi???t ?????n l?? m???t trong nh???ng ?????a ch??? ??n u???ng n???i ti???ng nh???t ??? S??i G??n' ," +
                "'138 Nam K??? Kh???i Ngh??a, Qu???n 1, TPHCM'," +
                "'Open','4.9'," +
                "'11:00:00','21:00:00'," +
                "'0123456789')");

        shops.add("INSERT INTO Shops values(null,'H???i Ng??? Qu??n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/Quan%2F3.jpg?alt=media&token=9ecbff23-3da9-4dbf-a3a2-68537329ee4e'," +
                "'Qu??n si??u ngon b??? r???' ," +
                "'61 Cao Th???ng, Ph?????ng 3, Qu???n 3, TP.HCM'," +
                "'Open','5.0'," +
                "'09:00:00','21:00:00'," +
                "'0123456789')");

        shops.add("INSERT INTO Shops values(null,'Chanh s??? Qu??n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/Quan%2F4.jpg?alt=media&token=4ab1bbb7-cf34-4c6f-b076-c507fb795a18'," +
                "'Chanh s??? Qu??n ???????c bi???t ?????n l?? qu??n chuy??n v??? l???u cua bi???n C?? Mau n???i ti???ng nh???t t???i S??i G??n l?? qu??n l???u cua bi???n C?? Mau n???i ti???ng nh???t S??i G??n' ," +
                "'1067 B??nh Qu???i, Qu???n B??nh Th???nh, TP.HCM'," +
                "'Open','5.0'," +
                "'09:00:00','21:00:00'," +
                "'0123456789')");

        shops.add("INSERT INTO Shops values(null,'Super Idol Qu??n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/Quan%2F5.jpg?alt=media&token=355b621c-3244-4861-a1bd-78bc363e74c0'," +
                "'Super Idol Qu??n kh??ng c??n xa l??? v???i nh???ng ng?????i d??n sinh s???ng t???i S??i G??n.' ," +
                "'110A s??? 9A, ngay khu d??n c?? Trung S??n, huy???n B??nh Ch??nh, TPHCM'," +
                "'Open','5.0'," +
                "'09:00:00','21:00:00'," +
                "'0123456789')");

        shops.add("INSERT INTO Shops values(null,'Th???y H??? Qu??n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/Quan%2F6.jpg?alt=media&token=c49ea1b8-bcb7-4593-b5ae-73829c492ea3'," +
                "'Menu ????? ??n v?? th???c u???ng t???i ????y ch??? y???u l?? c??c m??n ??u h???p d???n nh?? steak, pasta, c??c m??n ch??? bi???n theo phong c??ch ch??u ??u h???p d???n v?? nh???ng lo???i vang cao c???p ????ng v??? nh???t' ," +
                "'74/7C Hai B?? Tr??ng, Qu???n 1, TPHCM'," +
                "'Open','4.0'," +
                "'09:00:00','21:00:00'," +
                "'0123456789')");

        shops.add("INSERT INTO Shops values(null,'H???ng L??u Qu??n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/Quan%2F7.jpg?alt=media&token=8c1991ea-eaf8-46ab-962f-a4bccfdc33a6'," +
                "'Qu??n si??u ngon b??? r???' ," +
                "'138 Nam K??? Kh???i Ngh??a, Qu???n 1, TPHCM'," +
                "'Open','3.0'," +
                "'09:00:00','21:00:00'," +
                "'0123456789')");

        shops.add("INSERT INTO Shops values(null,'Kho??i Qu??n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/Quan%2F8.jpg?alt=media&token=004076ce-4a83-4728-9a01-01f88e8a0a61'," +
                "'Qu??n Kho??i l?? ?????a ch??? chuy??n ph???c v??? c??c m??n ?????c s???n c???a v??ng ?????t Nha Trang nh?? m??n b??nh c??n, m??n b??nh b??o t??m ch????y hay m??n b??n s???a ???????c ch??? bi???n ????ng v???i ????ng h????ng v??? bi???n Nha Trang' ," +
                "'16 L?? Qu?? ????n, Ph?????ng 06, Qu???n 03, TPHCM'," +
                "'Open','4.0'," +
                "'09:00:00','21:00:00'," +
                "'0123456789')");

        shops.add("INSERT INTO Shops values(null,'The Coffee House Qu??n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/Quan%2F9.jpg?alt=media&token=e3292988-33e0-4bef-b7ca-3b175e2708df'," +
                "'Qu??n si??u ngon b??? r???' ," +
                "'87 B?? Huy???n Thanh Quan, Qu???n 3, TPHCM'," +
                "'Open','5.0'," +
                "'09:00:00','21:00:00'," +
                "'0123456789')");

        shops.add("INSERT INTO Shops values(null,'Mi???n Nam Qu??n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/Quan%2F10.jpg?alt=media&token=a144d7c3-f8d3-4dc3-9b31-dee4abe155e7'," +
                "'Mi???n Nam Qu??n l?? ?????a ch??? qu??n ??n ngon ??? S??i G??n chuy??n mang ?????n cho th???c kh??ch c??c m??n ??n h???p d???n t??? nguy??n li???u ch??nh l?? h???i s???n nh?? cua, gh???, h??u hay t??m h??m ?????t' ," +
                "'S??? 10 Nguy???n Th??ng, Ph?????ng 7, Qu???n 3, TPHCM'," +
                "'Open','5.0'," +
                "'09:00:00','21:00:00'," +
                "'0123456789')");
        return shops;
    }
    private List<String> listFoods(){
        List<String> foods = new ArrayList<>();
        //Shop 1
        foods.add("INSERT INTO Foods values(null," +
                        "'Susi Nh???t b???n'," +
                        "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F1.jpg?alt=media&token=9b4cd5bb-fbc8-44a5-8dd4-5960b2bd3493'," +
                "'1'," +
                "'Su si si??u ngon','50000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh kh???t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F2.jpg?alt=media&token=57229602-f3cf-4143-a5fb-4d474d402e5f'," +
                "'1'," +
                "'B??nh kh???t si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Hamberger'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F3.jpg?alt=media&token=48df5205-d07d-4c78-a23d-52667cdfda45'," +
                "'1'," +
                "'Hamberger si??u ngon','50000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??n b?? hu??'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F4.jpg?alt=media&token=e17f53d5-9483-4df8-a4af-9c55a039c5a9'," +
                "'1'," +
                "'B??n b?? si??u ngon','70000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh tuy???t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F5.jpg?alt=media&token=f5861de6-f444-4ded-ae16-0c1f6756b0bf'," +
                "'1'," +
                "'B??nh tuy???t si??u ngon','40000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh m??'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F6.jpg?alt=media&token=e6bb6ce8-f245-4d4b-a0c0-0d0d32e929b1'," +
                "'1'," +
                "'B??nh m?? si??u ngon','10000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B?? b??t t???t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F7.jpg?alt=media&token=4db786c9-0cd0-48ad-9b3d-35404cefbb6d'," +
                "'1'," +
                "'B?? b??t t???t si??u ngon','200000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Ch??? l???i'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F8.jpg?alt=media&token=fa23c333-8f0c-4b93-9a97-eda910351edf'," +
                "'1'," +
                "'Ch??? l???i si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'b??n L???u n???m'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F9.jpg?alt=media&token=5771b1e2-3ac6-4d83-bf8e-dafc3337cc77'," +
                "'1'," +
                "'L???u n???m si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'T??m n?????ng'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F10.jpg?alt=media&token=609323f5-b4b4-44df-b1b3-70cc376dba01'," +
                "'1'," +
                "'T??m n?????ng si??u ngon','10000.0','V???n c??n h??ng')");

        //Shop 2

        foods.add("INSERT INTO Foods values(null," +
                "'Toboki Nh???t b???n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F1.jpg?alt=media&token=1c15b1ce-3139-40b3-ac0d-0a3c0aae33f8'," +
                "'2'," +
                "'Toboki si??u ngon','50000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh X??o'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F2.jpg?alt=media&token=656d129c-d2ae-4a02-9f81-4c5ce2d19337'," +
                "'2'," +
                "'B??nh X??o si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Khoai t??y chi??n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F3.jpg?alt=media&token=bb8d2523-291a-4299-8e9f-259c527501bd'," +
                "'2'," +
                "'Khoai t??y chi??n si??u ngon','50000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'b??n b?? hu??'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F4.jpg?alt=media&token=576600cf-ce23-403f-be6a-516e143e51d6'," +
                "'2'," +
                "'B??n b?? si??u ngon','70000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh tuy???t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F5.jpg?alt=media&token=f470eb87-1e94-4c4c-8108-c6f21b8d9b28'," +
                "'2'," +
                "'B??nh tuy???t si??u ngon','40000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh m??'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F6.jpg?alt=media&token=6005b307-5777-414b-abda-22f62f868d14'," +
                "'2'," +
                "'B??nh m?? si??u ngon','10000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B?? b??t t???t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F8.jpg?alt=media&token=ba68e7d1-f51e-4f95-a926-919cb1cd1fce'," +
                "'2'," +
                "'B?? b??t t???t si??u ngon','200000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Ch??? l???i'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F9.jpg?alt=media&token=94a410fc-8727-4b26-9fbf-ece050f9a5c5'," +
                "'2'," +
                "'Ch??? l???i si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'b??n L???u n???m'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F7.jpg?alt=media&token=90b25798-b436-4681-b65b-1b4ab88cbf11'," +
                "'2'," +
                "'L???u n???m si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'T??m n?????ng'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F10.jpg?alt=media&token=6a53dc30-f76f-493c-9adb-858d649e7a5f'," +
                "'2'," +
                "'T??m n?????ng si??u ngon','10000.0','V???n c??n h??ng')");

        //Shop 3

        foods.add("INSERT INTO Foods values(null," +
                "'Susi H??n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F1.jpg?alt=media&token=0f6b1531-3432-44f5-806e-96837153ba1b'," +
                "'3'," +
                "'Susi si??u ngon','50000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh t??t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F2.jpg?alt=media&token=2ae6598c-0ece-4aaa-8ffd-5fc81da4c565'," +
                "'3'," +
                "'B??nh t??t si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Hamberger'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F3.jpg?alt=media&token=e5084364-70fe-4593-b2f7-9145d1f1736e'," +
                "'3'," +
                "'Hamberger si??u ngon','50000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??n ri??u cua'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F4.jpg?alt=media&token=4dc041ed-725a-4506-96a0-bd5e814f6345'," +
                "'3'," +
                "'B??n ri??u cua si??u ngon','70000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh plan'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F5.jpg?alt=media&token=ddecb8b9-13dd-4539-b8d4-623b76a4ea7f'," +
                "'3'," +
                "'B??nh plan si??u ngon','40000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh m??'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F6.jpg?alt=media&token=967becae-8405-4cde-a3f4-74fbce180fd5'," +
                "'3'," +
                "'B??nh m?? si??u ngon','10000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B?? b??t t???t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F7.jpg?alt=media&token=29bcc124-9283-4c12-8cc8-e47c6baac595'," +
                "'3'," +
                "'B?? b??t t???t si??u ngon','200000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Ch??? l???i'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F8.jpg?alt=media&token=8d28cd34-4856-4054-975d-c36d72693ec7'," +
                "'3'," +
                "'Ch??? l???i si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'b??n L???u n???m'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F9.jpg?alt=media&token=43fbf772-90a9-4103-84e6-73c314c762ad'," +
                "'3'," +
                "'L???u n???m si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'T??m n?????ng'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F10.jpg?alt=media&token=be53e667-bf2b-4004-b4c7-380e0f173ee1'," +
                "'3'," +
                "'T??m n?????ng si??u ngon','10000.0','V???n c??n h??ng')");

        //Shop 4

        foods.add("INSERT INTO Foods values(null," +
                "'Susi Nh???t b???n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F1.jpg?alt=media&token=9b4cd5bb-fbc8-44a5-8dd4-5960b2bd3493'," +
                "'4'," +
                "'Su si si??u ngon','50000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh kh???t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F2.jpg?alt=media&token=57229602-f3cf-4143-a5fb-4d474d402e5f'," +
                "'4'," +
                "'B??nh kh???t si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Hamberger'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F3.jpg?alt=media&token=48df5205-d07d-4c78-a23d-52667cdfda45'," +
                "'4'," +
                "'Hamberger si??u ngon','50000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??n b?? hu??'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F4.jpg?alt=media&token=e17f53d5-9483-4df8-a4af-9c55a039c5a9'," +
                "'4'," +
                "'B??n b?? si??u ngon','70000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh tuy???t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F5.jpg?alt=media&token=f5861de6-f444-4ded-ae16-0c1f6756b0bf'," +
                "'4'," +
                "'B??nh tuy???t si??u ngon','40000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh m??'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F6.jpg?alt=media&token=e6bb6ce8-f245-4d4b-a0c0-0d0d32e929b1'," +
                "'4'," +
                "'B??nh m?? si??u ngon','10000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B?? b??t t???t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F7.jpg?alt=media&token=4db786c9-0cd0-48ad-9b3d-35404cefbb6d'," +
                "'4'," +
                "'B?? b??t t???t si??u ngon','200000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Ch??? l???i'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F8.jpg?alt=media&token=fa23c333-8f0c-4b93-9a97-eda910351edf'," +
                "'4'," +
                "'Ch??? l???i si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'b??n L???u n???m'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F9.jpg?alt=media&token=5771b1e2-3ac6-4d83-bf8e-dafc3337cc77'," +
                "'4'," +
                "'L???u n???m si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'T??m n?????ng'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F10.jpg?alt=media&token=609323f5-b4b4-44df-b1b3-70cc376dba01'," +
                "'4'," +
                "'T??m n?????ng si??u ngon','10000.0','V???n c??n h??ng')");

        //Shop 5
        foods.add("INSERT INTO Foods values(null," +
                "'Toboki Nh???t b???n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F1.jpg?alt=media&token=1c15b1ce-3139-40b3-ac0d-0a3c0aae33f8'," +
                "'5'," +
                "'Toboki si??u ngon','50000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh X??o'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F2.jpg?alt=media&token=656d129c-d2ae-4a02-9f81-4c5ce2d19337'," +
                "'5'," +
                "'B??nh X??o si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Khoai t??y chi??n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F3.jpg?alt=media&token=bb8d2523-291a-4299-8e9f-259c527501bd'," +
                "'5'," +
                "'Khoai t??y chi??n si??u ngon','50000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'b??n b?? hu??'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F4.jpg?alt=media&token=576600cf-ce23-403f-be6a-516e143e51d6'," +
                "'5'," +
                "'B??n b?? si??u ngon','70000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh tuy???t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F5.jpg?alt=media&token=f470eb87-1e94-4c4c-8108-c6f21b8d9b28'," +
                "'5'," +
                "'B??nh tuy???t si??u ngon','40000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh m??'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F6.jpg?alt=media&token=6005b307-5777-414b-abda-22f62f868d14'," +
                "'5'," +
                "'B??nh m?? si??u ngon','10000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B?? b??t t???t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F8.jpg?alt=media&token=ba68e7d1-f51e-4f95-a926-919cb1cd1fce'," +
                "'5'," +
                "'B?? b??t t???t si??u ngon','200000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Ch??? l???i'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F9.jpg?alt=media&token=94a410fc-8727-4b26-9fbf-ece050f9a5c5'," +
                "'5'," +
                "'Ch??? l???i si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'b??n L???u n???m'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F7.jpg?alt=media&token=90b25798-b436-4681-b65b-1b4ab88cbf11'," +
                "'5'," +
                "'L???u n???m si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'T??m n?????ng'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F10.jpg?alt=media&token=6a53dc30-f76f-493c-9adb-858d649e7a5f'," +
                "'5'," +
                "'T??m n?????ng si??u ngon','10000.0','V???n c??n h??ng')");

        //Shop 6

        foods.add("INSERT INTO Foods values(null," +
                "'Susi H??n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F1.jpg?alt=media&token=0f6b1531-3432-44f5-806e-96837153ba1b'," +
                "'6'," +
                "'Susi si??u ngon','50000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh t??t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F2.jpg?alt=media&token=2ae6598c-0ece-4aaa-8ffd-5fc81da4c565'," +
                "'6'," +
                "'B??nh t??t si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Hamberger'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F3.jpg?alt=media&token=e5084364-70fe-4593-b2f7-9145d1f1736e'," +
                "'6'," +
                "'Hamberger si??u ngon','50000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??n ri??u cua'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F4.jpg?alt=media&token=4dc041ed-725a-4506-96a0-bd5e814f6345'," +
                "'6'," +
                "'B??n ri??u cua si??u ngon','70000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh plan'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F5.jpg?alt=media&token=ddecb8b9-13dd-4539-b8d4-623b76a4ea7f'," +
                "'6'," +
                "'B??nh plan si??u ngon','40000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh m??'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F6.jpg?alt=media&token=967becae-8405-4cde-a3f4-74fbce180fd5'," +
                "'6'," +
                "'B??nh m?? si??u ngon','10000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B?? b??t t???t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F7.jpg?alt=media&token=29bcc124-9283-4c12-8cc8-e47c6baac595'," +
                "'6'," +
                "'B?? b??t t???t si??u ngon','200000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Ch??? l???i'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F8.jpg?alt=media&token=8d28cd34-4856-4054-975d-c36d72693ec7'," +
                "'6'," +
                "'Ch??? l???i si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'b??n L???u n???m'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F9.jpg?alt=media&token=43fbf772-90a9-4103-84e6-73c314c762ad'," +
                "'6'," +
                "'L???u n???m si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'T??m n?????ng'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F10.jpg?alt=media&token=be53e667-bf2b-4004-b4c7-380e0f173ee1'," +
                "'6'," +
                "'T??m n?????ng si??u ngon','10000.0','V???n c??n h??ng')");


        //Shop 7
        foods.add("INSERT INTO Foods values(null," +
                "'Susi Nh???t b???n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F1.jpg?alt=media&token=9b4cd5bb-fbc8-44a5-8dd4-5960b2bd3493'," +
                "'7'," +
                "'Su si si??u ngon','50000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh kh???t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F2.jpg?alt=media&token=57229602-f3cf-4143-a5fb-4d474d402e5f'," +
                "'7'," +
                "'B??nh kh???t si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Hamberger'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F3.jpg?alt=media&token=48df5205-d07d-4c78-a23d-52667cdfda45'," +
                "'7'," +
                "'Hamberger si??u ngon','50000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??n b?? hu??'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F4.jpg?alt=media&token=e17f53d5-9483-4df8-a4af-9c55a039c5a9'," +
                "'7'," +
                "'B??n b?? si??u ngon','70000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh tuy???t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F5.jpg?alt=media&token=f5861de6-f444-4ded-ae16-0c1f6756b0bf'," +
                "'7'," +
                "'B??nh tuy???t si??u ngon','40000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh m??'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F6.jpg?alt=media&token=e6bb6ce8-f245-4d4b-a0c0-0d0d32e929b1'," +
                "'7'," +
                "'B??nh m?? si??u ngon','10000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B?? b??t t???t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F7.jpg?alt=media&token=4db786c9-0cd0-48ad-9b3d-35404cefbb6d'," +
                "'7'," +
                "'B?? b??t t???t si??u ngon','200000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Ch??? l???i'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F8.jpg?alt=media&token=fa23c333-8f0c-4b93-9a97-eda910351edf'," +
                "'7'," +
                "'Ch??? l???i si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'b??n L???u n???m'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F9.jpg?alt=media&token=5771b1e2-3ac6-4d83-bf8e-dafc3337cc77'," +
                "'7'," +
                "'L???u n???m si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'T??m n?????ng'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F10.jpg?alt=media&token=609323f5-b4b4-44df-b1b3-70cc376dba01'," +
                "'7'," +
                "'T??m n?????ng si??u ngon','10000.0','V???n c??n h??ng')");
        //Shop 8
        foods.add("INSERT INTO Foods values(null," +
                "'Toboki Nh???t b???n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F1.jpg?alt=media&token=1c15b1ce-3139-40b3-ac0d-0a3c0aae33f8'," +
                "'8'," +
                "'Toboki si??u ngon','50000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh X??o'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F2.jpg?alt=media&token=656d129c-d2ae-4a02-9f81-4c5ce2d19337'," +
                "'8'," +
                "'B??nh X??o si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Khoai t??y chi??n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F3.jpg?alt=media&token=bb8d2523-291a-4299-8e9f-259c527501bd'," +
                "'8'," +
                "'Khoai t??y chi??n si??u ngon','50000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'b??n b?? hu??'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F4.jpg?alt=media&token=576600cf-ce23-403f-be6a-516e143e51d6'," +
                "'8'," +
                "'B??n b?? si??u ngon','70000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh tuy???t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F5.jpg?alt=media&token=f470eb87-1e94-4c4c-8108-c6f21b8d9b28'," +
                "'8'," +
                "'B??nh tuy???t si??u ngon','40000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh m??'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F6.jpg?alt=media&token=6005b307-5777-414b-abda-22f62f868d14'," +
                "'8'," +
                "'B??nh m?? si??u ngon','10000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B?? b??t t???t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F8.jpg?alt=media&token=ba68e7d1-f51e-4f95-a926-919cb1cd1fce'," +
                "'8'," +
                "'B?? b??t t???t si??u ngon','200000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Ch??? l???i'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F9.jpg?alt=media&token=94a410fc-8727-4b26-9fbf-ece050f9a5c5'," +
                "'8'," +
                "'Ch??? l???i si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'b??n L???u n???m'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F7.jpg?alt=media&token=90b25798-b436-4681-b65b-1b4ab88cbf11'," +
                "'8'," +
                "'L???u n???m si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'T??m n?????ng'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/2%2F10.jpg?alt=media&token=6a53dc30-f76f-493c-9adb-858d649e7a5f'," +
                "'8'," +
                "'T??m n?????ng si??u ngon','10000.0','V???n c??n h??ng')");

        //Shop 9
        foods.add("INSERT INTO Foods values(null," +
                "'Susi H??n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F1.jpg?alt=media&token=0f6b1531-3432-44f5-806e-96837153ba1b'," +
                "'9'," +
                "'Susi si??u ngon','50000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh t??t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F2.jpg?alt=media&token=2ae6598c-0ece-4aaa-8ffd-5fc81da4c565'," +
                "'9'," +
                "'B??nh t??t si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Hamberger'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F3.jpg?alt=media&token=e5084364-70fe-4593-b2f7-9145d1f1736e'," +
                "'9'," +
                "'Hamberger si??u ngon','50000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??n ri??u cua'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F4.jpg?alt=media&token=4dc041ed-725a-4506-96a0-bd5e814f6345'," +
                "'9'," +
                "'B??n ri??u cua si??u ngon','70000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh plan'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F5.jpg?alt=media&token=ddecb8b9-13dd-4539-b8d4-623b76a4ea7f'," +
                "'9'," +
                "'B??nh plan si??u ngon','40000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh m??'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F6.jpg?alt=media&token=967becae-8405-4cde-a3f4-74fbce180fd5'," +
                "'9'," +
                "'B??nh m?? si??u ngon','10000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B?? b??t t???t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F7.jpg?alt=media&token=29bcc124-9283-4c12-8cc8-e47c6baac595'," +
                "'9'," +
                "'B?? b??t t???t si??u ngon','200000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Ch??? l???i'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F8.jpg?alt=media&token=8d28cd34-4856-4054-975d-c36d72693ec7'," +
                "'9'," +
                "'Ch??? l???i si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'b??n L???u n???m'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F9.jpg?alt=media&token=43fbf772-90a9-4103-84e6-73c314c762ad'," +
                "'9'," +
                "'L???u n???m si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'T??m n?????ng'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/3%2F10.jpg?alt=media&token=be53e667-bf2b-4004-b4c7-380e0f173ee1'," +
                "'9'," +
                "'T??m n?????ng si??u ngon','10000.0','V???n c??n h??ng')");

        //Shop 10
        foods.add("INSERT INTO Foods values(null," +
                "'Susi Nh???t b???n'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F1.jpg?alt=media&token=9b4cd5bb-fbc8-44a5-8dd4-5960b2bd3493'," +
                "'10'," +
                "'Su si si??u ngon','50000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh kh???t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F2.jpg?alt=media&token=57229602-f3cf-4143-a5fb-4d474d402e5f'," +
                "'10'," +
                "'B??nh kh???t si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Hamberger'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F3.jpg?alt=media&token=48df5205-d07d-4c78-a23d-52667cdfda45'," +
                "'10'," +
                "'Hamberger si??u ngon','50000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??n b?? hu??'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F4.jpg?alt=media&token=e17f53d5-9483-4df8-a4af-9c55a039c5a9'," +
                "'10'," +
                "'B??n b?? si??u ngon','70000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh tuy???t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F5.jpg?alt=media&token=f5861de6-f444-4ded-ae16-0c1f6756b0bf'," +
                "'10'," +
                "'B??nh tuy???t si??u ngon','40000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B??nh m??'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F6.jpg?alt=media&token=e6bb6ce8-f245-4d4b-a0c0-0d0d32e929b1'," +
                "'10'," +
                "'B??nh m?? si??u ngon','10000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'B?? b??t t???t'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F7.jpg?alt=media&token=4db786c9-0cd0-48ad-9b3d-35404cefbb6d'," +
                "'10'," +
                "'B?? b??t t???t si??u ngon','200000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'Ch??? l???i'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F8.jpg?alt=media&token=fa23c333-8f0c-4b93-9a97-eda910351edf'," +
                "'10'," +
                "'Ch??? l???i si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'b??n L???u n???m'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F9.jpg?alt=media&token=5771b1e2-3ac6-4d83-bf8e-dafc3337cc77'," +
                "'10'," +
                "'L???u n???m si??u ngon','30000.0','V???n c??n h??ng')");

        foods.add("INSERT INTO Foods values(null," +
                "'T??m n?????ng'," +
                "'https://firebasestorage.googleapis.com/v0/b/foody-eece8.appspot.com/o/1%2F10.jpg?alt=media&token=609323f5-b4b4-44df-b1b3-70cc376dba01'," +
                "'10'," +
                "'T??m n?????ng si??u ngon','10000.0','V???n c??n h??ng')");
        return foods;
    }
}
