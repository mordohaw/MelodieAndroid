package com.example.williammordohay.melodieandroidv44.Cell;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.williammordohay.melodieandroidv44.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by william.mordohay on 19/04/2017.
 */

public class CellAdapter extends ArrayAdapter<CellObject> {
    public Map<View,CellObject> viewCellObjectMap = new HashMap<View, CellObject>();

    public CellAdapter(Context context, List<CellObject> cells){
        super(context,0,cells);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_machine,parent, false);
        }


        //check if the view is not empty
        CellObjectViewHolder viewHolder = (CellObjectViewHolder) convertView.getTag();
        if(viewHolder==null){
            // Associate View with object viewHolder
            viewHolder = new CellObjectViewHolder();
            viewHolder.num=(TextView) convertView.findViewById(R.id.num);
            viewHolder.text=(TextView) convertView.findViewById(R.id.text);
            //viewHolder.color=(TextView) convertView.findViewById(R.id.color);

            viewHolder.image=(ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (CellObjectViewHolder) convertView.getTag();
        }

        // On récupère l'item correspondant à la position dans la list<CellObject>
        CellObject currentCell = getItem(position);

        //On rempli la vue
        viewHolder.num.setText(String.valueOf(currentCell.getCellNumber()));
        viewHolder.text.setText(currentCell.getCellText());
        viewHolder.image.setImageResource(currentCell.getIdImage());

        //viewHolder.color.setText("");
        //Set color with the field ColourCode in object currentCell
        //viewHolder.color.setBackgroundColor(Color.parseColor(String.valueOf(currentCell.getColourCode())));

        viewCellObjectMap.put(convertView, currentCell);

        return convertView;
    }

    public class CellObjectViewHolder{
        public TextView num;
        public TextView text;
        public TextView color;
        public ImageView image;
    }
}
