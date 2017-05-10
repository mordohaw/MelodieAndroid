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
import java.util.Locale;
import java.util.concurrent.ExecutionException;

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
        Button btnData2 =(Button) findViewById(R.id.button2);
        tvData =(TextView)findViewById(R.id.textView);
        String lang = Locale.getDefault().getLanguage();//get the langage of the current device
        tvData.setText(lang);

        btnData2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    String req2 =new BgrdTask().execute(" http://val-prod-jfc/MelodieNet_REST_Service/GetLinesList/").get();
                    tvData.setText(req2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        btnData.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    String req1 =new BgrdTask().execute("http://val-prod-jfc/MelodieNet_REST_Service/GetLoginAgreement/administrateur/c2fca02304de447157701d00c2d64094").get();
                    tvData.setText(req1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                /*InputStream stream = null;
                BufferedReader reader=null;
                try {
                    stream = new BgrdTask().execute("http://val-prod-jfc/MelodieNet_REST_Service/GetLoginAgreement/administrateur/c2fca02304de447157701d00c2d64094").get();
                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();
                    String line = "";
                    try {
                        while ((line = reader.readLine()) != null) {
                            buffer.append(line);

                        }
                        tvData.setText(buffer.toString());
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }*/



                //new JSONTask().execute("http://val-prod-jfc/MelodieNet_REST_Service/GetLoginAgreement/administrateur/c2fca02304de447157701d00c2d64094");
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
