package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.model.Product;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> products;

    public ProductAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        this.products = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_product, parent, false);

        TextView txtProductId = rowView.findViewById(R.id.txtProductId);
        TextView txtName = rowView.findViewById(R.id.txtName);
        TextView txtDesc = rowView.findViewById(R.id.txtDescription);
        TextView txtPrice = rowView.findViewById(R.id.txtPrice);
        TextView txtStock = rowView.findViewById(R.id.txtStock);

        txtProductId.setText(String.format("PRODUCT ID: %d", products.get(pos).getId()));
        txtName.setText(String.format("PRODUCT NAME: %s", products.get(pos).getName()));
        txtDesc.setText(String.format("PRODUCT DESCRIPTION: %s", products.get(pos).getDescription()));
        txtPrice.setText(String.format("PRODUCT PRICE: %s", products.get(pos).getPrice()));
        txtStock.setText(String.format("PRODUCT STOCK: %s", products.get(pos).getStock()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra("product_id", String.valueOf(products.get(pos).getId()));
                intent.putExtra("product_name", products.get(pos).getName());
                intent.putExtra("product_description", products.get(pos).getDescription());
                intent.putExtra("product_price", String.valueOf(products.get(pos).getPrice()));
                intent.putExtra("product_stock", String.valueOf(products.get(pos).getStock()));
                context.startActivity(intent);
                //hola
            }
        });

        return rowView;
    }
}
