package com.example.williammordohay.melodieandroidv44;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.williammordohay.melodieandroidv44.Cell.CellAdapter;
import com.example.williammordohay.melodieandroidv44.Cell.CellObject;

import java.util.ArrayList;
import java.util.List;

public class MachineActivity extends AppCompatActivity {

    int i=0;

    private ListView mListView;
    private List<CellObject> cellObjectList = new ArrayList<>();
    private CellAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine);

        populateMachineView();
        //cellObjectList.remove(8);

        //mListView.invalidateViews();
        /*while(true){
            //Toast.makeText(getApplicationContext(), i, Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
            i++;
        }*/

    }

    /*private void updateData() {
        List<CellObject> newCellObjectList = cellObjectList;
        adapter=new CellAdapter(this,newCellObjectList);
        mListView.setAdapter(adapter);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }*/

    public void generateCells(){

        this.cellObjectList.add(new CellObject(1410, "Production", "#00FF00"));
        this.cellObjectList.add(new CellObject(1413, "Maintenance", "#FF0000"));
        this.cellObjectList.add(new CellObject(1490, "Communicating problems", "#2C75FF"));
        this.cellObjectList.add(new CellObject(2000, "Production", "#00FF00"));
        this.cellObjectList.add(new CellObject(2030, "Stop", "#FF0000"));
        this.cellObjectList.add(new CellObject(2041, "Production", "#00FF00"));
        this.cellObjectList.add(new CellObject(2042, "Maintenance", "#FF0000"));
        this.cellObjectList.add(new CellObject(2131, "Stop", "#FF0000"));
        this.cellObjectList.add(new CellObject(2201, "Production", "#00FF00"));

    }

    public void quitCurrentActivity(View v){
        MachineActivity.this.finish();
    }

    public void populateMachineView(){
        generateCells();
        mListView=(ListView) findViewById(R.id.CellsView);
        adapter=new CellAdapter(this,cellObjectList);
        mListView.setAdapter(adapter);

    }

}