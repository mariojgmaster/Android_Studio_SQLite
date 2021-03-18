/*
 * Class: ProductDAO
 * Version: 1.2.1
 * Author: Mário Oliveira
 */

package com.exemple.products;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ProductDAO {

    private Connection connection;
    private SQLiteDatabase banco;

    public ProductDAO(Context context) {
        connection = new Connection(context);
        banco = connection.getWritableDatabase();
    }

    public long insertProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("active", product.getActive());
        values.put("description", product.getDescription());
        values.put("price", product.getPrice());
        return banco.insert("product", null, values);
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        Cursor cursor = banco.query("product", new String[]{"id", "name", "active", "description", "price"},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            Product product = new Product(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2) > 0,
                    cursor.getString(3),
                    cursor.getDouble(4)
            );
            products.add(product);
        }
        return products;
    }

    public void deleteProduct(Product prod) {
        banco.delete("product", "id = ?", new String[]{prod.getId().toString()});
    }

    public void updateProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("active", product.getActive());
        values.put("description", product.getDescription());
        values.put("price", product.getPrice());
        banco.update("product", values, "id = ?", new String[]{product.getId().toString()});
    }

}
