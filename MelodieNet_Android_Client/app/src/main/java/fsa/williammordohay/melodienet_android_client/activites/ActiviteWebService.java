package fsa.williammordohay.melodienet_android_client.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import fsa.williammordohay.melodienet_android_client.R;
import fsa.williammordohay.melodienet_android_client.connexionserviceweb.EcritureLangTel;
import fsa.williammordohay.melodienet_android_client.connexionserviceweb.LectureDonneesWeb;

/**
 * Created by william.mordohay on 22/05/2017.
 */

public abstract class ActiviteWebService extends AppCompatActivity
{

    public String recupereDonnees(String nomActivite,String urlRequete)
    {
        //réception de données provenants du service Web
        String donneesEntrantes="";
        try
        {
            donneesEntrantes = new LectureDonneesWeb().execute(urlRequete).get();
        }
        catch (InterruptedException e)
        {
            debugActivite(nomActivite);
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            debugActivite(nomActivite);
            e.printStackTrace();
        }
        return donneesEntrantes;
    }

    public String envoiDonnees(String nomActivite,String urlRequete, String langue){
        //envoi de données au service Web
        String donneesEntrantes="";
        try
        {
            donneesEntrantes = new EcritureLangTel().execute(urlRequete,langue).get();
        }
        catch (InterruptedException e)
        {
            debugActivite(nomActivite);
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            debugActivite(nomActivite);
            e.printStackTrace();
        }
        return donneesEntrantes;
    }


    public void debugActivite(String nomActivite)
    {
        Toast.makeText(this, R.string.erreur_connexion, Toast.LENGTH_LONG).show();
        switch(nomActivite)
        {
            case "Authentification":
                startActivity(new Intent(this, ActiviteAuthent.class));
                break;
            case "MachinesParam":
                startActivity(new Intent(this, ActiviteMenu.class));
                break;
            case "ModeMarche":
                startActivity(new Intent(this, ActiviteMenu.class));
                break;
        }
    }

    public String chargeParam()
    {
        SharedPreferences SharedParam = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //load the value enter by user in editURL. Default value is "http://val-prod-jfc/MelodieNet_REST_Service/" here
        return(SharedParam.getString("editURL", "http://val-prod-jfc/MelodieNet_REST_Service/"));
    }
}
