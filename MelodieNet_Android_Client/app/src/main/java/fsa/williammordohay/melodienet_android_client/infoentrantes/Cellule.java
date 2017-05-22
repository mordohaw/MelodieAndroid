package fsa.williammordohay.melodienet_android_client.infoentrantes;

/**
 * Created by william.mordohay on 19/05/2017.
 */

public class Cellule {
    private int cellNumber;
    private String modeLabel;

    public Cellule(int lineNumber, String lineLabel){
        this.cellNumber =lineNumber;
        this.modeLabel =lineLabel;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getModeLabel() {
        return modeLabel;
    }

    public void setModeLabel(String modeLabel) {
        this.modeLabel = modeLabel;
    }
}
