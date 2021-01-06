package com.example.demotruyen.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.demotruyen.Activity.LoginActivity;
import com.example.demotruyen.R;

import static android.content.Context.MODE_PRIVATE;

public class Fragment_User extends Fragment {

    View view;
    TextView textViewtentaikhoan, textViewmatkhau;
    TextView textViewtaikhoan, textViewmatkhautaikhoan;
    //ImageView imageViewHidePass;
    Button buttondangxuat;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "sharedprefname";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_PASSWORD = "keypassword";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);

        textViewtaikhoan = view.findViewById(R.id.textViewtentaikhoan);
        textViewmatkhautaikhoan = view.findViewById(R.id.textViewmatkhauuser);

        textViewtentaikhoan = view.findViewById(R.id.taikhoan);
        textViewmatkhau = view.findViewById(R.id.matkhau);

        buttondangxuat = view.findViewById(R.id.dangxuat);

        //imageViewHidePass = view.findViewById(R.id.imageViewHidePass);

        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String user = sharedPreferences.getString(KEY_USERNAME, null);
        String password = sharedPreferences.getString(KEY_PASSWORD, null);
        textViewtaikhoan.setText(user);
        textViewmatkhautaikhoan.setText(password);



        LogoutAcc();

        return view;
    }

    private void LogoutAcc() {
        buttondangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentlogout = new Intent(getActivity(), LoginActivity.class);
                startActivity(intentlogout);
            }
        });
    }

}
