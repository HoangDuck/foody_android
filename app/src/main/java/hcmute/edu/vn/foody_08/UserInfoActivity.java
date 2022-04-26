package hcmute.edu.vn.foody_08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class UserInfoActivity extends AppCompatActivity {
    LinearLayout linearLayoutName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        setView();
        setOnclick();
    }
    protected void setView(){
        linearLayoutName = findViewById(R.id.LinearEditName);
    }
    protected void setOnclick(){
        linearLayoutName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditName();
            }
        });

    }

    protected void openEditName(){
        Intent intent=new Intent(this, EditNameActivity.class);
        startActivity(intent);
    }

    public void back(View view) {
        finish();
    }
}