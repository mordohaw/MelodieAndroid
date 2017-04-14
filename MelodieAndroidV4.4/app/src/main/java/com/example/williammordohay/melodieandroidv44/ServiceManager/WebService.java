package com.example.williammordohay.melodieandroidv44.ServiceManager;

import com.example.williammordohay.melodieandroidv44.ServiceManager.Json;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
//import com.google.gson.Gson;

/**
 * Created by william.mordohay on 14/04/2017.
 */

public class WebService {
    private  final String URL = "http://val-prod-002/MelodieNet/Modules/EcransDeBase/Bienvenue.aspx";

    Json gson;

    public WebService(){
        gson = new Json();
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
}
