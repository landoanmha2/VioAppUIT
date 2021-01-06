package com.example.demotruyen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demotruyen.Model.BinhLuanTruyen;
import com.example.demotruyen.Model.Truyen;
import com.example.demotruyen.R;

import java.util.ArrayList;

public class BinhLuanAdapter extends RecyclerView.Adapter<BinhLuanAdapter.ViewHolder>{

    Context context;
    ArrayList<BinhLuanTruyen> binhLuanTruyenArrayList;

    public BinhLuanAdapter(Context context, ArrayList<BinhLuanTruyen> binhLuanTruyenArrayList) {
        this.context = context;
        this.binhLuanTruyenArrayList = binhLuanTruyenArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.dong_binhluanadapter, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BinhLuanTruyen binhLuanTruyen = binhLuanTruyenArrayList.get(position);

        holder.textViewnoidungbinhluan.setText(binhLuanTruyen.getNdBinhLuan());
        holder.textviewtentruyenbinhluan.setText(binhLuanTruyen.getTenTruyen());
    }

    @Override
    public int getItemCount() {
        return binhLuanTruyenArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewnoidungbinhluan, textviewtentruyenbinhluan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewnoidungbinhluan = itemView.findViewById(R.id.textViewnoidungbinhluan);
            textviewtentruyenbinhluan = itemView.findViewById(R.id.textviewtentruyenbinhluan);
        }
    }
}
