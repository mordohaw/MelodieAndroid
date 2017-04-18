package com.example.williammordohay.melodieandroidv44;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ProductionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production);
    }

    public void quitCurrentActivity(View v){
        ProductionActivity.this.finish();
    }
}
