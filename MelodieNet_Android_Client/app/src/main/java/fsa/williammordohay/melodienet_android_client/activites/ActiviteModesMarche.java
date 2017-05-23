package fsa.williammordohay.melodienet_android_client.activites;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import fsa.williammordohay.melodienet_android_client.R;
import fsa.williammordohay.melodienet_android_client.connexionserviceweb.ConstructeurUrl;
import fsa.williammordohay.melodienet_android_client.infoentrantes.Cellule;
import fsa.williammordohay.melodienet_android_client.modesmarche.AdapteurModesMarche;
import fsa.williammordohay.melodienet_android_client.modesmarche.ModeMarche;

public class ActiviteModesMarche extends ActiviteWebService{

    private SwipeRefreshLayout vueRafraichissement;
    private ListView vueListe;
    private List<ModeMarche> listeModes = new ArrayList<>();
    private List<Cellule> listeCellules = new ArrayList<>();
    private AdapteurModesMarche adapteurModesMarche;
    private Gson gson;
    private String informationEntrante,ligneSelectionnee, baseUrl, modeMarcheUrl;
    private ConstructeurUrl constructRequetes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_modes_marche);
        gson=new Gson();
        baseUrl =chargeParam();
        //initialise le constructeur d'URL
        constructRequetes = new ConstructeurUrl(baseUrl);

        //on récupère le numéro de ligne choisi
        ligneSelectionnee = getIntent().getStringExtra("ligneSelect");

        vueListe = (ListView) findViewById(R.id.liste_modes_marche);

        listeCellules = recupereListeCellule(constructRequetes,ligneSelectionnee);
        rempliListe();

        vueRafraichissement = (SwipeRefreshLayout)findViewById(R.id.SwipeRefresh);
        vueRafraichissement.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rafraichirListe();
            }
        });

    }

    public void rempliListe(){
        String resultatModesMarches=construitListeModes(listeCellules);
        listeModes = gson.fromJson(resultatModesMarches,new TypeToken<List<ModeMarche>>(){}.getType());

        adapteurModesMarche=new AdapteurModesMarche(this,listeModes);
        vueListe.setAdapter(adapteurModesMarche);
    }

    public String construitListeModes(List<Cellule> listeCellules){
        String listeModesMarche="[";

        for (Cellule c : listeCellules) {
            modeMarcheUrl =constructRequetes.obtenirModesMarche(ligneSelectionnee,String.valueOf(c.getCellNumber()));


            //get the data from WebService
            if(listeModesMarche == "["){
                listeModesMarche = listeModesMarche + recupereDonnees("ModeMarche", modeMarcheUrl);;
                }
            else{
                listeModesMarche = listeModesMarche + "," + recupereDonnees("ModeMarche", modeMarcheUrl);;
            }


        }
        listeModesMarche +="]";

        return listeModesMarche;
    }

    public ArrayList<Cellule> recupereListeCellule(ConstructeurUrl constructeurRequete,String numLigne){
        String celluleUrl,chaineEntrante = "";

        celluleUrl=constructeurRequete.obtenirListeCellules(numLigne);

        //get the data from WebService
        informationEntrante = recupereDonnees("ModeMarche",celluleUrl);

        if(informationEntrante != null){
            return gson.fromJson(informationEntrante,new TypeToken<List<Cellule>>(){}.getType());
        }
        return null;
    }

    private void rafraichirListe(){


        Toast.makeText(ActiviteModesMarche.this, R.string.rafraichissement, Toast.LENGTH_SHORT).show();

        //set the rafraichirListe
        vueListe.invalidateViews();
        vueRafraichissement.setRefreshing(true);

        //rafraichirListe long-time task in background thread
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
                        rempliListe();

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
