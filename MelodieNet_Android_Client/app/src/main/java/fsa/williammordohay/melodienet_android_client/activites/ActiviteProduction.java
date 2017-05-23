package fsa.williammordohay.melodienet_android_client.activites;

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
import fsa.williammordohay.melodienet_android_client.production.AdapteurProduction;
import fsa.williammordohay.melodienet_android_client.production.Production;

public class ActiviteProduction extends ActiviteWebService {

    private SwipeRefreshLayout vueRafraichissement;
    private ListView vueListe;
    private List<Production> listeProduction = new ArrayList<>();
    private AdapteurProduction adapteurProduction;
    Gson gson;
    private String donnesEntrantes, urlBase,productionChoisie,ligneChoisie,urlProduction;
    private ConstructeurUrl constructRequetes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_production);

        gson=new Gson();
        urlBase=chargeParam();
        constructRequetes= new ConstructeurUrl(urlBase);

        //On récupère la valeur
        Bundle extras = getIntent().getExtras();
        productionChoisie = extras.getString("ProductionType");
        ligneChoisie = extras.getString("LigneSelectionee");

        vueListe=(ListView) findViewById(R.id.ProductView);
        rempliVue();

        vueRafraichissement = (SwipeRefreshLayout)findViewById(R.id.SwipeRefresh);
        vueRafraichissement.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rafraichirListe();
            }
        });
    }



    public void rempliVue() {
        //populate the ProductListView
        listeProduction = genereProduction();
        adapteurProduction=new AdapteurProduction(this,listeProduction);
        vueListe.setAdapter(adapteurProduction);
    }

    public List<Production> genereProduction() {
        switch (productionChoisie) {
            case "GetHourProduction":
                urlProduction = constructRequetes.obtenirProdHeure(ligneChoisie);
                break;
            case "GetDayProduction":
                urlProduction = constructRequetes.obtenirProdJour(ligneChoisie);
                break;
            case "GetWeekProduction":
                urlProduction = constructRequetes.obtenirProdSemaine(ligneChoisie);
                break;
        }
        donnesEntrantes = recupereDonnees("ActiviteProduction", urlProduction);

        return (gson.fromJson(donnesEntrantes, new TypeToken<List<Production>>() {}.getType()));

    }

    private void rafraichirListe(){


        Toast.makeText(ActiviteProduction.this, R.string.rafraichissement, Toast.LENGTH_SHORT).show();

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
                        rempliVue();

                        //Update the list
                        vueListe.invalidateViews();
                        vueRafraichissement.setRefreshing(false);
                    }
                });

            }
        }).start();


    }



    public void quitter(View v){
        ActiviteProduction.this.finish();
    }
}
