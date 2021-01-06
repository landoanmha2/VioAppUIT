package com.example.demotruyen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demotruyen.Activity.BinhLuanActivity;
import com.example.demotruyen.Activity.DocChuongActivity3;
import com.example.demotruyen.Model.Truyen;
import com.example.demotruyen.R;

import java.util.ArrayList;

public class DanhsachtruyenAdapter extends RecyclerView.Adapter<DanhsachtruyenAdapter.ViewHolder> {
    //Kế thừa từ RecyclerView để custom lại RecyclerView
    //Khai bào biến
    Context context;
    ArrayList<Truyen> mangchuongtruyen;

    public DanhsachtruyenAdapter(Context context, ArrayList<Truyen> mangchuongtruyen) {
        this.context = context;
        this.mangchuongtruyen = mangchuongtruyen;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Dùng để khởi tạo cho việc hình thành RecyclerView
        LayoutInflater inflater = LayoutInflater.from(context);
        //Gán layout dong_danhsachtruyenadapter
        View view = inflater.inflate(R.layout.dong_danhsachtruyenadapter, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Dùng để tương tác gán dữ liệu vào
        Truyen truyen = mangchuongtruyen.get(position);
        holder.txtlistchuongacti2.setText(truyen.getTenChuong());
    }

    @Override
    public int getItemCount() {
        //Trả về Size của mảng (có bao nhiêu chương thì trả về bấy nhiêu)
        return mangchuongtruyen.size();
    }

    //Dùng để khai báo và ánh xạ
    public class ViewHolder extends RecyclerView.ViewHolder {
        //Khai báo
        TextView txtlistchuongacti2;
        ImageView imageViewbinhluan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtlistchuongacti2 = itemView.findViewById(R.id.textViewlistchuongacti2);
            imageViewbinhluan = itemView.findViewById(R.id.imageViewbinhluan);
            //Bắt sự kiện khi nhấn vào items trong ListView
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Muốn chuyển màn hình gọi phương thức Intent, context là màn hình đang đứng
                    Intent intent = new Intent(context, DocChuongActivity3.class);
                    //Đẩy dữ liệu đi theo từ khóa "docchuong", muốn chuyển dưới dạng Object Class phải có dạng implements Serializeable
                    intent.putExtra("docchuong", mangchuongtruyen.get(getPosition()));
                    //Lệnh chuyển màn hình
                    context.startActivity(intent);
                }
            });

            imageViewbinhluan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BinhLuanActivity.class);
                    intent.putExtra("binhluan", mangchuongtruyen.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
