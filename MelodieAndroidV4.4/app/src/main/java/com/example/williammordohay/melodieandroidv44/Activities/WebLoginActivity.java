package com.example.williammordohay.melodieandroidv44.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.williammordohay.melodieandroidv44.R;
import com.example.williammordohay.melodieandroidv44.Security.ConnectionObject;
import com.example.williammordohay.melodieandroidv44.ServiceManager.RequestBuilder;
import com.example.williammordohay.melodieandroidv44.ServiceManager.WebServiceData;
import com.example.williammordohay.melodieandroidv44.Settings.SettingFragShow;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.williammordohay.melodieandroidv44.Security.HashPassword.hashPassword;

public class WebLoginActivity extends AppCompatActivity {

    String databaseUsername, databasePassword, databaseName, databaseIp;
    String currentUsername, currentPassword;
    EditText loginUsername, loginPassword;
    Button bLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_login);

        //Getting the differents components
        loginUsername = (EditText) findViewById(R.id.loginUsername);
        bLogin = (Button) findViewById(R.id.loginButton);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        loginPassword.setHint(R.string.sign_up_password);


        //check the login when click
        bLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                currentUsername = loginUsername.getText().toString();
                currentPassword = loginPassword.getText().toString();
                CheckLogin();
            }
        });
    }

    public void quitCurrentActivity(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.check_dialog_message)
                .setTitle(R.string.check_dialog_title)
                .setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // CONFIRM
                        WebLoginActivity.this.finish();
                    }
                })
                .setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // CANCEL
                    }
                });
        // Create the AlertDialog object
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void goToParameters(View v){
        startActivity(new Intent(this, SettingFragShow.class));
    }
    public void CheckLogin() {

        String baseURL, loginURL, result = "";
        Gson gson = new Gson();
        RequestBuilder loginRequest;


        if (currentUsername.trim().equals("") || currentPassword.trim().equals("")) {
            Toast.makeText(WebLoginActivity.this, getResources().getString(R.string.Not_enough_fields), Toast.LENGTH_LONG).show();
        } else {
            SharedPreferences SharedParam = PreferenceManager.getDefaultSharedPreferences(WebLoginActivity.this);
            //load the value enter by user in editURL. Default value is "http://val-prod-jfc/MelodieNet_REST_Service/" here
            baseURL = SharedParam.getString("editURL", "http://val-prod-jfc/MelodieNet_REST_Service/");
            loginRequest = new RequestBuilder(baseURL);
            String requestUser, requestPassword;
            requestUser = currentUsername;
                try {
                    requestPassword = hashPassword(currentPassword);
                    loginURL = loginRequest.getLoginAgreement(requestUser, requestPassword);
                    //get the data from WebService
                    try {
                        result = new WebServiceData().execute(loginURL).get();

                        if(result != ""){
                            ConnectionObject connect = gson.fromJson(result,ConnectionObject.class);
                            if (connect.isAgreement()) {
                                Toast.makeText(WebLoginActivity.this, R.string.Success, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(WebLoginActivity.this, MenuActivity.class));
                            } else {
                                Toast.makeText(WebLoginActivity.this, R.string.Login_failure, Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(WebLoginActivity.this, "can't find the Webservice", Toast.LENGTH_LONG).show();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                        Toast.makeText(WebLoginActivity.this, "can't find the Webservice : " +e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }







        }

    }

}