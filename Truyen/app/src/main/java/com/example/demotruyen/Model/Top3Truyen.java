package com.example.demotruyen.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Top3Truyen implements Serializable {

    @SerializedName("IdRank")
    @Expose
    private String idRank;
    @SerializedName("IdUser")
    @Expose
    private String idUser;
    @SerializedName("IdTruyen")
    @Expose
    private String idTruyen;
    @SerializedName("TenTruyen")
    @Expose
    private String tenTruyen;
    @SerializedName("TenTheLoai")
    @Expose
    private String tenTheLoai;
    @SerializedName("TacGia")
    @Expose
    private String tacGia;
    @SerializedName("MoTaTruyen")
    @Expose
    private String moTaTruyen;
    @SerializedName("LinkAnhBia")
    @Expose
    private String linkAnhBia;
    @SerializedName("LinkBackGround")
    @Expose
    private String linkBackGround;

    public String getIdRank() {
        return idRank;
    }

    public void setIdRank(String idRank) {
        this.idRank = idRank;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdTruyen() {
        return idTruyen;
    }

    public void setIdTruyen(String idTruyen) {
        this.idTruyen = idTruyen;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getMoTaTruyen() {
        return moTaTruyen;
    }

    public void setMoTaTruyen(String moTaTruyen) {
        this.moTaTruyen = moTaTruyen;
    }

    public String getLinkAnhBia() {
        return linkAnhBia;
    }

    public void setLinkAnhBia(String linkAnhBia) {
        this.linkAnhBia = linkAnhBia;
    }

    public String getLinkBackGround() {
        return linkBackGround;
    }

    public void setLinkBackGround(String linkBackGround) {
        this.linkBackGround = linkBackGround;
    }

}