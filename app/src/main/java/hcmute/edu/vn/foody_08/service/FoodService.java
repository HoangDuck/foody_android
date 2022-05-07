package hcmute.edu.vn.foody_08.service;

import android.content.Context;

import java.util.List;
import java.util.stream.Collectors;

import hcmute.edu.vn.foody_08.model.DAO.FoodDAO;
import hcmute.edu.vn.foody_08.model.Food;

public class FoodService {
    private FoodDAO foodDAO;


    public FoodService(Context context){
        foodDAO = new FoodDAO(context);
    }

    public List<Food> getAllFoods(){
        return foodDAO.getListFoods();
    }

    public List<Food> getAllFoodsBy(String key, String value){
        return foodDAO.getListFoodsBy(key,value);
    }

    public List<Food> getAllFoodsByIdShop(int id){
        return foodDAO.getListFoodsBy("shopId",String.valueOf(id));
    }

    public void createFood(Food Food){
        foodDAO.createFood(Food);
    }

}
