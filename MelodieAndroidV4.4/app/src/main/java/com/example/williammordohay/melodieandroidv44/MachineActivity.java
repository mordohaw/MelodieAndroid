package com.example.williammordohay.melodieandroidv44;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.williammordohay.melodieandroidv44.Cell.CellAdapter;
import com.example.williammordohay.melodieandroidv44.Cell.CellObject;
import com.example.williammordohay.melodieandroidv44.ServiceManager.WebService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MachineActivity extends AppCompatActivity {

    int i=8;

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView mListView;
    private List<CellObject> cellObjectList = new ArrayList<>();
    private CellAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine);
        mListView=(ListView) findViewById(R.id.CellsView);

        populateMachineView();

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.SwipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

    }


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
        //populate the ListView
        generateCells();
        adapter=new CellAdapter(this,cellObjectList);
        mListView.setAdapter(adapter);

    }

    private void refresh(){


        Toast.makeText(MachineActivity.this, R.string.refresh, Toast.LENGTH_SHORT).show();

        //set the refresh
        mListView.invalidateViews();
        swipeRefreshLayout.setRefreshing(true);

        //refresh long-time task in background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //dummy delay for 1 second
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //update ui on UI thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //set the action on up dating
                        //Create SharedPreferences to load values
                        SharedPreferences SharedParam= PreferenceManager.getDefaultSharedPreferences(MachineActivity.this);
                        //load the value enter by user in editURL. Default value is "http://val-prod-002/MelodieNet/Modules/EcransDeBase/Bienvenue.aspx" here
                        String stringUrl = SharedParam.getString("editURL","http://val-prod-002/MelodieNet/Modules/EcransDeBase/Bienvenue.aspx");
                        //get the cells List
                        cellObjectList = getCellsList(stringUrl);
                        if(cellObjectList != null){
                            //exemple
                            if(i>=0){
                                cellObjectList.remove(i);
                                i--;
                            }
                        }
                        else{
                            Toast.makeText(MachineActivity.this, "Failure on refreshing", Toast.LENGTH_SHORT).show();
                        }

                        //Update the list
                        mListView.invalidateViews();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        }).start();


    }
    public List<CellObject> getCellsList(String URL){
        WebService myWebService = new WebService();
        myWebService.setURL(URL);
        if(myWebService.getURL()!=null){
            return(myWebService.getCells());
        }
        return null;
    }


}
