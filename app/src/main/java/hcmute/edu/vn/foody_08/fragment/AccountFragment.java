package hcmute.edu.vn.foody_08.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hcmute.edu.vn.foody_08.R;
import hcmute.edu.vn.foody_08.UserInfoActivity;
import hcmute.edu.vn.foody_08.service.ShareReferences;
import hcmute.edu.vn.foody_08.view.LoginRegisterActivity;

public class AccountFragment extends Fragment {
    TextView txtUserInfo,txtUserInfoLogout;
    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtUserInfo=view.findViewById(R.id.txtUserInfoIcon);
        txtUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkLogin()){
                    Intent intent=new Intent(view.getContext(),UserInfoActivity.class);
                    startActivity(intent);
                }
            }
        });
        txtUserInfoLogout=view.findViewById(R.id.txtLogoutIcon);
        txtUserInfoLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), LoginRegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private Boolean checkLogin() {
        ShareReferences shareReferences=ShareReferences.getInstance(getContext());
        try{
            String user=shareReferences.getData("user");
            if(user==""){
                Intent intent=new Intent(getContext(), LoginRegisterActivity.class);
                startActivity(intent);
                return false;
            }
        }catch (Exception e){
            Intent intent=new Intent(getContext(), LoginRegisterActivity.class);
            startActivity(intent);
            return false;
        }
        return true;
    }

}