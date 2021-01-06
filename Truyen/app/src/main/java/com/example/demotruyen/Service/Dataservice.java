package com.example.demotruyen.Service;

import com.example.demotruyen.Model.BinhLuanTruyen;
import com.example.demotruyen.Model.Top3Truyen;
import com.example.demotruyen.Model.Truyen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {

    @GET("top3yeuthichbanner.php")
    Call<List<Top3Truyen>> GetTop3();
    //Phương thức GET dùng để tương tác với Server
    @GET("truyenbanner.php")
    Call<List<Truyen>> GetDataTruyen();

    @GET("getbinhluanv3.php")
    Call<List<BinhLuanTruyen>> GetBinhLuan();

    //Phương thức POST dùng để gửi dữ liệu lên Server và trả về theo câu lệnh SQL của file php trên Server
    @FormUrlEncoded
    @POST("danhsachtruyen.php")
    Call<List<Truyen>> GetDanhSachTruyenActi2(@Field("idrank") String idrank);

    @FormUrlEncoded
    @POST("danhsachtruyen.php")
    Call<List<Truyen>> GetDanhSachTruyenListView(@Field("idtruyen") String idtruyen);

    @FormUrlEncoded
    @POST("timkiem.php")
    Call<List<Truyen>> GetTimKiemTruyen(@Field("tukhoa") String tukhoa);

    @FormUrlEncoded
    @POST("postbinhluanv2.php")
    Call<String> PostBinhLuan(@Field("ndbinhluan") String ndbinhluan);

    @FormUrlEncoded
    @POST("loginapp.php")
    Call<String> LoginApp(@Field("tentaikhoan") String tentaikhoan, @Field("matkhautaikhoan") String matkhautaikhoan);

    @FormUrlEncoded
    @POST("dangkyapp.php")
    Call<String> DangKyAcc(@Field("dangkytaikhoan") String dangkytaikhoan, @Field("dangkymatkhau") String dangkymatkhau);
}
