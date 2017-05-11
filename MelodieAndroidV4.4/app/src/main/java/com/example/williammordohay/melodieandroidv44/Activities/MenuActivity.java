package com.example.williammordohay.melodieandroidv44.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.williammordohay.melodieandroidv44.R;
import com.example.williammordohay.melodieandroidv44.Settings.SettingFragShow;
import com.example.williammordohay.melodieandroidv44.Settings.SettingsMain;

public class MenuActivity extends AppCompatActivity {
    private boolean choiceMachineTracking=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void goToParameters(View v){
        startActivity(new Intent(this, SettingFragShow.class));
    }

    public void quitCurrentActivity(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.check_disconnect_message)
                .setTitle(R.string.check_disconnect_title)
                .setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // CONFIRM
                        MenuActivity.this.finish();
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

    public void sharingChoiceValue(View v){
        Intent getButtonSelected = new Intent(MenuActivity.this, ViewsActivity.class);
        getButtonSelected.putExtra("userChoice",choiceMachineTracking);
        startActivity(getButtonSelected);
    }
    public void goToFirstActivity(View v){
        choiceMachineTracking=true;
        sharingChoiceValue(v);
    }

    public void goToSecondActivity(View v){
        startActivity(new Intent(MenuActivity.this, ProductionSpinnerActivity.class));
        //choiceMachineTracking=false;
        //sharingChoiceValue(v);
    }

}
