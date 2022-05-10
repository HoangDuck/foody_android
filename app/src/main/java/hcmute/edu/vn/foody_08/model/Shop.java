package hcmute.edu.vn.foody_08.model;

import java.io.Serializable;
import java.time.LocalTime;


public class Shop implements Serializable {
    private int id;
    private String name;
    private String image;
    private String description;
    private String address;
    private String status;
    private Double rating;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String phoneNumber;

    public Shop() {
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Shop(int id, String name, String image, String description, String address, String status, Double rating, LocalTime openTime, LocalTime closeTime, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.address = address;
        this.status = status;
        this.rating = rating;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.phoneNumber = phoneNumber;
    }
    public Shop( String name, String image, String description, String address, String status, Double rating, LocalTime openTime, LocalTime closeTime, String phoneNumber) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.address = address;
        this.status = status;
        this.rating = rating;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.phoneNumber = phoneNumber;
    }
}
