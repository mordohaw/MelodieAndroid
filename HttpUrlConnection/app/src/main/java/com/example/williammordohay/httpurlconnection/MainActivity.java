package com.example.williammordohay.httpurlconnection;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    public List<Line> getLineObjectList() {
        return lineObjectList;
    }

    public void setLineObjectList(List<Line> lineObjectList) {
        this.lineObjectList = lineObjectList;
    }

    private List<Line> lineObjectList = new ArrayList<>();
    private TextView tvData;
    BgrdTask bgrdTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnData =(Button) findViewById(R.id.button);
        tvData =(TextView)findViewById(R.id.textView);
        new JSONTask().execute("http://val-prod-jfc/MelodieNet_REST_Service/GetCellsList/2");

        btnData.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //new JSONTask().execute("http://val-prod-jfc/MelodieNet_REST_Service/GetCellsList/2");
                //bgrdTask=new BgrdTask();
                //bgrdTask.execute("http://val-prod-jfc/MelodieNet_REST_Service/GetLinesList/");
            }
        });
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
            tvData.setText(result);
        }
    }


}
