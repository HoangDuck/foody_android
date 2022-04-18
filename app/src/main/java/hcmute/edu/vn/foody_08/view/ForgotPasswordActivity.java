package hcmute.edu.vn.foody_08.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hcmute.edu.vn.foody_08.R;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText txt_email;
    Button btn_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        addControl();
        addEvent();
    }

    private void addEvent() {
        btn_email.setOnClickListener(view -> {
            sendEmail();
            finish();
        });
    }

    private void sendEmail() {

    }

    private void addControl() {
        txt_email=findViewById(R.id.txt_email);
        btn_email=findViewById(R.id.btn_email);
    }

    public void closeForgotPassword(View view) {
        finish();
    }
}