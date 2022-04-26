package hcmute.edu.vn.foody_08.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.TemAdapter;

public class HomeFragment extends Fragment {
    List listthucan;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GridView gridView = view.findViewById(R.id.grid);
        listthucan = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            listthucan.add(i);
        TemAdapter footItemAdapter = new TemAdapter(getActivity(), R.layout.activity_item_home, listthucan);
        gridView.setAdapter(footItemAdapter);
        footItemAdapter.notifyDataSetChanged();
    }
}