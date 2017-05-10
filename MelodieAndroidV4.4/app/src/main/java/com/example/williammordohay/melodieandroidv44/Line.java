package com.example.williammordohay.melodieandroidv44;

/**
 * Created by william.mordohay on 10/05/2017.
 */

public class Line {


    private int lineNumber;
    private String lineLabel;

    public Line(int lineNumber, String lineLabel){
        this.lineNumber=lineNumber;
        this.lineLabel=lineLabel;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getLineLabel() {
        return lineLabel;
    }

    public void setLineLabel(String lineLabel) {
        this.lineLabel = lineLabel;
    }
}

