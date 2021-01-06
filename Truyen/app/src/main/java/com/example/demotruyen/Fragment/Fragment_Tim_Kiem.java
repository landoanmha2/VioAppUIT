package com.example.demotruyen.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demotruyen.Adapter.SearchTruyenAdapter;
import com.example.demotruyen.Model.Truyen;
import com.example.demotruyen.R;
import com.example.demotruyen.Service.APIService;
import com.example.demotruyen.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_Kiem extends Fragment {
    //Muốn custom lại ViewPager phải kế thừa Fragment
    //Khai báo biến
    View view;
    Toolbar toolbartimkiem;
    RecyclerView recyclerViewtimkiem;
    TextView textViewthongbaotimkiem;

    SearchTruyenAdapter searchTruyenAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Phương thức onCreateView dùng để gán View
        view = inflater.inflate(R.layout.fragment_tim_kiem, container, false);
        //Ánh xạ
        toolbartimkiem = view.findViewById(R.id.toorbartimkiem);
        recyclerViewtimkiem = view.findViewById(R.id.recyclerviewtimkiem);
        textViewthongbaotimkiem = view.findViewById(R.id.textviewthongbaotimkiem);
        //Ánh xạ toolbar
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbartimkiem);
        toolbartimkiem.setTitle(""); //Set Title cho toolbar

        setHasOptionsMenu(true); // Bật chức năng Menu

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        //Phương thức dùng để gán layout menu
        inflater.inflate(R.menu.search_view, menu);
        MenuItem menuItem = menu.findItem(R.id.menusearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE); //Set độ dài cho tìm kiếm

        //Bắt sự kiện tìm kiếm
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Bắt sự kiện khi Click icon tìm kiếm
                Log.d("Tìm Kiếm", query);
                TimKiemTruyen(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Bắt sự kiện khi thay đổi Query
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void TimKiemTruyen(String tukhoa) {
        //Khởi tạo lại APIService
        Dataservice dataservice = APIService.getService();
        //Gửi dữ liệu lên theo thương thức GetTimKiemTruyen với query là 'tukhoa', trả dữ liệu qua biến callback
        Call<List<Truyen>> callback = dataservice.GetTimKiemTruyen(tukhoa);
        //Lắng nghe cho sự kiện trả về
        callback.enqueue(new Callback<List<Truyen>>() {
            @Override
            public void onResponse(Call<List<Truyen>> call, Response<List<Truyen>> response) {
                //Nếu thành công
                ArrayList<Truyen> mangtruyentimkiem = (ArrayList<Truyen>) response.body();
                if(mangtruyentimkiem.size() > 0) {
                    //Khi tìm kiếm nếu giá trị trả về > 0 tức là có kết quả tìm kiếm
                    searchTruyenAdapter = new SearchTruyenAdapter(getActivity(), mangtruyentimkiem);
                    //Dịnh dạng lại hiển thị của RecyclerView
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    //Gán dữ liệu
                    recyclerViewtimkiem.setLayoutManager(linearLayoutManager);
                    recyclerViewtimkiem.setAdapter(searchTruyenAdapter);

                    textViewthongbaotimkiem.setVisibility(View.GONE); //Nếu có dữ liệu thì text thông báo ẩn đi
                    recyclerViewtimkiem.setVisibility(View.VISIBLE); //Nếu có dữ liệu thì hiện RecylerView
                }else {
                    //Nếu mảng trả về ko có dữ liệu
                    recyclerViewtimkiem.setVisibility(View.GONE); //Ẩn RecylerView
                    textViewthongbaotimkiem.setVisibility(View.VISIBLE); //Hiện thông báo 
                }
            }

            @Override
            public void onFailure(Call<List<Truyen>> call, Throwable t) {
                //Nếu thất bại
                Log.d("Thất Bại rồi", "Thử lại đi Fragment Tìm Kiếm");
            }
        });
    }
}
