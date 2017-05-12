package com.example.williammordohay.melodieandroidv44.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.williammordohay.melodieandroidv44.Product.ProductAdapter;
import com.example.williammordohay.melodieandroidv44.Product.ProductObject;
import com.example.williammordohay.melodieandroidv44.R;
import com.example.williammordohay.melodieandroidv44.ServiceManager.RequestBuilder;
import com.example.williammordohay.melodieandroidv44.ServiceManager.WebServiceData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by william.mordohay on 11/05/2017.
 */

public class ProductionActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView mListView;
    private List<ProductObject> productObjectList = new ArrayList<>();
    private ProductAdapter productAdapter;
    Gson gson;
    private String currentInputString,productionType,lineSelected,baseURL,currentProductURL;
    private RequestBuilder currentRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson=new Gson();
        SharedPreferences SharedParam= PreferenceManager.getDefaultSharedPreferences(ProductionActivity.this);
        //load the value enter by user in editURL. Default value is "http://val-prod-002/MelodieNet/Modules/EcransDeBase/Bienvenue.aspx" here
        baseURL = SharedParam.getString("editURL","http://val-prod-jfc/MelodieNet_REST_Service/");

        currentRequest = new RequestBuilder(baseURL);

        //On récupère la valeur
        Bundle extras = getIntent().getExtras();
        productionType = extras.getString("ProductionType");
        lineSelected = extras.getString("LineSelected");
        //productionType = getIntent().getStringExtra("ProductionType");

        setContentView(R.layout.activity_production);
        mListView=(ListView) findViewById(R.id.ProductView);
        populateProductView();

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.SwipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }
    public void populateProductView(){
        //populate the ProductListView
        generateProducts();
        productAdapter=new ProductAdapter(this,productObjectList);
        mListView.setAdapter(productAdapter);

    }

    public void generateProducts(){
        switch (productionType){
            case "GetHourProduction":
                currentProductURL = currentRequest.getHourProduction(lineSelected);
                break;
            case "GetDayProduction":
                currentProductURL = currentRequest.getDayProduction(lineSelected);
                break;
            case "GetWeekProduction":
                currentProductURL = currentRequest.getWeekProduction(lineSelected);
                break;
        }
        try {
            currentInputString = new WebServiceData().execute(currentProductURL).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        this.productObjectList = gson.fromJson(currentInputString,new TypeToken<List<ProductObject>>(){}.getType());


    }

    public void quitCurrentActivity(View v){
        ProductionActivity.this.finish();
    }

    private void refresh(){


        Toast.makeText(ProductionActivity.this, R.string.refresh, Toast.LENGTH_SHORT).show();

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

                        //startService(serviceIntent);
                            populateProductView();

                        //Update the list
                        mListView.invalidateViews();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        }).start();


    }
}
