package fsa.williammordohay.melodienet_android_client.modesmarche;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fsa.williammordohay.melodienet_android_client.R;

/**
 * Created by william.mordohay on 19/05/2017.
 */

public class AdapteurModesMarche extends ArrayAdapter<ModeMarche>
{
    public Map<View,ModeMarche> mapModes = new HashMap<View, ModeMarche>();
    public List<Integer> listeImages = new ArrayList<>();


    public AdapteurModesMarche(Context context, List<ModeMarche> modeMarches)
    {
        super(context,0,modeMarches);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.celulle,parent, false);
        }


        //check if the view is not empty
        VueModes viewHolder = (VueModes) convertView.getTag();
        if(viewHolder==null)
        {
            // Associate View with object viewHolder
            viewHolder = new VueModes();
            viewHolder.num=(TextView) convertView.findViewById(R.id.numero_cellule);
            viewHolder.details=(TextView) convertView.findViewById(R.id.details_cellule);
            viewHolder.imageCelulle =(ImageView) convertView.findViewById(R.id.vue_image);

            convertView.setTag(viewHolder);
        }
        else
        {
            //increase performance
            viewHolder = (VueModes) convertView.getTag();
        }

        // On récupère l'item correspondant à la position dans la list<CellObject>
        ModeMarche modeMarcheCourant = getItem(position);

        //On rempli la vue
        viewHolder.num.setText(String.valueOf(modeMarcheCourant.getCellNumber()));
        viewHolder.details.setText(modeMarcheCourant.getModeLabel());
        //viewHolder.couleur.setText(String.valueOf(modeMarcheCourant.getModeColor()));
        remplirListeImages();
        int indiceCouleur = modeMarcheCourant.getModeColor();

        //charge l'image correspondant à l'indice de la couleur en la cherchant dans l'ArrayList "listeImages"
        viewHolder.imageCelulle.setImageResource(listeImages.get(indiceCouleur-1));


        mapModes.put(convertView, modeMarcheCourant);

        return convertView;
    }

    public void remplirListeImages()
    {
        listeImages.add(R.drawable.image_000000);
        listeImages.add(R.drawable.image_808080);
        listeImages.add(R.drawable.image_f5f5f5);
        listeImages.add(R.drawable.image_a0522d);
        listeImages.add(R.drawable.image_ff0000);
        listeImages.add(R.drawable.image_ff69b4);
        listeImages.add(R.drawable.image_ee82ee);
        listeImages.add(R.drawable.image_6a5acd);
        listeImages.add(R.drawable.image_0000cd);
        listeImages.add(R.drawable.image_1e90ff);
        listeImages.add(R.drawable.image_00ced1);
        listeImages.add(R.drawable.image_008b8b);
        listeImages.add(R.drawable.image_32cd32);
        listeImages.add(R.drawable.image_7cfc00);
        listeImages.add(R.drawable.image_ffff00);
        listeImages.add(R.drawable.image_ffa500);
    }

    public class VueModes
    {
        public TextView num;
        public TextView details;
        public ImageView imageCelulle;
    }
}
