package hcmute.edu.vn.foody_08.model;

import java.io.Serializable;

public class CartItem implements Serializable {
    private int id;
    private String name;
    private int shopId;
    private Double price;
    private int quantity;

    public CartItem(int id, String name, int shopId, Double price, int quantity) {
        this.id = id;
        this.name = name;
        this.shopId = shopId;
        this.price = price;
        this.quantity = quantity;
    }

    public CartItem() {
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

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
