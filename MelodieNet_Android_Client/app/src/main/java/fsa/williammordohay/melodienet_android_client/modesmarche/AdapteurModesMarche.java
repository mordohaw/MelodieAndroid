package fsa.williammordohay.melodienet_android_client.modesmarche;

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
 * Created by william.mordohay on 19/05/2017.
 */

public class AdapteurModesMarche extends ArrayAdapter<ModeMarche> {
    public Map<View,ModeMarche> mapModes = new HashMap<View, ModeMarche>();


    public AdapteurModesMarche(Context context, List<ModeMarche> modeMarches){
        super(context,0,modeMarches);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.celulle,parent, false);
        }


        //check if the view is not empty
        VueModes viewHolder = (VueModes) convertView.getTag();
        if(viewHolder==null){
            // Associate View with object viewHolder
            viewHolder = new VueModes();
            viewHolder.num=(TextView) convertView.findViewById(R.id.numero_cellule);
            viewHolder.details=(TextView) convertView.findViewById(R.id.details_cellule);
            viewHolder.couleur=(TextView) convertView.findViewById(R.id.couleur_cellule);

            //viewHolder.image=(ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        }
        else{
            //increase performance
            viewHolder = (VueModes) convertView.getTag();
        }

        // On récupère l'item correspondant à la position dans la list<CellObject>
        ModeMarche modeMarcheCourant = getItem(position);

        //On rempli la vue
        viewHolder.num.setText(String.valueOf(modeMarcheCourant.getCellNumber()));
        viewHolder.details.setText(modeMarcheCourant.getModeLabel());
        viewHolder.couleur.setText(String.valueOf(modeMarcheCourant.getModeColor()));
        //viewHolder.couleur.setImageResource(currentCell.getIdImage());

        //viewHolder.color.setText("");
        //Set color with the field ColourCode in object currentCell
        //viewHolder.color.setBackgroundColor(Color.parseColor(String.valueOf(currentCell.getColourCode())));

        mapModes.put(convertView, modeMarcheCourant);

        return convertView;
    }

    public class VueModes{
        public TextView num;
        public TextView details;
        public TextView couleur;
    }
}
