package com.example.williammordohay.melodieandroidv44.Cell;

import com.example.williammordohay.melodieandroidv44.R;

/**
 * Created by william.mordohay on 18/04/2017.
 */

public class CellObject {


    private int cellNumber;
    private String cellLabel;
    private String colourCode;

    public int getIdImage() {
        return idImage;
    }

    private int idImage;

    public CellObject(int number,String text,String code){
        this.cellNumber=number;
        this.cellLabel =text;
        this.colourCode=code;
        switch (this.cellLabel){
            case "Production" :
                this.idImage=R.drawable.check_ssfond;
                break;

            case "Maintenance" :
                this.idImage=R.drawable.maintenance;
                break;

            case "Communicating problems" :
                this.idImage=R.drawable.compb2;
                break;

            case "Stop" :
                this.idImage=R.drawable.stop_ssfond;
                break;
        }
    }
    public CellObject(int number,String text){
        this.cellNumber=number;
        this.cellLabel =text;
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    public void setCellLabel(String cellLabel) {
        this.cellLabel = cellLabel;
    }

    public void setColourCode(String colourCode) {
        this.colourCode = colourCode;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public String getCellLabel() {
        return cellLabel;
    }
    public String getColourCode() {
        return colourCode;
    }
}
