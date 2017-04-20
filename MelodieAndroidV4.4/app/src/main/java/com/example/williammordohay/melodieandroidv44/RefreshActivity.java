package com.example.williammordohay.melodieandroidv44;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by william.mordohay on 20/04/2017.
 */

public abstract class RefreshActivity extends AppCompatActivity {


    /*SwipeRefreshLayout swipeRefreshLayout;

    protected void RefreshActivity(final ListView currentListView,ArrayList<Objects> arrayList) {

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.SwipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh(currentListView);
            }
        });
    }

    public void refresh(ListView currentListView){


        Toast.makeText(this, "Refreshing...", Toast.LENGTH_SHORT).show();

        //set the refresh
        currentListView.invalidateViews();
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
                        cellObjectList.remove(i);
                        i--;
                        //Update the list
                        mListView.invalidateViews();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        }).start();


    }*/

}
