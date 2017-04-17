package com.example.williammordohay.melodieandroidv44.Settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

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
    }
}
