package fsa.williammordohay.melodienet_android_client.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import fsa.williammordohay.melodienet_android_client.infoentrantes.Ligne;
import fsa.williammordohay.melodienet_android_client.R;
import fsa.williammordohay.melodienet_android_client.infoentrantes.Cellule;
import fsa.williammordohay.melodienet_android_client.connexionserviceweb.ConstructeurUrl;

public class ActiviteMachinesParam extends ActiviteWebService {



    private List<Ligne> listeLignes = new ArrayList<>();
    Spinner spinnerLigne;
    String ligneChoisie, urlBase;
    private ConstructeurUrl requeteSpinner;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_machines_param);



        gson=new Gson();

        //On charge la base de la connexion
        urlBase = chargeParam();
        requeteSpinner = new ConstructeurUrl(urlBase);

        //on récupère les deux spinner
        spinnerLigne=(Spinner) findViewById(R.id.spinnerNumLigne);
        //spinnerCellules =(Spinner) findViewById(R.id.spinnerNumCellules);
        rempliSpinnerLignes(requeteSpinner,gson);
        //rempliSpinnerCellules(requeteSpinner,gson);
    }

    public void rempliSpinnerLignes(ConstructeurUrl requeteSpinnerLigne, Gson objetGson){

        String urlSpinner,currentInputString="";


        urlSpinner=requeteSpinnerLigne.obtenirListeLignes();

            //get the data from WebService
        currentInputString = recupereDonnees("MachinesParam",urlSpinner);

        if(currentInputString != null)
        {
            //put the data in the arrayList productLineList
            this.listeLignes = objetGson.fromJson(currentInputString,new TypeToken<List<Ligne>>(){}.getType());


            String ligneTab[] = new String[listeLignes.size()];

            for(int i = 0; i< listeLignes.size(); i++)
            {
                    ligneTab[i] = String.valueOf(listeLignes.get(i).getLigneNumber());
            }


            ArrayAdapter<String> lineNumberAdapter = new ArrayAdapter<>(this, R.layout.element_spinner, ligneTab);
            spinnerLigne.setAdapter(lineNumberAdapter);
        }
            else
            {
                Toast.makeText(ActiviteMachinesParam.this, "Sorry, i can't find the WebService...", Toast.LENGTH_LONG).show();

            }


    }

    public void quitter(View v)
    {
        ActiviteMachinesParam.this.finish();
    }
    public void allerAModesMarche(View v)
    {
        Intent modesMarcheIntent = new Intent(ActiviteMachinesParam.this, ActiviteModesMarche.class);
        ligneChoisie =(spinnerLigne.getSelectedItem()).toString();
        modesMarcheIntent.putExtra("ligneSelect", ligneChoisie);

        startActivity(modesMarcheIntent);
        //finish();

    }
}
