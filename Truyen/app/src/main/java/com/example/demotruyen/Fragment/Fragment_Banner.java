package com.example.demotruyen.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.demotruyen.Adapter.BannerAdapter;
import com.example.demotruyen.Model.Top3Truyen;
import com.example.demotruyen.Model.Truyen;
import com.example.demotruyen.R;
import com.example.demotruyen.Service.APIService;
import com.example.demotruyen.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Banner extends Fragment {
//Class được kế thừa từ Fragment
    //Khai báo biến
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int currentItem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Phương thức onCreateView được dùng để gán View vào Fragmnet
        view = inflater.inflate(R.layout.fragment_banner, container, false); //gán layout
        AnhXa();

        GetData(); //GetData dùng để đọc dữ liệu
        return view;
    }

    private void AnhXa() {
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.indicatordefault);
    }


    private void GetData() {
        //Khởi tạo lại APIService
        Dataservice dataservice = APIService.getService();
        //Gửi dữ liệu lên theo thương thức GetTop3, trả dữ liệu qua biến callback
        Call<List<Top3Truyen>> callback = dataservice.GetTop3();
        //Lắng nghe cho sự kiện trả về
        callback.enqueue(new Callback<List<Top3Truyen>>() {
            @Override
            public void onResponse(Call<List<Top3Truyen>> call, Response<List<Top3Truyen>> response) {
                //Phương thức onResponse nếu dữ liệu trả về thành công
                ArrayList<Top3Truyen> banners = (ArrayList<Top3Truyen>) response.body(); //Vì dữ liệu trả về là 1 mảng object nên phải nhận bằng 1 mảng object

                //Gán dữ liệu vào
                bannerAdapter = new BannerAdapter(getActivity(), banners);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);

                //Dùng để tự động chuyển mục Quảng Cáo theo 1 thời gian nhất định và chuyển về mục 1 khi chuyển đến cuối cùng
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        currentItem = viewPager.getCurrentItem();
                        currentItem++;
                        if(currentItem >= viewPager.getAdapter().getCount()) {
                            currentItem = 0;
                        }
                        viewPager.setCurrentItem(currentItem, true);
                        handler.postDelayed(runnable, 4500);
                    }
                };
                handler.postDelayed(runnable, 4500);
            }

            @Override
            public void onFailure(Call<List<Top3Truyen>> call, Throwable t) {
                //Phương thức onFailure nếu trả về dữ liệu thất bại
                Log.d("Thất Bại rồi", "Thử lại đi GetData Fragment Banner");
            }
        });
    }
}
