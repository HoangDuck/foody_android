package hcmute.edu.vn.foody_08;

import static hcmute.edu.vn.foody_08.ultil.constant.GLOBAL_USER_EMAIL;
import static hcmute.edu.vn.foody_08.ultil.constant.GLOBAL_USER_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import hcmute.edu.vn.foody_08.service.ShareReferences;

public class UserInfoActivity extends AppCompatActivity {
    LinearLayout linearLayoutName;
    LinearLayout linearLayoutEmail;
    LinearLayout linearLayoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        setView();
        setOnclick();
    }
    protected void setView(){
        linearLayoutName = findViewById(R.id.LinearEditName);
        linearLayoutEmail= findViewById(R.id.LinearEditNickName);
        linearLayoutPassword= findViewById(R.id.LinearEditpassword);

        TextView textViewName = findViewById(R.id.txtName);
        TextView textViewEmail = findViewById(R.id.txtEmail);
        TextView textViewName2 = findViewById(R.id.textViewNameUser);
        textViewName.setText(ShareReferences.getInstance(this).getData(GLOBAL_USER_NAME));
        textViewEmail.setText(ShareReferences.getInstance(this).getData(GLOBAL_USER_EMAIL));
        textViewName2.setText(ShareReferences.getInstance(this).getData(GLOBAL_USER_NAME));

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