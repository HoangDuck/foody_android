package hcmute.edu.vn.foody_08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
    }

    public void editNamePage(View view) {
        Intent intent=new Intent(UserInfoActivity.this,EditNameActivity.class);
        startActivity(intent);
    }

    public void back(View view) {
        finish();
    }
}