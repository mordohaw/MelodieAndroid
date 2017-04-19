package com.example.williammordohay.melodieandroidv44.Product;

/**
 * Created by william.mordohay on 19/04/2017.
 */

public class ProductObject {

    private String reference;
    private int goodProductNumber;
    private int badProductNumber;

    public ProductObject(String ref,int good,int bad){
        this.reference=ref;
        this.goodProductNumber=good;
        this.badProductNumber=bad;
    }

    public String getReference() {
        return reference;
    }

    public int getGoodProductNumber() {
        return goodProductNumber;
    }

    public int getBadProductNumber() {
        return badProductNumber;
    }


}
