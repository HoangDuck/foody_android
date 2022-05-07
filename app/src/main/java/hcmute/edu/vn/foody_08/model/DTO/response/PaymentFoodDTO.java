package hcmute.edu.vn.foody_08.model.DTO.response;

import hcmute.edu.vn.foody_08.model.Food;

public class PaymentFoodDTO {
    private Food food;
    private int num;

    public PaymentFoodDTO(Food food, int num) {
        this.food = food;
        this.num = num;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
