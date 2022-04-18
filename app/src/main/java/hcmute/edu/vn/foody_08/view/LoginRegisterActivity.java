package hcmute.edu.vn.foody_08.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hcmute.edu.vn.foody_08.R;

public class LoginRegisterActivity extends AppCompatActivity {
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
                //Intent intent=new Intent(this,)
            }
        });
    }

    private boolean checkAccount(String email, String password) {
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