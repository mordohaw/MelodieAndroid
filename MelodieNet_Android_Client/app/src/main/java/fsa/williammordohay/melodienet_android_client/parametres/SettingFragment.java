package fsa.williammordohay.melodienet_android_client.parametres;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import fsa.williammordohay.melodienet_android_client.R;

/**
 * Created by william.mordohay on 18/05/2017.
 */

public class SettingFragment extends PreferenceFragment {
    EditTextPreference urlPref;
    SwitchPreference switchPref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //load the preference xml
        addPreferencesFromResource(R.xml.preference);

        switchPref = (SwitchPreference) findPreference("switch_preference");
        urlPref = (EditTextPreference) findPreference("editURL");


        switchPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference,
                                              Object newValue) {
                if (!switchPref.isChecked()) {
                    urlPref.setText(urlPref.getText());
                } else {
                    //put the default value with this URL
                    urlPref.setDefaultValue("http://val-prod-002/MelodieNet_REST_Service/");
                    urlPref.setText("http://val-prod-002/MelodieNet_REST_Service/");
                }
                return true;
            }

        });
    }
}
