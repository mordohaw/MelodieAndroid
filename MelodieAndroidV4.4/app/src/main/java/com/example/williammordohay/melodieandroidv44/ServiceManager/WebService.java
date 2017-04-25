package com.example.williammordohay.melodieandroidv44.ServiceManager;


import android.util.Log;

import com.example.williammordohay.melodieandroidv44.Cell.CellObject;
import com.example.williammordohay.melodieandroidv44.Product.ProductObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by william.mordohay on 14/04/2017.
 */

public class WebService {
    private  static String URL=null;

    Gson gson;

    public WebService(){
        gson = new Gson();
    }
    private InputStream sendRequest(URL url) throws Exception{
        try{
            /**
             * Open Connection
             */
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            /**
             * Connection to URL
             */
            urlConnection.connect();

            if(urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                return urlConnection.getInputStream();
            }
        }catch(Exception e){
            throw new Exception("");
        }
        return null;
    }

    public List<CellObject> getCells(URL urlAdress) {

        try{
            //Send the request
            InputStream inputStream = sendRequest(new URL(URL));
            //Check the inputStream
            if(inputStream != null){
                //read the inputStream
                InputStreamReader reader = new InputStreamReader(inputStream);

                //return the list from Json
                return gson.fromJson(reader, new TypeToken<List<CellObject>>(){}.getType());
            }
        }
        catch(Exception e){
            Log.e("WebService","Can't get the Cells data !");
        }
        return null;
    }

    public List<ProductObject> getProduct() {


        try{
            //Send the request
            InputStream inputStream = sendRequest(new URL(URL));
            //Check the inputStream
            if(inputStream != null){
                //read the inputStream
                InputStreamReader reader = new InputStreamReader(inputStream);

                //return the list from Json
                return gson.fromJson(reader, new TypeToken<List<ProductObject>>(){}.getType());
            }
        }
        catch(Exception e){
            Log.e("WebService","Can't get the Products data !");
        }
        return null;
    }
}

