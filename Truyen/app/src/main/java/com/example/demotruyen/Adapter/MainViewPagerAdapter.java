package com.example.demotruyen.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentController;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    //Mảng này dùng để add vào các Fragment
    private ArrayList<Fragment> arrayFragment = new ArrayList<>();
    //Mảng này dùng để hiện thị các title của Fragment
    private ArrayList<String> arrayTitle = new ArrayList<>();

    public MainViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        //Click tới Fragment nào thì trả về Fragment đó
        return arrayFragment.get(position);
    }

    @Override
    public int getCount() {
        //Trả về size của Fragment
        return arrayFragment.size();
    }
    // Phương thức để addFragmet và hiện thị title của Fragment
    public void addFragment(Fragment fragment, String title) {
        arrayFragment.add(fragment);
        arrayTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        //Trả về tên của Fragment
        return arrayTitle.get(position);
    }
}
