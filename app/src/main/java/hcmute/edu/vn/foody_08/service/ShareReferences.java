package hcmute.edu.vn.foody_08.service;

import static hcmute.edu.vn.foody_08.ultil.constant.GLOBAL_USER_ADDRESS;
import static hcmute.edu.vn.foody_08.ultil.constant.GLOBAL_USER_AVATAR;
import static hcmute.edu.vn.foody_08.ultil.constant.GLOBAL_USER_EMAIL;
import static hcmute.edu.vn.foody_08.ultil.constant.GLOBAL_USER_GENDER;
import static hcmute.edu.vn.foody_08.ultil.constant.GLOBAL_USER_ID;
import static hcmute.edu.vn.foody_08.ultil.constant.GLOBAL_USER_NAME;
import static hcmute.edu.vn.foody_08.ultil.constant.GLOBAL_USER_PASSWORD;
import static hcmute.edu.vn.foody_08.ultil.constant.GLOBAL_USER_PHONE;
import static hcmute.edu.vn.foody_08.ultil.constant.USER_ID;

import android.content.Context;
import android.content.SharedPreferences;

import hcmute.edu.vn.foody_08.model.User;

public class ShareReferences {
    private static ShareReferences yourPreference;
    private SharedPreferences sharedPreferences;

    public static ShareReferences getInstance(Context context) {
        if (yourPreference == null) {
            yourPreference = new ShareReferences(context);
        }
        return yourPreference;
    }

    private ShareReferences(Context context) {
        sharedPreferences = context.getSharedPreferences("YourCustomNamedPreference", Context.MODE_PRIVATE);
    }

    public void saveData(String key, String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public String getData(String key) {
        if (sharedPreferences != null) {
            return sharedPreferences.getString(key, "");
        }
        return "";
    }

    public void SaveGlobalUser(User user) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(GLOBAL_USER_ID, String.valueOf(user.getId()));
        prefsEditor.putString(GLOBAL_USER_NAME, user.getName());
        prefsEditor.putString(GLOBAL_USER_EMAIL, user.getEmail());
        prefsEditor.putString(GLOBAL_USER_PASSWORD, user.getPassword());
        prefsEditor.putString(GLOBAL_USER_AVATAR, user.getAvatar());
        prefsEditor.putString(GLOBAL_USER_ADDRESS, user.getAddress());
        prefsEditor.putString(GLOBAL_USER_GENDER, user.getGender());
        prefsEditor.putString(GLOBAL_USER_PHONE, user.getPhone());
        prefsEditor.commit();
    }
    public void DeleteGlobalUser() {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(GLOBAL_USER_ID, null);
        prefsEditor.putString(GLOBAL_USER_NAME, null);
        prefsEditor.putString(GLOBAL_USER_EMAIL, null);
        prefsEditor.putString(GLOBAL_USER_PASSWORD, null);
        prefsEditor.putString(GLOBAL_USER_AVATAR, null);
        prefsEditor.putString(GLOBAL_USER_ADDRESS, null);
        prefsEditor.putString(GLOBAL_USER_GENDER, null);
        prefsEditor.putString(GLOBAL_USER_PHONE, null);
        prefsEditor.commit();
    }

    public User getGlobalUser() {
        User user = new User();
        user.setId(Integer.parseInt(getData(GLOBAL_USER_ID)));
        user.setAddress(getData(GLOBAL_USER_ADDRESS));
        user.setAvatar(getData(GLOBAL_USER_AVATAR));
        user.setEmail(getData(GLOBAL_USER_EMAIL));
        user.setPassword(getData(GLOBAL_USER_PASSWORD));
        user.setGender(getData(GLOBAL_USER_GENDER));
        user.setPhone(getData(GLOBAL_USER_PHONE));
        user.setName(getData(GLOBAL_USER_NAME));
      return user;

    }
}
