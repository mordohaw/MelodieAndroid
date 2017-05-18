package com.example.williammordohay.melodieandroidv44.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.williammordohay.melodieandroidv44.R;
import com.example.williammordohay.melodieandroidv44.Security.ConnectionObject;
import com.example.williammordohay.melodieandroidv44.ServiceManager.RequestBuilder;
import com.example.williammordohay.melodieandroidv44.ServiceManager.WebServiceData;
import com.example.williammordohay.melodieandroidv44.ServiceManager.WebServiceSender;
import com.example.williammordohay.melodieandroidv44.Settings.SettingFragShow;
import com.google.gson.Gson;

import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import static com.example.williammordohay.melodieandroidv44.Security.HashPassword.hashPassword;

public class WebLoginActivity extends AppCompatActivity {

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


        /*String baseURL = loadParameters();
        RequestBuilder langRequest = new RequestBuilder(baseURL);
        sendLangage(langRequest);*/
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

    public String loadParameters(){
        SharedPreferences SharedParam = PreferenceManager.getDefaultSharedPreferences(WebLoginActivity.this);
        //load the value enter by user in editURL. Default value is "http://val-prod-jfc/MelodieNet_REST_Service/" here
        return(SharedParam.getString("editURL", "http://val-prod-jfc/MelodieNet_REST_Service/"));
    }
    public void sendLangage(RequestBuilder request) {
        String langURL, langPostResult="";

        String langString = Locale.getDefault().getLanguage();
        langURL = request.postlangage(langString);
        try {
            langPostResult = new WebServiceSender().execute(langURL, langString).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.e("successss",langPostResult);
        if (langPostResult == "success") {
            Log.e("successss","canardddd");
        }else{
            Log.e("failure","canardddd");
        }
    }
    public void CheckLogin() {

        String baseURL, loginURL, langURL;
        String loginResult = "", langPostResult="";
        Gson gson = new Gson();
        RequestBuilder loginRequest;


        if (currentUsername.trim().equals("") || currentPassword.trim().equals("")) {
            Toast.makeText(WebLoginActivity.this, getResources().getString(R.string.Not_enough_fields), Toast.LENGTH_LONG).show();
        } else {
            baseURL = loadParameters();
            loginRequest = new RequestBuilder(baseURL);
            String requestUser, requestPassword;
            requestUser = currentUsername;
                try {
                    requestPassword = hashPassword(currentPassword);
                    loginURL = loginRequest.getLoginAgreement(requestUser, requestPassword);
                    //get the data from WebService
                    try {
                        loginResult = new WebServiceData().execute(loginURL).get();

                        if(loginResult != ""){
                            ConnectionObject connect = gson.fromJson(loginResult,ConnectionObject.class);
                            if (connect.isAgreement()) {
                                sendLangage(loginRequest);
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

}