package hcmute.edu.vn.foody_08.service;

import android.content.Context;

import hcmute.edu.vn.foody_08.data.DatabaseHelper;
import hcmute.edu.vn.foody_08.model.DAO.UserDAO;
import hcmute.edu.vn.foody_08.model.DTO.payload.LoginDTO;
import hcmute.edu.vn.foody_08.model.User;

public class UserService {
    private UserDAO userDAO;

    public UserService(Context context){
        userDAO= new UserDAO(context);
    }


    public User Login(LoginDTO login){
        return userDAO.CheckLogin(login);
    }
    public User getUserByEmail(String value){
        return userDAO.getUserByEmail(value);
    }

    public void createUser(User user){
         userDAO.createUser(user);
    }
}
