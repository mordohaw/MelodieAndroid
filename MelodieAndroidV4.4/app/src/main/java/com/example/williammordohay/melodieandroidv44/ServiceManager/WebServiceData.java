package com.example.williammordohay.melodieandroidv44.ServiceManager;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * Created by william.mordohay on 10/05/2017.
 */

public class WebServiceData extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... params) {
        if(android.os.Debug.isDebuggerConnected())
            android.os.Debug.waitForDebugger();

        HttpURLConnection connection=null;
        BufferedReader reader=null;
        try {
            URL url = new URL(params[0]);


            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            //URL connection
            connection.setConnectTimeout(5000); //set timeout to 5 seconds


            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream stream = new BufferedInputStream(connection.getInputStream());

                //retourner le flux
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                return buffer.toString();
            }
            else{
                return null;
            }

        } catch (MalformedURLException e) {
            //e.printStackTrace();
            return null;
        } catch (IOException e) {
            //e.printStackTrace();
            return null;
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
        //return null;
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
