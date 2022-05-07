package hcmute.edu.vn.foody_08.model;


public class OrderDetail {
    private int id;
    private int idOrder;
    private int idFood;
    private int num;
    private double priceEach;

    public OrderDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFood() {
        return idFood;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(double priceEach) {
        this.priceEach = priceEach;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public OrderDetail(int id, int idOrder, int idFood, int num, double priceEach) {
        this.id = id;
        this.idOrder = idOrder;
        this.idFood = idFood;
        this.num = num;
        this.priceEach = priceEach;
    }
    public OrderDetail( int idOrder, int idFood, int num, double priceEach) {
        this.idOrder = idOrder;
        this.idFood = idFood;
        this.num = num;
        this.priceEach = priceEach;
    }


}
