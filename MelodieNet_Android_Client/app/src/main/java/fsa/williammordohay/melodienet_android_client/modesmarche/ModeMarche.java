package fsa.williammordohay.melodienet_android_client.modesmarche;

/**
 * Created by william.mordohay on 19/05/2017.
 */

public class ModeMarche {


    private int cellNumber;
    private int modeColor;
    private String modeLabel;
    private int runningMode;

    public ModeMarche(int cellNumber, int modeColor, String modeLabel, int runningMode) {
        this.cellNumber = cellNumber;
        this.modeColor = modeColor;
        this.modeLabel = modeLabel;
        this.runningMode = runningMode;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    public int getModeColor() {
        return modeColor;
    }

    public void setModeColor(int modeColor) {
        this.modeColor = modeColor;
    }

    public String getModeLabel() {
        return modeLabel;
    }

    public void setModeLabel(String modeLabel) {
        this.modeLabel = modeLabel;
    }

    public int getRunningMode() {
        return runningMode;
    }

    public void setRunningMode(int runningMode) {
        this.runningMode = runningMode;
    }

}
