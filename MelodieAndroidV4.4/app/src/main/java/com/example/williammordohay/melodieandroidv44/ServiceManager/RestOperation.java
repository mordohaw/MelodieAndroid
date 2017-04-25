package com.example.williammordohay.melodieandroidv44.ServiceManager;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by william.mordohay on 25/04/2017.
 */

public class RestOperation extends AsyncTask<String,Void,Void> {
    //final HttpURLConnection httpURLConnection;
    String content;
    String error;
    //ProgressDialog progressDialog = new ProgressDialog(RestOperation.this);
    String data;
    String output;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        try{
            data += "&" + URLEncoder.encode("data","UTF-8")+"-"+output;
        }catch(UnsupportedEncodingException e){

        }
    }

    @Override
    protected Void doInBackground(String... params) {
        BufferedReader br=null;
        try {
            URL url = new URL(params[0]);
            URLConnection connection = new URLConnection(url) {
                @Override
                public void connect() throws IOException {

                }
            };
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
