package com.example.williammordohay.melodieandroidv44;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {

    private ArrayList cells = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        CompleteCells();
    }

    public void quitCurrentActivity(View v){
        FirstActivity.this.finish();
    }
    public void CompleteCells(){
        cells.add(1410);
        cells.add(2140);
    }

}
