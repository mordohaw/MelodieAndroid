package com.example.williammordohay.melodieandroidv44.ServiceManager;

import android.os.AsyncTask;


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by william.mordohay on 15/05/2017.
 */

    public class WebServiceSender extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            if(android.os.Debug.isDebuggerConnected())
                android.os.Debug.waitForDebugger();

            HttpURLConnection connection=null;
            OutputStreamWriter out;
            try {
                URL url = new URL(params[0]);
                String lang = params[1];

                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setConnectTimeout(15000 /* milliseconds */);

                //URL connection
                connection.connect();

                out = new OutputStreamWriter(connection.getOutputStream());
                out.write(lang);

                out.close();

                //retourner le flux
                return connection.getResponseMessage();


            } catch (MalformedURLException e) {
                e.printStackTrace();
                return("tata");
            } catch (IOException e) {
                e.printStackTrace();
                return("tata");
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }



    }


