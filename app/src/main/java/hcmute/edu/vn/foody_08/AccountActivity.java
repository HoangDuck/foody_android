package hcmute.edu.vn.foody_08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class AccountActivity extends AppCompatActivity {
    LinearLayout linearLayoutUserInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        setView();
        setOnclick();
    }

    protected void setView(){
        linearLayoutUserInfo = findViewById(R.id.LinearUserInfo);
    }
    protected void setOnclick(){
        linearLayoutUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearch();
            }
        });

    }

    protected void openSearch(){
        Intent intent=new Intent(this, UserInfoActivity.class);
        startActivity(intent);
    }
}