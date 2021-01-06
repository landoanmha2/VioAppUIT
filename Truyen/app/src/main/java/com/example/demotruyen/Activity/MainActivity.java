package com.example.demotruyen.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.demotruyen.Adapter.MainViewPagerAdapter;
import com.example.demotruyen.Fragment.Fragment_Tim_Kiem;
import com.example.demotruyen.Fragment.Fragment_Trang_Chu;
import com.example.demotruyen.Fragment.Fragment_User;
import com.example.demotruyen.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    //Khai báo biến
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //logo();

        anhXa();
        intit();

        //Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //Dùng để ánh xạ lên layout
    private void anhXa() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
    //Phương thức dùng để khởi tạo
    private void intit() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Trang_Chu(), "TRANG CHỦ");
        mainViewPagerAdapter.addFragment(new Fragment_Tim_Kiem(), "TÌM KIẾM");
        mainViewPagerAdapter.addFragment(new Fragment_User(), "NGƯỜI DÙNG");

        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.icontimkiem);
        tabLayout.getTabAt(2).setIcon(R.drawable.iconuserv2);
        
    }
}