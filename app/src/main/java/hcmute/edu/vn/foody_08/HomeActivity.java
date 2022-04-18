package hcmute.edu.vn.foody_08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    List listthucan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        GridView gridView = findViewById(R.id.grid);
        listthucan = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            listthucan.add(i);
        TemAdapter footItemAdapter = new TemAdapter(this, R.layout.activity_item_home, listthucan);
        gridView.setAdapter(footItemAdapter);
        footItemAdapter.notifyDataSetChanged();
    }
}