package fsa.williammordohay.melodienet_android_client.connexionserviceweb;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by william.mordohay on 18/05/2017.
 */

public class EcritureLangTel extends AsyncTask<String, String, String>
{
    @Override
    protected String doInBackground(String... params)
    {
        if(android.os.Debug.isDebuggerConnected())
            android.os.Debug.waitForDebugger();

        HttpURLConnection connexionService=null;
        OutputStreamWriter fluxSortie;
        String resultat="";
        try
        {
            URL url = new URL(params[0]);
            String lang = params[1];

            connexionService = (HttpURLConnection) url.openConnection();
            connexionService.setRequestMethod("POST");
            connexionService.setConnectTimeout(15000 /* milliseconds */);

            //URL connection
            connexionService.connect();

            fluxSortie = new OutputStreamWriter(connexionService.getOutputStream());
            fluxSortie.write(lang);

            fluxSortie.close();

            //retourner le flux
            resultat = connexionService.getResponseMessage();


        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (connexionService != null)
            {
                connexionService.disconnect();
            }
        }
        return resultat;
    }
}
