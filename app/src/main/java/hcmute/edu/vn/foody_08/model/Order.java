package hcmute.edu.vn.foody_08.model;

import java.time.LocalDateTime;
import java.util.Date;


public class Order {
    private int id;
    private int idUser;
    private String status;
    private Double priceTotal;
    private LocalDateTime dateOrder;
    private int numTotal;
    private String address;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(Double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public LocalDateTime getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(LocalDateTime dateOrder) {
        this.dateOrder = dateOrder;
    }

    public int getNumTotal() {
        return numTotal;
    }

    public void setNumTotal(int numTotal) {
        this.numTotal = numTotal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Order(int id, int idUser, String status, Double priceTotal, LocalDateTime dateOrder, int numTotal, String address) {
        this.id = id;
        this.idUser = idUser;
        this.status = status;
        this.priceTotal = priceTotal;
        this.dateOrder = dateOrder;
        this.numTotal = numTotal;
        this.address = address;
    }

    public Order(int idUser, String status, Double priceTotal, LocalDateTime dateOrder, int numTotal, String address) {
        this.idUser = idUser;
        this.status = status;
        this.priceTotal = priceTotal;
        this.dateOrder = dateOrder;
        this.numTotal = numTotal;
        this.address = address;
    }

}
