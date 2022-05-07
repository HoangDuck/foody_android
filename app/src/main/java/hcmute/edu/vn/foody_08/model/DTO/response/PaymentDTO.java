package hcmute.edu.vn.foody_08.model.DTO.response;

import java.util.List;

public class PaymentDTO {
    private String address;
    private List<PaymentShopDTO> paymentShopDTOList;
    private Double totalPrice;
    private int totalAmount;

    public PaymentDTO(String address, List<PaymentShopDTO> paymentShopDTOList, Double totalPrice, int totalAmount) {
        this.address = address;
        this.paymentShopDTOList = paymentShopDTOList;
        this.totalPrice = totalPrice;
        this.totalAmount = totalAmount;
    }

    public PaymentDTO() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<PaymentShopDTO> getPaymentShopDTOList() {
        return paymentShopDTOList;
    }

    public void setPaymentShopDTOList(List<PaymentShopDTO> paymentShopDTOList) {
        this.paymentShopDTOList = paymentShopDTOList;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
