package com.example.williammordohay.melodieandroidv44.Cell;

/**
 * Created by william.mordohay on 18/04/2017.
 */

public class CellObject {

    private int cellNumber;
    private String cellText;
    private String colourCode;

    public CellObject(int number,String text,String code){
        this.cellNumber=number;
        this.cellText=text;
        this.colourCode=code;
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
