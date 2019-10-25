package com.example.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.app.model.Product;
import com.example.app.remote.APIUtils;
import com.example.app.remote.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnAddUser;
    Button btnGetUsersList;
    ListView listView;

    ProductService productService;
    List<Product> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("CRUD");

        btnAddUser = (Button) findViewById(R.id.btnAddUser);
        btnGetUsersList = (Button) findViewById(R.id.btnGetUsersList);
        listView = (ListView) findViewById(R.id.listView);
        productService = APIUtils.getProductService();

        btnGetUsersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProductsList();
            }
        });

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                intent.putExtra("product_name", "");
                intent.putExtra("product_description", "");
                intent.putExtra("product_price", "");
                intent.putExtra("product_stock", "");
                startActivity(intent);
            }
        });
    }

    public void getProductsList() {
        Call<List<Product>> call = productService.all();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    listView.setAdapter(new ProductAdapter(MainActivity.this, R.layout.list_product, list));
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
