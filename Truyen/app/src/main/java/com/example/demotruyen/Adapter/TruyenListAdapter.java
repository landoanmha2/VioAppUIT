package com.example.demotruyen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.demotruyen.Model.Truyen;
import com.example.demotruyen.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TruyenListAdapter extends ArrayAdapter<Truyen> {
    //Dùng để custom dữ liệu cho ListView Truyện, kế thừa từ ArrayAdapter
    public TruyenListAdapter(@NonNull Context context, int resource, @NonNull List<Truyen> objects) {
        super(context, resource, objects);
    }

    class ViewHolder {
        //Khai báo biến
        ImageView imageTruyenList, imgbackground;
        TextView txtViewtentruyen, txtViewtheloai, txtViewtacgia, txtViewtenchuong;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Phương thức getView dùng để gán Items vào cho layout
        ViewHolder viewHolder = null;
        if(convertView == null) {
            //Nếu null thì gán dữ liệu vào
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dong_truyen_list, null);
            viewHolder = new ViewHolder();
            //Ánh xạ
            viewHolder.imageTruyenList = convertView.findViewById(R.id.imageViewtruyenlist);
            viewHolder.imgbackground = convertView.findViewById(R.id.imageViewbackgroundlist);
            viewHolder.txtViewtentruyen = convertView.findViewById(R.id.textViewTenTruyen);
            viewHolder.txtViewtenchuong = convertView.findViewById(R.id.textViewTenChuong);
            viewHolder.txtViewtheloai = convertView.findViewById(R.id.textViewTheLoai);
            viewHolder.txtViewtacgia = convertView.findViewById(R.id.textViewTacGia);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Truyen truyen = getItem(position);
        //Gán dữ liệu hình ảnh
        Picasso.with(getContext()).load(truyen.getLinkBackGround()).into(viewHolder.imgbackground);
        Picasso.with(getContext()).load(truyen.getLinkAnhBia()).into(viewHolder.imageTruyenList);

        //Gán dữ liệu text
        viewHolder.txtViewtentruyen.setText(truyen.getTenTruyen());
        viewHolder.txtViewtenchuong.setText(truyen.getTenChuong());
        viewHolder.txtViewtheloai.setText(truyen.getTenTheLoai());
        viewHolder.txtViewtacgia.setText(truyen.getTacGia());

        return convertView;
    }
}
