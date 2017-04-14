package com.example.williammordohay.melodieandroidv44;

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
        Menu.this.finish();
    }

    public void goToFirstActivity(View v){
        startActivity(new Intent(this, FirstActivity.class));
    }

    public void goToSecondActivity(View v){
        startActivity(new Intent(this, SecondActivity.class));
    }

}
