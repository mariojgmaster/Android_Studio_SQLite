package com.exemple.products;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {

    private final Context context;
    private final ArrayList<Product> elementos;

    public ProductAdapter(Context context, ArrayList<Product> elementos) {
        super(context, R.layout.row, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);

        TextView productTitle = (TextView) rowView.findViewById(R.id.title_prod);
        TextView productDescription = (TextView) rowView.findViewById(R.id.desc_prod);
        TextView productPrice = (TextView) rowView.findViewById(R.id.price_prod);

        productTitle.setText(elementos.get(position).getName());
        productDescription.setText(elementos.get(position).getDescription());
        productPrice.setText(elementos.get(position).getPrice().toString());

        return rowView;
    }

}
