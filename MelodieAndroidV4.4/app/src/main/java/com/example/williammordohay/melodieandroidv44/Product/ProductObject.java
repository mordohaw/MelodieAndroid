package com.example.williammordohay.melodieandroidv44.Product;

/**
 * Created by william.mordohay on 19/04/2017.
 */

public class ProductObject {

    private int lineNumber;
    private String reference;
    private int goodPartsQty;
    private int badPartsQty;

    public ProductObject(int lineNumber,String ref,int good,int bad){
        this.lineNumber=lineNumber;
        this.reference=ref;
        this.goodPartsQty =good;
        this.badPartsQty =bad;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setGoodPartsQty(int goodPartsQty) {
        this.goodPartsQty = goodPartsQty;
    }

    public void setBadPartsQty(int badPartsQty) {
        this.badPartsQty = badPartsQty;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }


    public String getReference() {
        return reference;
    }

    public int getGoodPartsQty() {
        return goodPartsQty;
    }

    public int getBadPartsQty() {
        return badPartsQty;
    }


}
