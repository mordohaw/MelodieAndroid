package fsa.williammordohay.melodienet_android_client.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import fsa.williammordohay.melodienet_android_client.connexionserviceweb.LectureDonneesWeb;

/**
 * Created by william.mordohay on 22/05/2017.
 */

public abstract class ActiviteWebService extends AppCompatActivity {

    public String recupereDonnees(String nomActivite,String urlRequete){
        //get the data from WebService
        String donneesEntrantes="";
        try {
            donneesEntrantes = new LectureDonneesWeb().execute(urlRequete).get();
        } catch (InterruptedException e) {
            Toast.makeText(this, "Sorry, i can't find the WebService...", Toast.LENGTH_LONG).show();
            debugActivite(nomActivite);
            e.printStackTrace();
        } catch (ExecutionException e) {
            Toast.makeText(this, "Sorry, i can't find the WebService...", Toast.LENGTH_LONG).show();
            debugActivite(nomActivite);
            e.printStackTrace();
        }
        return donneesEntrantes;
    }

    public void debugActivite(String nomActivite){
        switch(nomActivite){
            case "Authent":
                startActivity(new Intent(this, ActiviteAuthent.class));
                break;
            case "ModeMarche":
                startActivity(new Intent(this, ActiviteModesMarche.class));
                break;
        }
    }
}
