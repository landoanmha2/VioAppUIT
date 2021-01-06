package com.example.demotruyen.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BinhLuanTruyen implements Serializable {

    @SerializedName("IdUser")
    @Expose
    private String idUser;
    @SerializedName("TkUser")
    @Expose
    private String tkUser;
    @SerializedName("MkUser")
    @Expose
    private String mkUser;
    @SerializedName("IdBinhLuan")
    @Expose
    private String idBinhLuan;
    @SerializedName("IdTruyen")
    @Expose
    private String idTruyen;
    @SerializedName("IdChuong")
    @Expose
    private String idChuong;
    @SerializedName("TenTruyen")
    @Expose
    private String tenTruyen;
    @SerializedName("TenChuong")
    @Expose
    private String tenChuong;
    @SerializedName("NdBinhLuan")
    @Expose
    private String ndBinhLuan;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getTkUser() {
        return tkUser;
    }

    public void setTkUser(String tkUser) {
        this.tkUser = tkUser;
    }

    public String getMkUser() {
        return mkUser;
    }

    public void setMkUser(String mkUser) {
        this.mkUser = mkUser;
    }

    public String getIdBinhLuan() {
        return idBinhLuan;
    }

    public void setIdBinhLuan(String idBinhLuan) {
        this.idBinhLuan = idBinhLuan;
    }

    public String getIdTruyen() {
        return idTruyen;
    }

    public void setIdTruyen(String idTruyen) {
        this.idTruyen = idTruyen;
    }

    public String getIdChuong() {
        return idChuong;
    }

    public void setIdChuong(String idChuong) {
        this.idChuong = idChuong;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getTenChuong() {
        return tenChuong;
    }

    public void setTenChuong(String tenChuong) {
        this.tenChuong = tenChuong;
    }

    public String getNdBinhLuan() {
        return ndBinhLuan;
    }

    public void setNdBinhLuan(String ndBinhLuan) {
        this.ndBinhLuan = ndBinhLuan;
    }

}