package fsa.williammordohay.melodienet_android_client.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fsa.williammordohay.melodienet_android_client.R;
import fsa.williammordohay.melodienet_android_client.connexionserviceweb.ConstructeurUrl;
import fsa.williammordohay.melodienet_android_client.connexionserviceweb.LectureDonneesWeb;
import fsa.williammordohay.melodienet_android_client.infoentrantes.Cellule;
import fsa.williammordohay.melodienet_android_client.modesmarche.AdapteurModesMarche;
import fsa.williammordohay.melodienet_android_client.modesmarche.ModeMarche;

public class ActiviteModesMarche extends ActiviteWebService{

    private SwipeRefreshLayout vueRafraichissement;
    private ListView vueListe;
    private List<ModeMarche> listeModes = new ArrayList<>();
    private List<Cellule> listeCellules = new ArrayList<>();
    private List<ModeMarche> listeModesService = new ArrayList<>();
    private AdapteurModesMarche adapteurModesMarche;
    private Gson gson;
    private String currentInputString,productionType,ligneSelectionnee,baseURL,modeMarcheURL;
    private ConstructeurUrl constructRequetes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_modes_marche);
        gson=new Gson();
        baseURL=chargeParam();
        //initialise le constructeur d'URL
        constructRequetes = new ConstructeurUrl(baseURL);

        //on récupère le numéro de ligne choisi
        ligneSelectionnee = getIntent().getStringExtra("ligneSelect");

        vueListe = (ListView) findViewById(R.id.liste_modes_marche);

        listeCellules = recupereListeCellule(constructRequetes,ligneSelectionnee);
        String resultatModesMarches=construitListeModes(listeCellules);
        /*String resultatModesMarches="[";
        for (Cellule c : listeCellules) {
            modeMarcheURL=constructRequetes.obtenirModesMarche(ligneSelectionnee,String.valueOf(c.getCellNumber()));

            //get the data from WebService
            try {
                if(resultatModesMarches == "["){
                    resultatModesMarches = resultatModesMarches + new LectureDonneesWeb().execute(modeMarcheURL).get();
                }
                else{
                    resultatModesMarches = resultatModesMarches + "," + new LectureDonneesWeb().execute(modeMarcheURL).get();
                }
            } catch (InterruptedException e) {
                Toast.makeText(ActiviteModesMarche.this, "Sorry, i can't find the WebService...", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, ActiviteModesMarche.class));
                e.printStackTrace();
            } catch (ExecutionException e) {
                Toast.makeText(ActiviteModesMarche.this, "Sorry, i can't find the WebService...", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ActiviteModesMarche.this, ActiviteModesMarche.class));
                e.printStackTrace();
            }
        }
        resultatModesMarches +="]";*/
        listeModes = gson.fromJson(resultatModesMarches,new TypeToken<List<ModeMarche>>(){}.getType());

        adapteurModesMarche=new AdapteurModesMarche(this,listeModes);
        vueListe.setAdapter(adapteurModesMarche);
        vueRafraichissement = (SwipeRefreshLayout)findViewById(R.id.SwipeRefresh);
        vueRafraichissement.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

    }
    public String chargeParam(){
        SharedPreferences SharedParam = PreferenceManager.getDefaultSharedPreferences(ActiviteModesMarche.this);
        //load the value enter by user in editURL. Default value is "http://val-prod-jfc/MelodieNet_REST_Service/" here
        return(SharedParam.getString("editURL", "http://val-prod-002/MelodieNet_REST_Service/"));
    }

    public String construitListeModes(List<Cellule> listeCellules){
        String listeModesMarche="[";

        for (Cellule c : listeCellules) {
            modeMarcheURL=constructRequetes.obtenirModesMarche(ligneSelectionnee,String.valueOf(c.getCellNumber()));


            //get the data from WebService
            if(listeModesMarche == "["){
                listeModesMarche = listeModesMarche + recupereDonnees("ModeMarche",modeMarcheURL);;
                }
            else{
                listeModesMarche = listeModesMarche + "," + recupereDonnees("ModeMarche",modeMarcheURL);;
            }


        }
        listeModesMarche +="]";

        return listeModesMarche;
    }

    public ArrayList<Cellule> recupereListeCellule(ConstructeurUrl constructeurRequete,String numLigne){
        String celluleUrl,chaineEntrante = "";

        celluleUrl=constructeurRequete.obtenirListeCellules(numLigne);

        //get the data from WebService
        try {
            currentInputString = new LectureDonneesWeb().execute(celluleUrl).get();
        } catch (InterruptedException e) {
            Toast.makeText(ActiviteModesMarche.this, "Sorry, i can't find the WebService...", Toast.LENGTH_LONG).show();
            startActivity(new Intent(ActiviteModesMarche.this, ActiviteModesMarche.class));
            e.printStackTrace();
        } catch (ExecutionException e) {
            Toast.makeText(ActiviteModesMarche.this, "Sorry, i can't find the WebService...", Toast.LENGTH_LONG).show();
            startActivity(new Intent(ActiviteModesMarche.this, ActiviteModesMarche.class));
            e.printStackTrace();
        }
        if(currentInputString != null){
            return gson.fromJson(currentInputString,new TypeToken<List<Cellule>>(){}.getType());
        }
        return null;
    }

    private void refresh(){


        Toast.makeText(ActiviteModesMarche.this, R.string.refresh, Toast.LENGTH_SHORT).show();

        //set the refresh
        vueListe.invalidateViews();
        vueRafraichissement.setRefreshing(true);

        //refresh long-time task in background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //dummy delay for 1 second
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //update ui on UI thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //set the action on up dating

                        //startService(serviceIntent);
                        populateProductView();

                        //Update the list
                        vueListe.invalidateViews();
                        vueRafraichissement.setRefreshing(false);
                    }
                });

            }
        }).start();


    }

    public void quitter(View v){
        ActiviteModesMarche.this.finish();
    }
}
