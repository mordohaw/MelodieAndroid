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

import fsa.williammordohay.melodienet_android_client.R;
import fsa.williammordohay.melodienet_android_client.connexionserviceweb.ConstructeurUrl;
import fsa.williammordohay.melodienet_android_client.infoentrantes.Ligne;
import fsa.williammordohay.melodienet_android_client.production.TypeProduction;

public class ActiviteProductionParam extends ActiviteWebService
{

    private TypeProduction choixProdTab[];
    private List<Ligne> listeLignes = new ArrayList<>();
    private Spinner spinnerProd,spinnerLigne;
    private String productionChoisie, ligneChoisie, urlBase;
    private ConstructeurUrl requeteSpinner;
    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_production_param);

        //On remplit le tableau du spiner avec l'enum
        choixProdTab = genereSpinnerTab();

        //On rempli le spinner en utilisant un Array Adapter
        spinnerProd = (Spinner) findViewById(R.id.productTypeSpinner);
        ArrayAdapter<TypeProduction> adapteurSpinner = new ArrayAdapter<>(getApplicationContext(),
                R.layout.element_spinner,
                choixProdTab);
        spinnerProd.setAdapter(adapteurSpinner);

        //rempli le spinner des lignes
        spinnerLigne=(Spinner) findViewById(R.id.lineNumberSpinner);
        rempliSpinnerLigne();
    }


    public void rempliSpinnerLigne()
    {
        String spinnerURL,donnesEntrantes="";
        gson=new Gson();

        urlBase = chargeParam();
        requeteSpinner = new ConstructeurUrl(urlBase);
        spinnerURL=requeteSpinner.obtenirListeLignes();

        //get the data from WebService
        donnesEntrantes = recupereDonnees("ActiviteProductionParam", spinnerURL);

        if(donnesEntrantes != null)
        {
            //put the data in the arrayList productLineList
            this.listeLignes = gson.fromJson(donnesEntrantes,new TypeToken<List<Ligne>>(){}.getType());


            //On converti l'ArrayList en tableau
            String choixLigneTab[] = new String[listeLignes.size()];

            for(int i=0;i<listeLignes.size();i++)
            {
                choixLigneTab[i] = String.valueOf(listeLignes.get(i).getLigneNumber());
            }


            ArrayAdapter<String> adapteurLigne = new ArrayAdapter<>(this,
                    R.layout.element_spinner,
                    choixLigneTab);
            spinnerLigne.setAdapter(adapteurLigne);
        }
        else
        {
            Toast.makeText(ActiviteProductionParam.this, R.string.erreur_connexion, Toast.LENGTH_LONG).show();

        }
    }

    public void allerAProduction(View v)
    {
        Intent productionIntent = new Intent(ActiviteProductionParam.this, ActiviteProduction.class);
        //Bundle to share both values
        Bundle bundleProduction = new Bundle();

        productionChoisie = ((TypeProduction) spinnerProd.getSelectedItem()).name();
        ligneChoisie = (spinnerLigne.getSelectedItem()).toString();
        bundleProduction.putString("ProductionType", productionChoisie);
        bundleProduction.putString("LigneSelectionee", ligneChoisie);

        productionIntent.putExtras(bundleProduction);
        startActivity(productionIntent);
    }

    private TypeProduction[] genereSpinnerTab()
    {
        return TypeProduction.values();
    }


    public void quitter(View v)
    {
        ActiviteProductionParam.this.finish();
    }

}
