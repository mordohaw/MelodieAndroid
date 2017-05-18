package fsa.williammordohay.melodienet_android_client.parametres;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by william.mordohay on 18/05/2017.
 */

public class VueParametres extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        //charge le fragment
        ft.replace(android.R.id.content, new SettingFragment());
        ft.commit();
    }
}
