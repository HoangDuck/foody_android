package hcmute.edu.vn.foody_08.service;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;
import java.util.stream.Collectors;

import hcmute.edu.vn.foody_08.model.DAO.ShopDAO;
import hcmute.edu.vn.foody_08.model.DTO.payload.LoginDTO;
import hcmute.edu.vn.foody_08.model.DTO.response.viewShopDTO;
import hcmute.edu.vn.foody_08.model.Shop;
import hcmute.edu.vn.foody_08.model.User;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ShopSevice {
    private ShopDAO shopDAO;


    public ShopSevice(Context context){
        shopDAO = new ShopDAO(context);
    }


    public List<Shop> getAllShops(){
        return shopDAO.getListShops();
    }

    public List<Shop> getAllShopsBy(String key, String value){
        return shopDAO.getListShopsBy(key,value);
    }

    public List<viewShopDTO> getAllShopsGridView(){
        return shopDAO.getListShops().stream().map(x->new viewShopDTO(x.getImage(),x.getName())).collect(Collectors.toList());
    }
    public void createShop(Shop shop){
        shopDAO.createShop(shop);
    }
}
