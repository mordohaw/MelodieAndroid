package com.example.williammordohay.melodieandroidv44;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.williammordohay.melodieandroidv44.Settings.SettingsMain;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void goToParameters(View v){
        startActivity(new Intent(this, SettingsMain.class));
    }

    public void quitCurrentActivity(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.check_dialog_message)
                .setTitle(R.string.check_dialog_title)
                .setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // CONFIRM
                        Menu.this.finish();
                    }
                })
                .setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // CANCEL
                    }
                });
        // Create the AlertDialog object
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void goToFirstActivity(View v){
        startActivity(new Intent(this, FirstActivity.class));
    }

    public void goToSecondActivity(View v){
        startActivity(new Intent(this, SecondActivity.class));
    }

}
