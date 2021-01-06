package com.example.demotruyen.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.demotruyen.R;

public class Fragment_Trang_Chu extends Fragment {
//Muốn custom lại ViewPager phải kế thừa Fragment
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Phương thức onCreateView dùng để gán View
        view = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        //Dùng để gán layout vào cho file Fragment
        return view;
    }
}
