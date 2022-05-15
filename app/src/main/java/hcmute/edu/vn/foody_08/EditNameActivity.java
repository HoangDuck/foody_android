package hcmute.edu.vn.foody_08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hcmute.edu.vn.foody_08.model.User;
import hcmute.edu.vn.foody_08.service.ShareReferences;
import hcmute.edu.vn.foody_08.service.UserService;

public class EditNameActivity extends AppCompatActivity {
    UserService userService = new UserService(this);
    EditText txt_edit_name;
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);
        addControl();
        addEvent();
    }

    private void addControl() {
        txt_edit_name=findViewById(R.id.txtUserInfo_edit_name);
        btn_login=findViewById(R.id.btn_save_edit_name);
    }
    private void addEvent() {
        btn_login.setOnClickListener(view -> {
            String name=txt_edit_name.getText().toString();
            if(name.trim().equals("")){
                Toast.makeText(this,"Info is empty",Toast.LENGTH_SHORT);
                return;
            }
            else {
                User user = ShareReferences.getInstance(this).getGlobalUser();
                user.setName(name);
                userService.updateUser(user);
                ShareReferences.getInstance(this).SaveGlobalUser(user);

                Intent intent=new Intent(this, UserInfoActivity.class);
                startActivity(intent);
            }
        });
    }
    public void closePageLogin(View view) {
        finish();
    }

    public void back(View view) {
        finish();
    }

}