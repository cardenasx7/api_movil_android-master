package com.example.app.remote;

import com.example.app.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ProductService {
    @GET("products")
    Call<List<Product>> all();

    @POST("products")
    Call<Product> add(@Body Product product);

    @PUT("products/{id}")
    Call<Product> update(@Path("id") int id, @Body Product product);

    @DELETE("products/{id}")
    Call<Product> delete(@Path("id") int id);
}
