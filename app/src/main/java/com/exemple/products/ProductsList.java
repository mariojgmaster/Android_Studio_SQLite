/*
 * Class: ProductsList
 * Version: 1.0.0
 * Author: Mário Oliveira
 */

package com.exemple.products;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ProductsList extends AppCompatActivity {

    Utils utils = new Utils();
    ArrayList<Product> productsList;
    ProductDAO dao;
    Context contextAct = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide StatusBar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        try{
            productsList = dao.getProducts();

            // Feed ListView With Adapter and Adapter With ProductsList
            ListView listProd = (ListView) findViewById(R.id.list_products);
            ArrayAdapter adapter = new ProductAdapter(this, productsList);
            listProd.setAdapter(adapter);
            utils.ShowToast(this, "Fim Sucesso!");
        } catch(Exception e) {
            // Feed ListView With Adapter and Adapter With ProductsList
            ListView listProd = (ListView) findViewById(R.id.list_products);
            ArrayAdapter adapter = new ProductAdapter(this, listaMockProdutos());
            listProd.setAdapter(adapter);
//            utils.ShowToast(this, "Erro: "+e.getMessage());
        }

        // Navigate to CreateProduct Activity
        findViewById(R.id.btn_add_product).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { utils.openScreen(contextAct, CreateProduct.class, true); }
        });

        // utils.ShowToast(this, "Mário Jorge Teste!!!");
    }

    private ArrayList<Product> listaMockProdutos() {

        ArrayList<Product> produtos = new ArrayList<>();
//        Product p = new Product(1, "Bicicleta Caloi 800 Marchas (Mock)", "Esta bicicleta é pprofissional e você bla bla bla também.", 2500.00);
//        produtos.add(p);
//        p = new Product(2, "Notebook Gamer Acer i7 9th (Mock)", "Este notebook é um notebook realmente poderoso que bla bla bla.", 5920.70);
//        produtos.add(p);
//        p = new Product(3, "Ursinho Ted (Mock)", "Este urso de pelucia infantil é muito legal para as crianças.", 99.99);
//        produtos.add(p);
//        p = new Product(4, "Casinha de Boneca da Polly Pocket Com Garagem (Mock)", "Esta Casinha de Boneca infantil da Polly Pocket é muito legal para as crianças.", 99.99);
//        produtos.add(p);
        Product p = new Product(4, "-", 0.0);
        produtos.add(p);

        return produtos;
    }
}