package fsa.williammordohay.melodienet_android_client.infoentrantes;

/**
 * Created by william.mordohay on 19/05/2017.
 */

public class Ligne {

    private int lineNumber;
    private String lineLabel;

    public Ligne(int lineNumber, String lineLabel){
        this.lineNumber=lineNumber;
        this.lineLabel=lineLabel;
    }

    public int getLigneNumber() {
        return lineNumber;
    }

    public void setLigneNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getLigneLabel() {
        return lineLabel;
    }

    public void setLigneLabel(String lineLabel) {
        this.lineLabel = lineLabel;
    }
}
