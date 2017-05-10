package com.example.williammordohay.melodieandroidv44.Settings;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.ListPreference;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by william.mordohay on 14/04/2017.
 */

public class SettingFragShow extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        //load the fragment
        ft.replace(android.R.id.content, new SettingFragment());
        ft.commit();

    }
}
