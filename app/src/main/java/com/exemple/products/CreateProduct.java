/*
 * Class: Connection
 * Version: 1.2.1
 * Author: Mário Oliveira
 */

package com.exemple.products;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CreateProduct extends AppCompatActivity {

    private Utils utils = new Utils();
    private Context contextAct = this;
    private ProductDAO dao;
    private TextView screenTitle;
    private Button createUpdateButton;
    private EditText nameProd;
    private EditText descProd;
    private EditText priceProd;
    private Product productExt = null;
    private CheckBox isProductActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        // Hide StatusBar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        dao = new ProductDAO(this);
        nameProd = findViewById(R.id.editProductName);
        descProd = findViewById(R.id.editProductDesc);
        priceProd = findViewById(R.id.editProductPrice);
        screenTitle = findViewById(R.id.list_products_screen_title);
        createUpdateButton = findViewById(R.id.create_add_btn);
        isProductActive = findViewById(R.id.checkBoxIsProdActive);

        Intent intent = getIntent();
        if(intent.hasExtra("product")) {
            screenTitle.setText("Editar Produto");
            createUpdateButton.setText("Salvar");
            productExt = (Product) intent.getSerializableExtra("product");
            nameProd.setText(productExt.getName());
            descProd.setText(productExt.getDescription());
            priceProd.setText(productExt.getPrice().toString());
            isProductActive.setChecked(productExt.getActive());
        }

        // Navigate to ProductsList Activity
        findViewById(R.id.btn_home).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { utils.openScreen(contextAct, ProductsList.class, false); }
        });
    }

    public void addProduct(View v) {
        if(productExt == null) {
            try {
                Product product = new Product(nameProd.getText().toString(), Double.parseDouble(priceProd.getText().toString()));
                product.setActive(isProductActive.isChecked());
                if(!descProd.getText().toString().isEmpty()) { product.setDescription(descProd.getText().toString()); }
                long id = dao.insertProduct(product); // Id from ProductDAO -> insertProduct
                utils.ShowToast(contextAct, "Produto Adicionado com Sucesso!");
                utils.openScreen(contextAct, ProductsList.class, false);
            } catch (Exception e) { utils.ShowToast(contextAct, "Holve um Erro ao Adicionar Produto."); }
        } else {
            try {
                if(!nameProd.getText().toString().isEmpty()) { productExt.setName(nameProd.getText().toString()); }
                productExt.setActive(isProductActive.isChecked());
                if(!descProd.getText().toString().isEmpty()) { productExt.setDescription(descProd.getText().toString()); }
                if(!priceProd.getText().toString().isEmpty()) { productExt.setPrice(Double.parseDouble(priceProd.getText().toString())); }
                dao.updateProduct(productExt);
                utils.ShowToast(contextAct, "Produto Atualizado com Sucesso!");
                utils.openScreen(contextAct, ProductsList.class, false);
            } catch (Exception e) { utils.ShowToast(contextAct, "Holve um erro ao atualizar dados do produto."); }
        }
    }
}