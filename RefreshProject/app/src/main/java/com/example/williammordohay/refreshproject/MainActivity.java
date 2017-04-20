package com.example.williammordohay.refreshproject;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    ListView swipeList;

    List<String> myList;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        swipeList = (ListView)findViewById(R.id.swipelist);

        /* rien à faire*/
        myList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, myList);
        swipeList.setAdapter(adapter);

        /* rien a faire*/
        swipeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void refresh(){

        final int pos = myList.size();
        myList.add(pos, "Refreshing...");

        //ça nous interesse
        swipeList.invalidateViews();
        swipeRefreshLayout.setRefreshing(true);

        //refresh long-time task in background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //dummy delay for 2 second
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //update ui on UI thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String currentDateTime =
                                DateFormat.getDateTimeInstance().format(new Date());
                        myList.set(pos, pos + " - " + currentDateTime);

                        //important
                        swipeList.invalidateViews();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        }).start();
    }
}
