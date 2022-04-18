package hcmute.edu.vn.foody_08.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import hcmute.edu.vn.foody_08.R;

public class OrderActivity extends AppCompatActivity {
    private int quantity=0;
    TextView txt_quantity;
    ImageButton btn_increase, btn_decrease;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        addControl();
        addEvent();
    }

    private void addEvent() {
        btn_increase.setOnClickListener(view -> {
            quantity++;
            txt_quantity.setText("VN ");//bước này khá phiền phức
        });
        btn_decrease.setOnClickListener(view -> {
            quantity--;
            if(quantity>=0){

            }
            txt_quantity.setText("");//bước này khá phiền phức

        });
        txt_quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.equals("0")){

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void addControl() {
        txt_quantity=findViewById(R.id.txt_quantity);
        btn_increase=findViewById(R.id.btn_increase_quantity);
        btn_decrease=findViewById(R.id.btn_decrease_quantity);
    }

    public void goToYourCart(View view) {
        Intent intent=new Intent(this,OrderAllCartActivity.class);
        //truyền đi và nhận lại dữ liệu qua intent dạng json
        startActivity(intent);
    }

    public void closeOrderPage(View view) {
        finish();
    }

    public void addToCartAndFinish(View view) {
        finish();
    }
}