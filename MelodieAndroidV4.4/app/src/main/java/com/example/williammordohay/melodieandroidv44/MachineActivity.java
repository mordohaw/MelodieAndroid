package com.example.williammordohay.melodieandroidv44;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MachineActivity extends AppCompatActivity {

    private ArrayList cells = new ArrayList();

    private ListView mListView;
    private List<CellObject> cellObjectList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine);
        populateListView();
    }

    public void generateCells(){

        this.cells.add(new CellObject(1410, "Production", "#00FF00"));
        this.cells.add(new CellObject(1413, "Maintenance", "#FF0000"));
        this.cells.add(new CellObject(1490, "Hors communication", "#2C75FF"));
        this.cells.add(new CellObject(2000, "Production", "#00FF00"));
        this.cells.add(new CellObject(2030, "Arret", "#FF0000"));
        this.cells.add(new CellObject(2041, "Production", "#00FF00"));
        this.cells.add(new CellObject(2042, "Maintenance", "#FF0000"));
        this.cells.add(new CellObject(2131, "Arret", "#FF0000"));
        this.cells.add(new CellObject(2201, "Production", "#00FF00"));

    }

    public void quitCurrentActivity(View v){
        MachineActivity.this.finish();
    }

    public void populateListView(){
        generateCells();
        ArrayAdapter<CellObject> adapter = new ArrayAdapter<CellObject>(
                this,
                R.layout.row_machine,
                cells
                );
    }

}
