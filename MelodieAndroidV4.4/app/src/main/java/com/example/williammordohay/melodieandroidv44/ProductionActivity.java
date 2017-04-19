package com.example.williammordohay.melodieandroidv44;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.williammordohay.melodieandroidv44.Product.ProductAdapter;
import com.example.williammordohay.melodieandroidv44.Product.ProductObject;

import java.util.ArrayList;
import java.util.List;

public class ProductionActivity extends AppCompatActivity {

    private ListView mListView;
    private List<ProductObject> productObjectList = new ArrayList<>();
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production);
        populateProductView();
    }

    public void quitCurrentActivity(View v){
        ProductionActivity.this.finish();
    }

    public void generateProducts(){

        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));
        this.productObjectList.add(new ProductObject("6-B9 G",1023,27));

    }


    public void populateProductView(){
        generateProducts();
        mListView=(ListView) findViewById(R.id.ProductView);
        adapter=new ProductAdapter(this,productObjectList);
        mListView.setAdapter(adapter);

    }
}
