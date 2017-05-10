package com.example.williammordohay.melodieandroidv44.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.example.williammordohay.melodieandroidv44.Cell.CellAdapter;
import com.example.williammordohay.melodieandroidv44.Cell.CellObject;
import com.example.williammordohay.melodieandroidv44.Product.ProductAdapter;
import com.example.williammordohay.melodieandroidv44.Product.ProductObject;
import com.example.williammordohay.melodieandroidv44.R;
import com.example.williammordohay.melodieandroidv44.ServiceManager.Request;
import com.example.williammordohay.melodieandroidv44.ServiceManager.WebService;
import com.example.williammordohay.melodieandroidv44.Settings.Line;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Created by william.mordohay on 27/04/2017.
 */

public class ViewsActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView mListView;
    private List<CellObject> cellObjectList = new ArrayList<>();
    private List<ProductObject> productObjectList = new ArrayList<>();
    private CellAdapter cellAdapter;
    private ProductAdapter productAdapter;
    Gson gson;
    private Request currentRequest;

    private String currentInputString;
    int i=8;
    private boolean choice,bound;
    private WebService mWebService;

    private List<Line> lineObjectList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson=new Gson();

        currentRequest = new Request("http://val-prod-jfc/MelodieNet_REST_Service/");
        choice = getIntent().getBooleanExtra("userChoice", true);
        if (choice == true) {
            //Machine Tracking case
            setContentView(R.layout.activity_machine);
            mListView=(ListView) findViewById(R.id.CellsView);
            populateMachineView();


        } else if (choice == false) {
            //Production Tracking case
            setContentView(R.layout.activity_production);
            mListView=(ListView) findViewById(R.id.ProductView);
            populateProductView();
            i=25;

        }
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.SwipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        /*currentRequest=new Request("http://val-prod-jfc/MelodieNet_REST_Service/");
        getList();*/
    }



    public void populateMachineView(){
        //populate the Machine ListView
        generateCells();
        cellAdapter=new CellAdapter(this,cellObjectList);
        mListView.setAdapter(cellAdapter);

    }
    public void populateProductView(){
        //populate the ProductListView
        generateProducts();
        productAdapter=new ProductAdapter(this,productObjectList);
        mListView.setAdapter(productAdapter);

    }


    private void refresh(){


        Toast.makeText(ViewsActivity.this, R.string.refresh, Toast.LENGTH_SHORT).show();

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
                        SharedPreferences SharedParam= PreferenceManager.getDefaultSharedPreferences(ViewsActivity.this);
                        //load the value enter by user in editURL. Default value is "http://val-prod-002/MelodieNet/Modules/EcransDeBase/Bienvenue.aspx" here
                        String stringUrl = SharedParam.getString("editURL","http://val-prod-jfc/Essai_ASPNET_REST_Service/GetProductList/");
                        //get the List
                        //Intent serviceIntent = new Intent(getBaseContext(), WebService.class);
                        //Send the current activity to the service
                        //serviceIntent.putExtra("booleanActivity", choice);
                        //serviceIntent.putExtra("requestType", requestEnum.GETLINE);

                        //startService(serviceIntent);
                        if(choice){
                            //cellObjectList = getCellsList(stringUrl);
                            if(cellObjectList != null){
                                //exemple
                                if(i>=0){
                                    cellObjectList.remove(i);
                                    i--;
                                }
                            }
                            else{
                                Toast.makeText(ViewsActivity.this, "Failure on refreshing", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            populateProductView();
                            //productObjectList = getProductList(stringUrl);

                            /*
                            if(i>=0){
                                productObjectList.remove(i);
                                i--;
                            }
                            */
                        }


                        //Update the list
                        mListView.invalidateViews();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        }).start();


    }


    public List<Line> getList(){
        return mWebService.getLineList();
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

    public void generateProducts(){
        String weekProdURL = currentRequest.getWeekProduction("1");
        try {
            currentInputString = new JSONTask().execute(weekProdURL).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        this.productObjectList = gson.fromJson(currentInputString,new TypeToken<List<ProductObject>>(){}.getType());


    }

    public class JSONTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection=null;
            BufferedReader reader=null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                //URL connection
                connection.connect();
                InputStream stream = new BufferedInputStream(connection.getInputStream());
                //retourner le flux
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);

                }
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Toast.makeText(ViewsActivity.this, result, Toast.LENGTH_SHORT).show();
            //currentInputString=result;
        }
    }
    public void quitCurrentActivity(View v){
        ViewsActivity.this.finish();
    }
}
