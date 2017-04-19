package com.example.williammordohay.melodieandroidv44.Product;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.williammordohay.melodieandroidv44.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by william.mordohay on 19/04/2017.
 */

public class ProductAdapter extends ArrayAdapter<ProductObject> {
    public Map<View,ProductObject>viewProductObjectMap = new HashMap<View, ProductObject>();

    public ProductAdapter(Context context, List<ProductObject> products){
        super(context,0,products);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_product,parent, false);
        }

        //check if the view is not empty
        ProductObjectViewHolder viewHolder = (ProductObjectViewHolder ) convertView.getTag();
        if(viewHolder==null){
            // Associate View with object viewHolder
            viewHolder = new ProductObjectViewHolder();
            viewHolder.ref=(TextView) convertView.findViewById(R.id.ref);
            viewHolder.goodNum=(TextView) convertView.findViewById(R.id.good);
            viewHolder.badNum=(TextView) convertView.findViewById(R.id.bad);
            convertView.setTag(viewHolder);
        }

        // On récupère l'item correspondant à la position dans la list<ProductObject>
        ProductObject currentProduct = getItem(position);

        //On rempli la vue
        viewHolder.ref.setText(currentProduct.getReference());
        viewHolder.goodNum.setText(String.valueOf(currentProduct.getGoodProductNumber()));
        viewHolder.badNum.setText(String.valueOf(currentProduct.getBadProductNumber()));

        viewProductObjectMap.put(convertView, currentProduct);

        return convertView;
    }

    public class ProductObjectViewHolder{
        public TextView ref;
        public TextView goodNum;
        public TextView badNum;
    }
}
