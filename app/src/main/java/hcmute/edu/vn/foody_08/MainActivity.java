package hcmute.edu.vn.foody_08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import hcmute.edu.vn.foody_08.fragment.AccountFragment;
import hcmute.edu.vn.foody_08.fragment.HomeFragment;
import hcmute.edu.vn.foody_08.view.LoginRegisterActivity;
import hcmute.edu.vn.foody_08.view.OrderActivity;
import hcmute.edu.vn.foody_08.view.OrderAllCartActivity;
import hcmute.edu.vn.foody_08.view.PaymentActivity;
import hcmute.edu.vn.foody_08.view.RestaurantActivity;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigation;
    private NavigationBarView.OnItemSelectedListener navListener = item -> {
        Fragment selectedFragment;
        System.out.println(item.getItemId());
        switch (item.getItemId()){
            case R.id.home: {
                selectedFragment = new HomeFragment();
                break;
            }
            case R.id.account: {
                selectedFragment = new AccountFragment();
                break;
            }
            default:
                selectedFragment = new AccountFragment();
                break;

        }
        System.out.println(selectedFragment);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flFragment,selectedFragment).commit();
        return true;
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setOnItemSelectedListener(navListener);
        bottomNavigation.setSelectedItemId(R.id.home);

    }
}