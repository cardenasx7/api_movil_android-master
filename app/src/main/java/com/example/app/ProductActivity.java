package com.example.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.model.Product;
import com.example.app.remote.APIUtils;
import com.example.app.remote.ProductService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    ProductService productService;
    EditText edtUId;
    EditText edtName;
    EditText edtDesc;
    EditText edtPrice;
    EditText edtStock;
    Button btnSave;
    Button btnDel;
    TextView txtUId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        setTitle("Products");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUId = findViewById(R.id.txtUId);
        edtUId = findViewById(R.id.edtUId);
        edtName = findViewById(R.id.edtName);
        edtDesc = findViewById(R.id.edtDesc);
        edtPrice = findViewById(R.id.edtPrice);
        edtStock = findViewById(R.id.edtStock);

        btnSave = findViewById(R.id.btnSave);
        btnDel = findViewById(R.id.btnDel);

        productService = APIUtils.getProductService();

        Bundle extras = getIntent().getExtras();
        final String productId = extras.getString("product_id");
        final String name = extras.getString("product_name");
        final String description = extras.getString("product_description");
        final String price = extras.getString("product_price");
        final String stock = extras.getString("product_stock");

        edtUId.setText(productId);
        edtName.setText(name);
        edtDesc.setText(description);
        edtPrice.setText(price);
        edtStock.setText(stock);

        if (productId != null && productId.trim().length() > 0) {
            edtUId.setFocusable(false);
        } else {
            txtUId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product p = new Product();
                p.setName(edtName.getText().toString());
                p.setDescription(edtDesc.getText().toString());
                p.setPrice(Double.parseDouble(edtPrice.getText().toString()));
                p.setStock(Integer.parseInt(edtStock.getText().toString()));

                if (productId != null && productId.trim().length() > 0) {
                    updateProduct(Integer.parseInt(productId), p);
                } else {
                    addProduct(p);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProduct(Integer.parseInt(productId));

                Intent intent = new Intent(ProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addProduct(Product p) {
        Call<Product> call = productService.add(p);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ProductActivity.this, "Product created successfully!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateProduct(int id, Product p) {
        Log.e("id_product", String.valueOf(id));
        Log.e("product", p.toString());

        Call<Product> call = productService.update(id, p);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ProductActivity.this, "Product updated successfully!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void deleteProduct(int id) {
        Call<Product> call = productService.delete(id);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ProductActivity.this, "Product deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
