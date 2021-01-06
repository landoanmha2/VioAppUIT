package com.example.demotruyen.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.demotruyen.Adapter.BinhLuanAdapter;
import com.example.demotruyen.Adapter.DanhsachtruyenAdapter;
import com.example.demotruyen.Model.BinhLuanTruyen;
import com.example.demotruyen.Model.Truyen;
import com.example.demotruyen.R;
import com.example.demotruyen.Service.APIService;
import com.example.demotruyen.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BinhLuanActivity extends AppCompatActivity {

    Context context;
    String binhluantext;

    EditText addcomment;
    ImageView imageViewprofile;
    TextView post;
    RecyclerView recyclerViewnoidungbinhluan;

    String postid;
    String publishsherid;

    Toolbar toolbar;

    BinhLuanTruyen binhLuanTruyen;
    Truyen truyen;
    ArrayList<BinhLuanTruyen> mangtruyenbinhluan;
    BinhLuanAdapter binhLuanAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binh_luan);

        toolbar = findViewById(R.id.toolbarbinhluan);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Bình Luận");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addcomment = findViewById(R.id.editextaddcomment);
        imageViewprofile = findViewById(R.id.imageviewnguoidung);
        post = findViewById(R.id.textviewpost);
        recyclerViewnoidungbinhluan = findViewById(R.id.recyclerviewbinhluan);
        //Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DataIntent();
        GetComment();

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addcomment.getText().toString().equals("")) {
                    Toast.makeText(BinhLuanActivity.this,"Không thể bình luận bằng khoảng trống", Toast.LENGTH_SHORT).show();
                }else {
                    binhluantext = addcomment.getText().toString();
                    postcomment();
                }
            }
        });
    }
    //Chưa chỉnh lổi bitmap
    private void postcomment() {
        Dataservice dataservice = APIService.getService();
        Call<String> callback = dataservice.PostBinhLuan(binhluantext);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String kq = response.body();
                if(kq.equals("Success")) {
                    Toast.makeText(getApplicationContext(), "Đã đăng bình luận", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Lỗi ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Thất Bại rồi", "Thử lại đi BinhLuanActivity");
            }
        });
    }
    private void GetComment() {
        Dataservice dataservice = APIService.getService();
        Call<List<BinhLuanTruyen>> callback = dataservice.GetBinhLuan();
        callback.enqueue(new Callback<List<BinhLuanTruyen>>() {
            @Override
            public void onResponse(Call<List<BinhLuanTruyen>> call, Response<List<BinhLuanTruyen>> response) {
                mangtruyenbinhluan = (ArrayList<BinhLuanTruyen>) response.body();
                /*Log.d("Thành công rồi", binhLuanTruyen.getTenTruyen());
                Log.d("Thành công rồi", truyen.getTenTruyen());*/
                binhLuanAdapter = new BinhLuanAdapter(BinhLuanActivity.this, mangtruyenbinhluan);
                recyclerViewnoidungbinhluan.setLayoutManager(new LinearLayoutManager(BinhLuanActivity.this));
                recyclerViewnoidungbinhluan.setAdapter(binhLuanAdapter);
            }

            @Override
            public void onFailure(Call<List<BinhLuanTruyen>> call, Throwable t) {
                Log.d("Thất Bại rồi", "Thử lại đi BinhLuanActivity");
            }
        });
    }

    private void DataIntent() {
        Intent intent = getIntent(); // Lấy giá trị từ Intent
        if(intent != null) {
            if(intent.hasExtra("binhluan")) {
                truyen = (Truyen) intent.getSerializableExtra("binhluan");
            }
        }
    }
}