package com.example.williammordohay.melodieandroidv44.Cell;

import android.graphics.drawable.Drawable;

import com.example.williammordohay.melodieandroidv44.R;

/**
 * Created by william.mordohay on 18/04/2017.
 */

public class CellObject {


    private int cellNumber;
    private String cellText;
    private String colourCode;

    public int getIdImage() {
        return idImage;
    }

    private int idImage;

    public CellObject(int number,String text,String code){
        this.cellNumber=number;
        this.cellText=text;
        this.colourCode=code;
        switch (this.cellText){
            case "Production" :
                this.idImage=R.drawable.checked_green;
                break;

            case "Maintenance" :
                this.idImage=R.drawable.maintenance;
                break;

            case "Communicating problems" :
                this.idImage=R.drawable.compb2;
                break;

            case "Stop" :
                this.idImage=R.drawable.stop;
                break;
        }
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    public void setCellText(String cellText) {
        this.cellText = cellText;
    }

    public void setColourCode(String colourCode) {
        this.colourCode = colourCode;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public String getCellText() {
        return cellText;
    }
    public String getColourCode() {
        return colourCode;
    }
}
