package hcmute.edu.vn.foody_08.model.DTO.response;

import java.util.ArrayList;
import java.util.List;

public class PaymentShopDTO {
    private int id;
    private String name;
    private String adress;
    private List<PaymentFoodDTO> paymentFoodDTOList;


    public PaymentShopDTO(int id, String name, String adress) {
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.paymentFoodDTOList = new ArrayList<>();
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public List<PaymentFoodDTO> getPaymentFoodDTOList() {
        return paymentFoodDTOList;
    }

    public void setPaymentFoodDTOList(List<PaymentFoodDTO> paymentFoodDTOList) {
        this.paymentFoodDTOList = paymentFoodDTOList;
    }
}
