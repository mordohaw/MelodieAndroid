package com.example.williammordohay.melodieandroidv44;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.williammordohay.melodieandroidv44.Product.ProductAdapter;
import com.example.williammordohay.melodieandroidv44.Product.ProductObject;

import java.util.ArrayList;
import java.util.List;

public class ProductionActivity extends AppCompatActivity {

    private int i=5;
    private ListView mListView;
    private List<ProductObject> productObjectList = new ArrayList<>();
    private ProductAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production);
        populateProductView();

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.SwipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    public void quitCurrentActivity(View v){
        ProductionActivity.this.finish();
    }

    public void generateProducts(){

        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));

    }


    public void populateProductView(){
        generateProducts();
        mListView=(ListView) findViewById(R.id.ProductView);
        adapter=new ProductAdapter(this,productObjectList);
        mListView.setAdapter(adapter);

    }

    private void refresh(){


        Toast.makeText(this, R.string.refresh, Toast.LENGTH_SHORT).show();

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
                        if(i>=0){
                            productObjectList.remove(i);
                            i--;
                        }

                        //Update the list
                        mListView.invalidateViews();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        }).start();


    }
}
