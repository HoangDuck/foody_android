package hcmute.edu.vn.foody_08.view;

import static hcmute.edu.vn.foody_08.ultil.constant.USER_EMAIL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hcmute.edu.vn.foody_08.MainActivity;
import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.fragment.HomeFragment;
import hcmute.edu.vn.foody_08.model.DTO.payload.LoginDTO;
import hcmute.edu.vn.foody_08.model.User;
import hcmute.edu.vn.foody_08.service.ShareReferences;
import hcmute.edu.vn.foody_08.service.UserService;

public class LoginRegisterActivity extends AppCompatActivity {
    UserService userService = new UserService(this);
    EditText txt_email,txt_password;
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        addControl();
        addEvent();
    }

    private void addEvent() {
        btn_login.setOnClickListener(view -> {
            String email=txt_email.getText().toString();
            String password=txt_password.getText().toString();
            if(email.trim().equals("")||password.trim().equals("")){
                return;
            }
            else if(checkAccount(email,password)){
                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this,"Wrong email or password",Toast.LENGTH_SHORT);
                return;
            }
        });
    }

    private boolean checkAccount(String email, String password) {
        LoginDTO loginDTO = new LoginDTO(email,password);
        User user = userService.Login(loginDTO);
        if (user == null)
        {
            user =userService.getUserByEmail(email);
            if(user != null)
                return false;
            else
            {
                user = new User("new user","new URL",email,password,"new address","Male","None");
                userService.createUser(user);
            }

        }
        ShareReferences.getInstance(this).SaveGlobalUser(user);
        return true;
    }

    private void addControl() {
        txt_email=findViewById(R.id.txt_email);
        txt_password=findViewById(R.id.txt_password);
        btn_login=findViewById(R.id.btn_login_with_your_account);
    }

    public void closePageLogin(View view) {
        finish();
    }

    public void forgotPassword(View view) {
        Intent intent=new Intent(this,ForgotPasswordActivity.class);
        startActivity(intent);
    }
}