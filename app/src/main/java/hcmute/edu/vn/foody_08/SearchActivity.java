package hcmute.edu.vn.foody_08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_08.adapter.TemAdapter;

public class SearchActivity extends AppCompatActivity {
    List listthucan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        GridView gridView = findViewById(R.id.grid);
        listthucan = new ArrayList<>();
        for(int i =0;i<100;i++)
            listthucan.add(i);
        TemAdapter footItemAdapter = new TemAdapter(this, R.layout.activity_item_search,listthucan);
        gridView.setAdapter(footItemAdapter);
        footItemAdapter.notifyDataSetChanged();
    }

    public void back(View view) {
        finish();
    }
}