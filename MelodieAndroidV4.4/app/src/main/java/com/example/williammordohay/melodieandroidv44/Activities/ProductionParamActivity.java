package com.example.williammordohay.melodieandroidv44.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.williammordohay.melodieandroidv44.Line;
import com.example.williammordohay.melodieandroidv44.ProductRequestType;
import com.example.williammordohay.melodieandroidv44.R;
import com.example.williammordohay.melodieandroidv44.ServiceManager.RequestBuilder;
import com.example.williammordohay.melodieandroidv44.ServiceManager.WebServiceData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProductionParamActivity extends AppCompatActivity {

    ProductRequestType productChoiceArray[];
    private List<Line> productLineList = new ArrayList<>();
    Spinner productSpinner,lineSpinner;
    String productionSelected,lineSelected, baseURL;
    private RequestBuilder spinnerRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_parameters);

        //On remplit le tableau du spiner avec l'enum
        productChoiceArray = generateReportArray();

        //populate the product spinner
        productSpinner = (Spinner) findViewById(R.id.productTypeSpinner);
        ArrayAdapter<ProductRequestType> ProductRequestAdapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.row_product_name,
                productChoiceArray);
        productSpinner.setAdapter(ProductRequestAdapter);

        //populate the line spinner
        lineSpinner=(Spinner) findViewById(R.id.lineNumberSpinner);
        populatelineSpinner();

    }
    public void populatelineSpinner(){
        String spinnerURL,currentInputString="";
        Gson gson;
        gson=new Gson();

        SharedPreferences SharedParam= PreferenceManager.getDefaultSharedPreferences(ProductionParamActivity.this);
        //load the value enter by user in editURL. Default value is "http://val-prod-jfc/MelodieNet_REST_Service/" here
        baseURL = SharedParam.getString("editURL","http://val-prod-jfc/MelodieNet_REST_Service/");
        spinnerRequest = new RequestBuilder(baseURL);
        spinnerURL=spinnerRequest.getLinesList();

        //get the data from WebService
        try {
            currentInputString = new WebServiceData().execute(spinnerURL).get();
        } catch (InterruptedException e) {
            Toast.makeText(ProductionParamActivity.this, "Sorry, i can't find the WebService...", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (ExecutionException e) {
            Toast.makeText(ProductionParamActivity.this, "Sorry, i can't find the WebService...", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        if(currentInputString != null)
        {
            //put the data in the arrayList productLineList
            this.productLineList = gson.fromJson(currentInputString,new TypeToken<List<Line>>(){}.getType());


            String lineChoiceArray[] = new String[productLineList.size()];

            for(int i=0;i<productLineList.size();i++)
            {
                lineChoiceArray[i] = String.valueOf(productLineList.get(i).getLineNumber());
            }


            ArrayAdapter<String> lineNumberAdapter = new ArrayAdapter<>(this,
                    R.layout.row_product_name,
                    lineChoiceArray);
            lineSpinner.setAdapter(lineNumberAdapter);
        }
        else
        {
            Toast.makeText(ProductionParamActivity.this, "Sorry, i can't find the WebService...", Toast.LENGTH_LONG).show();

        }



    }
    public void quitCurrentActivity(View v){
        ProductionParamActivity.this.finish();
    }

    private ProductRequestType[] generateReportArray(){
        return ProductRequestType.values();
    }


    public void GoToProductsView(View v){
        Intent productionIntent = new Intent(ProductionParamActivity.this, ProductionActivity.class);
        //Bundle to share both values
        Bundle myBundle = new Bundle();

        productionSelected = ((ProductRequestType) productSpinner.getSelectedItem()).name();
        lineSelected = (lineSpinner.getSelectedItem()).toString();
        myBundle.putString("ProductionType", productionSelected);
        myBundle.putString("LineSelected", lineSelected);

        //productionIntent.putExtra("ProductionType", productionSelected);
        //productionIntent.putExtra("ProductionType", lineSelected);
        productionIntent.putExtras(myBundle);
        //finish();
        startActivity(productionIntent);
    }

}
