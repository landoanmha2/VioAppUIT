package com.example.demotruyen.Service;

public class APIService {
//Class này dùng để kết hợp class APIRetrofitClient với Class Dataservice
    private static String base_url = "https://landatatruyen.000webhostapp.com/Server/";

    //Trả về cho Dataservice
    public static Dataservice getService() {
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
