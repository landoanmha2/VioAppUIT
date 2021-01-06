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

import com.example.demotruyen.Activity.DanhsachtruyenActivity2;
import com.example.demotruyen.Model.Truyen;
import com.example.demotruyen.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchTruyenAdapter extends RecyclerView.Adapter<SearchTruyenAdapter.ViewHolder>{
    //Class kế thừa RecyclerView
    //Khai báo biến
    Context context;
    ArrayList<Truyen> mangtruyentimkiem;

    public SearchTruyenAdapter(Context context, ArrayList<Truyen> mangtruyentimkiem) {
        this.context = context;
        this.mangtruyentimkiem = mangtruyentimkiem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Dùng để gán layout
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_search_truyen, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Truyen truyen = mangtruyentimkiem.get(position);
        //Gán dữ liệu text
        holder.txttentruyentimkiem.setText(truyen.getTenTruyen());
        holder.txttheloaitimkiem.setText(truyen.getTenTheLoai());
        holder.txttacgiatimkiem.setText(truyen.getTacGia());
        //Gán dữ liệu hình ảnh
        Picasso.with(context).load(truyen.getLinkAnhBia()).into(holder.imageViewanhbiatimkiem);
        Picasso.with(context).load(truyen.getLinkBackGround()).into(holder.imageViewbackgroundtimkiem);
    }

    @Override
    public int getItemCount() {
        //Trả về dựa theo size mảng đã tìm kiếm được
        return mangtruyentimkiem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Khai báo
        TextView txttentruyentimkiem, txttheloaitimkiem, txttacgiatimkiem;
        ImageView imageViewanhbiatimkiem, imageViewbackgroundtimkiem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Ánh xạ
            txttentruyentimkiem = itemView.findViewById(R.id.textviewtimkiemtentruyen);
            txttheloaitimkiem = itemView.findViewById(R.id.textviewtimkiemtheloai);
            txttacgiatimkiem = itemView.findViewById(R.id.textviewtimkiemtacgia);

            imageViewbackgroundtimkiem = itemView.findViewById(R.id.imageViewbackgroundtimkiem);
            imageViewanhbiatimkiem = itemView.findViewById(R.id.imageViewtimkiem);

            //Bắt sự kiện khi cliecj vào item hiện ra khi tìm kiếm
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Muốn chuyển màn hình gọi phương thức Intent, màn hình đang đứng context
                    // chuyển đến màn hình DanhsachtruyenActivity2
                    Intent intent = new Intent(context, DanhsachtruyenActivity2.class);
                    //Đẩy dữ liệu đi theo từ khóa "itemtruyenlist", muốn chuyển dưới dạng Object Class phải có dạng implements Serializeable
                    intent.putExtra("itemtruyenlist", mangtruyentimkiem.get(getPosition()));
                    //Lệnh chuyển màn hình
                    context.startActivity(intent);
                }
            });
        }
    }
}
