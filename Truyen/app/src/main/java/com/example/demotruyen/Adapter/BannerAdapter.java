package com.example.demotruyen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.demotruyen.Activity.DanhsachtruyenActivity2;
import com.example.demotruyen.Model.Top3Truyen;
import com.example.demotruyen.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import okhttp3.internal.Internal;

public class BannerAdapter extends PagerAdapter {
    //Class này dùng để custom dữ liệu đổ vào ViewPager - MainViewPagerAdapter
    Context context;
    ArrayList<Top3Truyen> arrayListbanner;

    public BannerAdapter(Context context, ArrayList<Top3Truyen> arrayListbanner) {
        this.context = context;
        this.arrayListbanner = arrayListbanner;
    }

    @Override
    public int getCount() {
        //Trả về kích thước của mảng, mảng có 5 items thì trả về 5 items trên mục Quảng Cáo
        return arrayListbanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    //Dùng để định hình Object và gán dữ liệu vào dựa vào view mẫu dong_banner.xml
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        //Gán dữ liệu mẫu vào và gán dữ liệu
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner, null);

        //Ánh xạ
        ImageView imgbackgroundbanner = view.findViewById(R.id.imageViewbackgroundBanner);
        ImageView imgtruyenbanner = view.findViewById(R.id.imageViewbanner);
        TextView txttitleTRUYENbanner = view.findViewById(R.id.textViewtitleBannerTRUYEN);
        TextView txtmota = view.findViewById(R.id.textViewTheLoai);

        //Gán dữ liệu hình ảnh và text
        Picasso.with(context).load(arrayListbanner.get(position).getLinkBackGround()).into(imgbackgroundbanner);
        Picasso.with(context).load(arrayListbanner.get(position).getLinkAnhBia()).into(imgtruyenbanner);
        txttitleTRUYENbanner.setText(arrayListbanner.get(position).getTenTruyen());
        txtmota.setText(arrayListbanner.get(position).getTacGia());

        container.addView(view);

        //Bắt sự kiện khi nhấn vào mục Quảng Cáo chuyển layout
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Muốn chuyển màn hình gọi phương thức Intent, context là màn hình đang đứng
                Intent intent = new Intent(context, DanhsachtruyenActivity2.class);
                //Đẩy dữ liệu đi theo từ khóa "banner", muốn chuyển dưới dạng Object Class phải có dạng implements Serializeable
                intent.putExtra("banner", arrayListbanner.get(position));
                //Lệnh chuyển màn hình
                context.startActivity(intent);
            }
        });
        return view;
    }

    //Sau khi chuyển đến View cuối cùng thì xóa View đi để tránh bị lỗi
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }
}
