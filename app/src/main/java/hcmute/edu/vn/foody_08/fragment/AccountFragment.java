package hcmute.edu.vn.foody_08.fragment;

import static hcmute.edu.vn.foody_08.ultil.constant.GLOBAL_USER_ID;

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
import hcmute.edu.vn.foody_08.model.User;
import hcmute.edu.vn.foody_08.service.ShareReferences;
import hcmute.edu.vn.foody_08.view.LoginRegisterActivity;

public class AccountFragment extends Fragment {
    TextView txtUserInfo,txtUserInfoLogout,txtLogout;
    ShareReferences shareReferences;
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
        shareReferences=ShareReferences.getInstance(getContext());
        txtLogout=view.findViewById(R.id.txtLogout);
        try {
            shareReferences.getGlobalUser();
            txtLogout.setText("Đăng xuất");
        }catch (Exception e){
            txtLogout.setText("Đăng nhập");
        }
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
        txtUserInfoLogout.setOnClickListener(view1 -> {
            clearUserData();
            txtLogout.setText("Đăng nhập");
            Intent intent=new Intent(view1.getContext(), LoginRegisterActivity.class);
            startActivity(intent);
        });
    }

    private void clearUserData() {
        shareReferences.DeleteGlobalUser();
    }

    private Boolean checkLogin() {
        try{
            User user=shareReferences.getGlobalUser();
            if(user==null){
                Intent intent=new Intent(getContext(), LoginRegisterActivity.class);
                startActivity(intent);
                return false;
            }
            else {
                txtLogout.setText("Đăng xuất");
            }
        }catch (Exception e){
            Intent intent=new Intent(getContext(), LoginRegisterActivity.class);
            startActivity(intent);
            return false;
        }
        return true;
    }

}