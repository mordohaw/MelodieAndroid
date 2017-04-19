package com.example.williammordohay.melodieandroidv44;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.williammordohay.melodieandroidv44.Cell.CellAdapter;
import com.example.williammordohay.melodieandroidv44.Cell.CellObject;

import java.util.ArrayList;
import java.util.List;

public class MachineActivity extends AppCompatActivity {

    private ArrayList cells = new ArrayList();

    private ListView mListView;
    private List<CellObject> cellObjectList = new ArrayList<CellObject>();
    private CellAdapter adapter;
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
        mListView=(ListView) findViewById(R.id.CellsView);
        generateCells();
        adapter=new CellAdapter(this,cells);
        mListView.setAdapter(adapter);

    }

}
