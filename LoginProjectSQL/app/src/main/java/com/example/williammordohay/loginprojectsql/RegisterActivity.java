package com.example.williammordohay.loginprojectsql;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterActivity extends AppCompatActivity {

    Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText registerUsername = (EditText) findViewById(R.id.registerUsername);
        final EditText registerName = (EditText) findViewById(R.id.registerName);
        final EditText registerPassword = (EditText) findViewById(R.id.registerPassword);
        registerPassword.setHint(R.string.sign_up_password);

        final Button bRegister = (Button) findViewById(R.id.registerButton);

        bRegister.setOnClickListener(new View.OnClickListener() {

            //convert values inside the textView to strings
            @Override
            public void onClick(View v) {
                final String name = registerName.getText().toString();
                final String userName = registerUsername.getText().toString();
                final String password = registerPassword.getText().toString();
            }
        });
    }
}

    /*public class CheckLogin extends AsyncTask<String,String,String> {

        String informUser="";
        Boolean LoginSuccefully=false;

        @Override
        protected void onPreExecute() {
        }

/*
        @Override
        protected String doInBackground(String... params) {
            if(currentUsername.trim().equals("")|| currentPassword.trim().equals("")){
                informUser="Please enter Username and Password";
            }else{
                try{
                    con=connectionFunction(databaseUsername,databasePassword,databaseName,databaseIp); //connection to DataBase
                    if(con==null){
                        informUser="Check your internet access";
                    }else{
                        String query= "select * from dbo.T2_UTILISATEURS where NOM_UTILISATEUR= '" + currentUsername.toString()+"'  ";
                        /*String query ="SELECT COUNT(*), \" + Constantes.T2_UTILISATEURS_NOM_UTILISATEUR + \", \" + Constantes.T2_UTILISATEURS_MOT_PASSE + \" \" +\n" +
                                "FROM \" + Constantes.T2_UTILISATEURS" +
                               "WHERE \" + Constantes.T2_UTILISATEURS_NOM_UTILISATEUR + \" = \"+ textBoxNomUtilisateur.Text.Replace(\"'\",\"''\") +" +
                                "GROUP BY \" + Constantes.T2_UTILISATEURS_NOM_UTILISATEUR + \", \" + Constantes.T2_UTILISATEURS_MOT_PASSE";*/
                        //String query = "select * from login where user_name= '" + currentUsername.toString() + "' and pass_word = '"+ currentPassword.toString() +"'  ";
                       /* Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if(rs.next())
                        {
                            informUser = "Login successful";
                            LoginSuccefully=true;
                            con.close();
                        }
                        else
                        {
                            informUser = "Login failed !";
                            LoginSuccefully = false;
                        }
                    }
                }
                catch (Exception ex)
                {
                    LoginSuccefully = false;
                    informUser = ex.getMessage();
                }
            }
            return informUser;
        }


        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
            if(LoginSuccefully)
            {
                Toast.makeText(RegisterActivity.this , "Register Successfull" , Toast.LENGTH_LONG).show();
                //finish();
            }
        }
    }


    public Connection connectionFunction(String user, String password, String database, String server)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + server +"/"+ database + ";user=" + user+ ";password=" + password + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se)
        {
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }
}*/
