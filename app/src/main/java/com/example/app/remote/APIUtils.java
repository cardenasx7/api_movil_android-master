package com.example.app.remote;

public class APIUtils {
    private APIUtils() { }

    public static String API_URL = "http://192.168.1.137:8000";

    public static ProductService getProductService() {
        return RetrofitClient.getRetrofit(API_URL).create(ProductService.class);
    }
}
