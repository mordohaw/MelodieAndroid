package fsa.williammordohay.melodienet_android_client.activites;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fsa.williammordohay.melodienet_android_client.R;
import fsa.williammordohay.melodienet_android_client.parametres.VueParametres;

public class ActiviteMenu extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_menu);
    }

    public void allerAuxParam(View v){
        startActivity(new Intent(this, VueParametres.class));
    }

    public void quitter(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.demande_deconnexion)
                .setTitle(R.string.demande_deco_titre)
                .setPositiveButton(R.string.oui, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // CONFIRM
                        ActiviteMenu.this.finish();
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

    public void AllerSuiviMachine(View v){
        startActivity(new Intent(ActiviteMenu.this, ActiviteMachinesParam.class));
    }

    public void AllerSuiviProd(View v){
        startActivity(new Intent(ActiviteMenu.this, ActiviteProductionParam.class));
    }
}
