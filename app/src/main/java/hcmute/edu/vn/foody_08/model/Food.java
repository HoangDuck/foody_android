package hcmute.edu.vn.foody_08.model;


import java.io.Serializable;
import java.util.Comparator;

public class Food implements Serializable {
    private int id;
    private String name;
    private String image;
    private int shopId;
    private String description;
    private Double price;
    private String status;

    public Food(int id, String name, String image, int shopId, String description, Double price, String status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.shopId = shopId;
        this.description = description;
        this.price = price;
        this.status = status;
    }
    public Food( String name, String image, int shopId, String description, Double price, String status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.shopId = shopId;
        this.description = description;
        this.price = price;
        this.status = status;
    }
    public Food() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


//    public void InsertToDatabase(Database db){
//
//        String[] params = new String[6] ;
//        params[0]=this.name;
//        params[1]=this.image;
//        params[2]=String.valueOf(this.shopId);
//        params[3]=this.description;
//        params[4]=String.valueOf(this.price);
//        params[5]=this.status;
//        db.ExecQuery("insert into Foods (name,image,shopId,description,price,status) values(?,?,?,?,?,?)",params);
//    }
}
