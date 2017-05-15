package com.example.williammordohay.melodieandroidv44.ServiceManager;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

/**
 * Created by william.mordohay on 15/05/2017.
 */

    public class WebServiceSender extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            if(android.os.Debug.isDebuggerConnected())
                android.os.Debug.waitForDebugger();

            HttpURLConnection connection=null;
            BufferedReader reader=null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                //URL connection
                connection.connect();

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    String lang = Locale.getDefault().getLanguage();//get the langage of the current device
                    OutputStream out = new BufferedOutputStream(connection.getOutputStream());

                    out.write(Integer.parseInt(lang));
                    //retourner le flux


                    return "success";
                }
                else{
                    return "fail";
                }

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


