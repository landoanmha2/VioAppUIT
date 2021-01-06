package com.example.demotruyen.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demotruyen.Adapter.DanhsachtruyenAdapter;
import com.example.demotruyen.Adapter.TruyenListAdapter;
import com.example.demotruyen.Model.Top3Truyen;
import com.example.demotruyen.Model.Truyen;
import com.example.demotruyen.R;
import com.example.demotruyen.Service.APIService;
import com.example.demotruyen.Service.Dataservice;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtruyenActivity2 extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayoutacti2;
    CoordinatorLayout coordinatorLayoutacti2;
    Toolbar toolbaracti2;
    RecyclerView recyclerViewacti2;
    ImageView imgViewactivity2;
    TextView txttentruyen, txttheloai, txttacgia, txtmota;
    FloatingActionButton floatingActionButton;

    Top3Truyen top3Truyen;
    ArrayList<Truyen> mangtruyenActi2;

    DanhsachtruyenAdapter danhsachtruyenAdapter;

    Truyen truyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtruyen2);
        DataTruyenIntent(); // Dùng để nhận màn hình khi chuyển qua
        anhXa(); //Ánh xạ view
        init(); //Dùng để khởi tạo toolbar
        //Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Khi nhấn vào mục Quảng Cáo
        if(top3Truyen != null && !top3Truyen.getIdTruyen().equals("")) {
            //Nếu như tồn tại và IdTruyen khác rỗng (null) thì sẽ lấy dữ liệu về
            setValueInView(top3Truyen.getTenTruyen(), top3Truyen.getTenTheLoai(), top3Truyen.getTacGia(),
                    top3Truyen.getMoTaTruyen(), top3Truyen.getLinkAnhBia(), top3Truyen.getLinkBackGround()); //Gán dữ liệu lên CollapsingToolbarLayout
            GetDataTop3Truyen(top3Truyen.getIdRank()); //Lấy IdTruyen, Khi ấn vào mục Quảng Cáo
        }
        //Khi nhấn vào items trong ListView
        if(truyen != null && !truyen.getIdTruyen().equals("")) {
            //Nếu như tồn tại và IdTruyen khác rỗng (null) thì sẽ lấy dữ liệu về
            setValueInView(truyen.getTenTruyen(), truyen.getTenTheLoai(), truyen.getTacGia(),
                    truyen.getMoTaTruyen(), truyen.getLinkAnhBia(), truyen.getLinkBackGround()); //Gán dữ liệu lên CollapsingToolbarLayout
            GetDataItemsTruyenList(truyen.getIdTruyen()); //Lấy IdTruyen, Khi ấn vào items trong ListView
        }

    }

    private void setValueInView(String tentruyen, String theloai, String tacgia, String motatruyen, String linkanhbia, String linkbackground) {
        collapsingToolbarLayoutacti2.setTitle("Thông tin truyện"); //Title của CollasingToolbar
        try {
            //Muốn set hình ảnh vào CollasingToolbar thì phải dùng bitmap
            URL url = new URL(linkbackground);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            collapsingToolbarLayoutacti2.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Set hình ảnh cho ảnh bìa
        Picasso.with(this).load(linkanhbia).into(imgViewactivity2);
        //Set dữ liệu cho text
        this.txttentruyen.setText(tentruyen);
        this.txttheloai.setText(theloai);
        this.txttacgia.setText(tacgia);
        this.txtmota.setText(motatruyen);
    }

    private void GetDataTop3Truyen(String idrank) {
        //Khởi tạo lại APIService
        Dataservice dataservice = APIService.getService();
        //Gửi dữ liệu lên theo thương thức GetDanhSachTruyenActi2 với gửi từ khóa idrank đã được lấy dữ liệu lên Server
        // trả dữ liệu qua biến callback
        Call<List<Truyen>> callback = dataservice.GetDanhSachTruyenActi2(idrank);
        //Lắng nghe cho sự kiện trả về
        callback.enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(Call<List<Truyen>> call, Response<List<Truyen>> response) {
                //Nếu thành công
                mangtruyenActi2 = (ArrayList<Truyen>) response.body();

                //Gán dữ liệu vào
                danhsachtruyenAdapter = new DanhsachtruyenAdapter(DanhsachtruyenActivity2.this, mangtruyenActi2);
                recyclerViewacti2.setLayoutManager(new LinearLayoutManager(DanhsachtruyenActivity2.this));
                recyclerViewacti2.setAdapter(danhsachtruyenAdapter);

            }
            @Override
            public void onFailure(Call<List<Truyen>> call, Throwable t) {
                //Nếu thất bại
                Log.d("Thất Bại rồi", "Thử lại đi GetDataTop3 DanhsachtruyenActi2");
            }
        });
    }

    private void GetDataItemsTruyenList(String idtruyen) {
        //Khởi tạo lại APIService
        Dataservice dataservice = APIService.getService();
        //Gửi dữ liệu lên theo thương thức GetDanhSachTruyen với gửi từ khóa idtruyen đã được lấy dữ liệu lên Server
        //trả dữ liệu qua biến callback
        Call<List<Truyen>> callback = dataservice.GetDanhSachTruyenListView(idtruyen);
        //Lắng nghe cho sự kiện trả về
        callback.enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(Call<List<Truyen>> call, Response<List<Truyen>> response) {
                //Nếu thành công
                mangtruyenActi2 = (ArrayList<Truyen>) response.body();

                //Gán dữ liệu vào
                danhsachtruyenAdapter = new DanhsachtruyenAdapter(DanhsachtruyenActivity2.this, mangtruyenActi2);
                recyclerViewacti2.setLayoutManager(new LinearLayoutManager(DanhsachtruyenActivity2.this));
                recyclerViewacti2.setAdapter(danhsachtruyenAdapter);
            }

            @Override
            public void onFailure(Call<List<Truyen>> call, Throwable t) {
                //Nếu thất bại
                Log.d("Thất Bại rồi", "Thử lại đi GetDataItems DanhsachtruyenActi2");
            }
        });
    }

    private void init() {
        setSupportActionBar(toolbaracti2); // Khai báo toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Khi nhấn vào chuyển về trang trước
        //Bắt sự kiện khi nhấn vào
        toolbaracti2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Kết thúc màn hình
            }
        });
        //Set màu chữ khi di chuyển collasingtoolbarlayout
        collapsingToolbarLayoutacti2.setExpandedTitleColor(Color.DKGRAY);
        collapsingToolbarLayoutacti2.setCollapsedTitleTextColor(Color.RED);
    }
    private void anhXa() {
        collapsingToolbarLayoutacti2 = findViewById(R.id.collasingtoolbarlayoutacti2);
        coordinatorLayoutacti2 = findViewById(R.id.coordinatorlayoutacti2);

        recyclerViewacti2 = findViewById(R.id.recyclerviewchuongacti2);

        toolbaracti2 = findViewById(R.id.toolbardanhsachacti2);

        imgViewactivity2 = findViewById(R.id.imageviewanhbiaacti2);

        txttentruyen = findViewById(R.id.textViewTenTruyenacti2);
        txttheloai = findViewById(R.id.textViewTheLoaiacti2);
        txttacgia = findViewById(R.id.textViewTacGiaacti2);
        txtmota = findViewById(R.id.textViewmotaacti2);

        floatingActionButton = findViewById(R.id.floatingactionbuttonacti2);

    }
    private void DataTruyenIntent() {
        Intent intent = getIntent(); // Lấy giá trị từ Intent
        if (intent != null) {
            //Nếu Intent null và kiểm tra từ khóa
            if(intent.hasExtra("banner")) {
                top3Truyen = (Top3Truyen) intent.getSerializableExtra("banner");
            }
            if(intent.hasExtra("itemtruyenlist")) {
                truyen = (Truyen) intent.getSerializableExtra("itemtruyenlist");
                Toast.makeText(this, truyen.getIdTruyen(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}