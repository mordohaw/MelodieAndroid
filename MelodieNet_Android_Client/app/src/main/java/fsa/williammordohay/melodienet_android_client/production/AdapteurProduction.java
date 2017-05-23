package fsa.williammordohay.melodienet_android_client.production;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fsa.williammordohay.melodienet_android_client.R;

/**
 * Created by william.mordohay on 23/05/2017.
 */

public class AdapteurProduction extends ArrayAdapter<Production> {
    public Map<View,Production> mapProduction = new HashMap<>();
    
    public AdapteurProduction(Context contexte, List<Production> produits){
        super(contexte,0,produits);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.produit,parent, false);
        }

        //check if the view is not empty
        VueProduits viewHolder = (VueProduits ) convertView.getTag();
        if(viewHolder==null){
            // Associate View with object viewHolder
            viewHolder = new VueProduits();
            viewHolder.ref=(TextView) convertView.findViewById(R.id.ref);
            viewHolder.nbBons=(TextView) convertView.findViewById(R.id.good);
            viewHolder.nbMauvais=(TextView) convertView.findViewById(R.id.bad);
            convertView.setTag(viewHolder);
        }
        else{
            //increase performance
            viewHolder = (VueProduits) convertView.getTag();
        }

        // On récupère l'item correspondant à la position dans la list<ProductObject>
        Production produitCourant = getItem(position);

        //On rempli la vue
        viewHolder.ref.setText(produitCourant.getReference());
        viewHolder.nbBons.setText(String.valueOf(produitCourant.getGoodPartsQty()));
        viewHolder.nbMauvais.setText(String.valueOf(produitCourant.getBadPartsQty()));

        mapProduction.put(convertView, produitCourant);

        return convertView;
    }

    public class VueProduits{
        public TextView ref;
        public TextView nbBons;
        public TextView nbMauvais;
    }
}
