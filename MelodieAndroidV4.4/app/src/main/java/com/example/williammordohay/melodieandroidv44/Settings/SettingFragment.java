package com.example.williammordohay.melodieandroidv44.Settings;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import com.example.williammordohay.melodieandroidv44.R;

/**
 * Created by william.mordohay on 14/04/2017.
 */

public class SettingFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //load the preference xml
        addPreferencesFromResource(R.xml.preference);

        SwitchPreference switchPref = (SwitchPreference) findPreference("switch_preference");
        EditTextPreference urlPref = (EditTextPreference) findPreference("editURL");
        if(!switchPref.isChecked()){
            urlPref.setText("http://val-prod-jfc/MelodieNet_REST_Service/");
        }
        ListPreference linePref = (ListPreference) findPreference("lineList");
        linePref.setEntries(new String[]{"1", "1"});

    }
}
