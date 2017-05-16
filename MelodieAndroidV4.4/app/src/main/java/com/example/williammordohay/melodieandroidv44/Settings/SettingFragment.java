package com.example.williammordohay.melodieandroidv44.Settings;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.williammordohay.melodieandroidv44.R;

/**
 * Created by william.mordohay on 14/04/2017.
 */

public class SettingFragment extends PreferenceFragment {

    EditTextPreference urlPref;
    SwitchPreference switchPref;
    String customUrl;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //load the preference xml
        addPreferencesFromResource(R.xml.preference);

        switchPref = (SwitchPreference) findPreference("switch_preference");
        urlPref = (EditTextPreference) findPreference("editURL");


        /*if(!switchPref.isChecked()){
            urlPref.setText("http://val-prod-002/MelodieNet_REST_Service/");
        }*/

        switchPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference,
                                              Object newValue) {
                if(!switchPref.isChecked()){
                    //urlPref.setDefaultValue("http://val-prod-002/MelodieNet_REST_Service/");
                    //urlPref.setText("http://val-prod-002/MelodieNet_REST_Service/");
                    Log.e("DEBUUUGGGGGG", "I passed hereee");
                    //customUrl = urlPref.getText();
                    //load the url pass in param
                    urlPref.setText(urlPref.getText());
                }else{
                    //put the default value with this URL
                   // urlPref.onSetInitialValue
                    urlPref.setDefaultValue("http://val-prod-002/MelodieNet_REST_Service/");
                    urlPref.setText("http://val-prod-002/MelodieNet_REST_Service/");
                    //urlPref.setDefaultValue("http://val-prod-002/MelodieNet_REST_Service/");
                }
                return true;
            }

        });
        ListPreference linePref = (ListPreference) findPreference("lineList");
        linePref.setEntries(new String[]{"1", "1"});


    }
    public void switchAction(View v){
        urlPref.setDefaultValue("http://val-prod-002/MelodieNet_REST_Service/");
        if(!switchPref.isChecked()){
            urlPref.setText("http://val-prod-002/MelodieNet_REST_Service/");
        }
    }
}
