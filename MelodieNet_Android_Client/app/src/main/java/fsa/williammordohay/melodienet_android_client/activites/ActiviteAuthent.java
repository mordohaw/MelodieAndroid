package fsa.williammordohay.melodienet_android_client.activites;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import fsa.williammordohay.melodienet_android_client.R;
import fsa.williammordohay.melodienet_android_client.connexionserviceweb.ConstructeurUrl;
import fsa.williammordohay.melodienet_android_client.connexionserviceweb.EcritureLangTel;
import fsa.williammordohay.melodienet_android_client.connexionserviceweb.LectureDonneesWeb;
import fsa.williammordohay.melodienet_android_client.parametres.VueParametres;
import fsa.williammordohay.melodienet_android_client.securite.LoginObject;

import static fsa.williammordohay.melodienet_android_client.securite.CryptageMdp.hashMdp;

public class ActiviteAuthent extends AppCompatActivity {

    String utilisateurActuel, motPasseActuel;
    EditText utilisateurActuelEditText, motPasseEditText;
    Button AuthentBoutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_authent);

        //Remplir les différents composants
        utilisateurActuelEditText = (EditText) findViewById(R.id.loginUsername);
        AuthentBoutton = (Button) findViewById(R.id.loginButton);
        motPasseEditText = (EditText) findViewById(R.id.loginPassword);
        motPasseEditText.setHint(R.string.mot_de_passe);

        AuthentBoutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                utilisateurActuel = utilisateurActuelEditText.getText().toString();
                motPasseActuel = motPasseEditText.getText().toString();
                authentification();
            }
        });
    }

    private void authentification() {
        String baseURL, loginURL, langURL;
        String loginResult = "", langPostResult="";
        Gson gson = new Gson();
        ConstructeurUrl authentRequete;


        if (utilisateurActuel.trim().equals("") || motPasseActuel.trim().equals("")) {
            Toast.makeText(ActiviteAuthent.this, getResources().getString(R.string.champs_vides), Toast.LENGTH_LONG).show();
        } else {
            baseURL = chargeParam();
            authentRequete = new ConstructeurUrl(baseURL);
            String requestUser, requestPassword;
            requestUser = utilisateurActuel;
            try {
                requestPassword = hashMdp(motPasseActuel);
                loginURL = authentRequete.obtenirAccordLogin(requestUser, requestPassword);
                //get the data from WebService
                try {
                    loginResult = new LectureDonneesWeb().execute(loginURL).get();

                    if(loginResult != ""){
                        LoginObject connect = gson.fromJson(loginResult,LoginObject.class);
                        if (connect.isAgreement()) {
                            String resultat = envoiLangue(authentRequete);
                            Toast.makeText(ActiviteAuthent.this, R.string.succes_authent, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(ActiviteAuthent.this, ActiviteMenu.class));
                        } else {
                            Toast.makeText(ActiviteAuthent.this, R.string.echec_authent, Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(ActiviteAuthent.this, "can't find the Webservice", Toast.LENGTH_LONG).show();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //debug
                    startActivity(new Intent(ActiviteAuthent.this, ActiviteAuthent.class));
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    //debug
                    startActivity(new Intent(ActiviteAuthent.this, ActiviteAuthent.class));
                    Toast.makeText(ActiviteAuthent.this, "can't find the Webservice : " +e.getMessage(), Toast.LENGTH_LONG).show();
                }

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

        }
    }

    public String chargeParam(){
        SharedPreferences SharedParam = PreferenceManager.getDefaultSharedPreferences(ActiviteAuthent.this);
        //load the value enter by user in editURL. Default value is "http://val-prod-jfc/MelodieNet_REST_Service/" here
        return(SharedParam.getString("editURL", "http://val-prod-002/MelodieNet_REST_Service/"));
    }
    public String envoiLangue(ConstructeurUrl requete) {
        String langURL, langPostResult="";

        String langString = Locale.getDefault().getLanguage();
        langURL = requete.envoyerLang(langString);
        try {
            langPostResult = new EcritureLangTel().execute(langURL, langString).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return langPostResult;
    }

    public void quitter(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.demande_quitter)
                .setTitle(R.string.quitter_dialogue_titre)
                .setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // CONFIRM
                        ActiviteAuthent.this.finish();
                    }
                })
                .setNegativeButton(R.string.non, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // CANCEL
                    }
                });
        // Create the AlertDialog object
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void allerAuxParam(View v){
        startActivity(new Intent(this, VueParametres.class));
    }


}
