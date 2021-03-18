/*
 * Class: Connection
 * Version: 1.1.0
 * Author: Mário Oliveira
 */

package com.exemple.products;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {

    private Utils utils = new Utils();
    private ProductDAO dao;
    private final Context context;
    private final ArrayList<Product> elementos;

    public ProductAdapter(Context context, ArrayList<Product> elementos) {
        super(context, R.layout.row, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        dao = new ProductDAO(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);

        TextView productTitle = (TextView) rowView.findViewById(R.id.title_prod);
        TextView productDescription = (TextView) rowView.findViewById(R.id.desc_prod);
        TextView productPrice = (TextView) rowView.findViewById(R.id.price_prod);
        Button btnDeleteProd = (Button) rowView.findViewById(R.id.delete_btn);
        Button btnUpdateProd = (Button) rowView.findViewById(R.id.edit_btn);

        productTitle.setText(elementos.get(position).getName());
        productDescription.setText(elementos.get(position).getDescription());
        productPrice.setText(String.format("%.2f", Double.parseDouble(new DecimalFormat("##.##").format(elementos.get(position).getPrice()))));

        btnDeleteProd.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { deleteProduct(context, position).show(); } });
        btnUpdateProd.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { updateProduct(context, position); } });

        return rowView;
    }

    public AlertDialog deleteProduct(Context context, Integer position) {
        Product elemPos = elementos.get(position);
        String nomeProd = elemPos.getName();
        AlertDialog dialog = new AlertDialog.Builder(context)
            .setTitle("Atenção")
            .setMessage("O produto \""+nomeProd+"\" será deletado permanentemente.")
            .setNegativeButton("Cancelar", null)
            .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dao.deleteProduct(elemPos);
                    utils.ShowToast(context, "O Produto \""+nomeProd+"\" foi excluído com Sucesso!");
                    utils.openScreen(context, ProductsList.class, false);
                }
            }).create();

        return dialog;
    }

    public void updateProduct(Context context, Integer position) {
        Intent intent = new Intent(context, CreateProduct.class);
        intent.putExtra("product", elementos.get(position));
        context.startActivity(intent);
    }

}
