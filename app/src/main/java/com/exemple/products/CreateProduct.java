package com.exemple.products;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateProduct extends AppCompatActivity {

    Utils utils = new Utils();
    Context contextAct = this;
    ProductDAO dao;
    EditText nameProd;
    EditText descProd;
    EditText priceProd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        // Hide StatusBar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        nameProd = findViewById(R.id.editProductName);
        descProd = findViewById(R.id.editProductDesc);
        priceProd = findViewById(R.id.editProductPrice);

        // Navigate to ProductsList Activity
        findViewById(R.id.btn_home).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { utils.openScreen(contextAct, ProductsList.class, false); }
        });
    }

    public void addProduct(View v) {
        Product product = new Product(
                nameProd.getText().toString(),
                true,
                descProd.getText().toString(),
                Double.parseDouble(priceProd.getText().toString())
        );
        long id = dao.insertProduct(product);

        utils.ShowToast(this, "Produto Adicionado com Sucesso!\nId: "+id);
//        utils.openScreen(contextAct, ProductsList.class, false);
    }
}