package com.example.williammordohay.simplewsproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.williammordohay.simplewsproject.Parameters.CellRunningModParam;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static android.R.attr.value;

public class MainActivity extends AppCompatActivity {

    private List<Line> productObjectList = new ArrayList<>();
    private Product productObject;
    private List<CellRunningModParam> MachineParamList = new ArrayList<>();
    RequestParams parametres = new RequestParams();
    boolean questionOnOneProduct;
    String WebserviceURL;
    JSONArray testV;
    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.MachineParamList.add(new CellRunningModParam(1, 1411));

        //HashMap<String, boolean> param = new HashMap<String, boolean>();
        //startService(new Intent(MainActivity.this, MonPremierService.class));



        parametres.put("","2");
        /* a décommenter params.put("numLigne", "1");
        params.put("numStation", "1411");*/

        //construction de l'URL
        URI uri = null;
        try {
            uri = new URI("http://val-prod-jfc/MelodieNet_REST_Service/GetLinesList/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        questionOnOneProduct=false;

        WebserviceURL = uri.toASCIIString();
    }


    public void invokeWS(RequestParams params,String URL){
        gson=new Gson();
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL,params ,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response="";
                for(int i=0; i<responseBody.length; i++)
                    response = response + (char)responseBody[i];


                /*try {
                    testV = new JSONArray(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
                if(!questionOnOneProduct){
                    //return a product List
                    productObjectList=gson.fromJson(response,new TypeToken<List<Line>>(){}.getType());
                    //productObjectList=gson.fromJson(response,new TypeToken<List<Product>>(){}.getType());
                }
                else{
                    //return a simple product
                    productObject=gson.fromJson(response,new TypeToken<Product>(){}.getType());
                }

                Toast.makeText(getApplicationContext(), "You are successfully logged in!", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // When Http response code is '404'
                if(statusCode == 404){
                    Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                }
                // When Http response code is '500'
                else if(statusCode == 500){
                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                }
                // When Http response code other than 404, 500
                else{
                    Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }

            }

        });
    }
    public void Onclick(View v){
        invokeWS(null,WebserviceURL);
    }


}
