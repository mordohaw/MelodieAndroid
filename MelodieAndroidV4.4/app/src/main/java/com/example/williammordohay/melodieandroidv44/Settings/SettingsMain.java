package com.example.williammordohay.melodieandroidv44.Settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.williammordohay.melodieandroidv44.R;

public class SettingsMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void quitCurrentActivity(View v){
        SettingsMain.this.finish();
    }

    public void showFrag(View v){
        //Go to fragment settings activity
        startActivity(new Intent(this,SettingFragShow.class));

    }

    public void showVal(View v){
        //Create SharedPreferences to load values
        SharedPreferences SharedParam= PreferenceManager.getDefaultSharedPreferences(this);
        EditText editTxt=(EditText)findViewById(R.id.URL_content);
        //load the value enter by user in editURL. Default value is "http://val-prod-002/MelodieNet/Modules/EcransDeBase/Bienvenue.aspx" here
        String stringUrl = SharedParam.getString("editURL","http://val-prod-002/MelodieNet/Modules/EcransDeBase/Bienvenue.aspx");
        editTxt.setText(stringUrl);


    }

}
