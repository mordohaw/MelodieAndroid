package com.example.williammordohay.simplewsproject.Parameters;

/**
 * Created by william.mordohay on 04/05/2017.
 */

public class CellRunningModParam {

    private int numLigne, numStation;
    public CellRunningModParam(int numLigne,int numStation){
        this.numLigne=numLigne;
        this.numStation=numStation;
    }

    public int getNumLigne() {
        return numLigne;
    }

    public int getNumStation() {
        return numStation;
    }

}

