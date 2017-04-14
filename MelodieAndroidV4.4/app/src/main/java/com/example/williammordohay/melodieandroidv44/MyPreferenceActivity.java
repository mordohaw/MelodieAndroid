package com.example.williammordohay.melodieandroidv44;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by william.mordohay on 14/04/2017.
 */

public class MyPreferenceActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment fragment = new SettingsScreen();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        //When this is the first creation
        if(savedInstanceState == null){
            fragmentTransaction.add(R.id.relativeLayout, fragment, "settings_fragment");
            fragmentTransaction.commit();
        }else{
            fragment = getFragmentManager().findFragmentByTag("settings_fragment");
        }
    }
    public static class SettingsScreen extends PreferenceFragment{
        public void OnCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference);
        }
    }

}
