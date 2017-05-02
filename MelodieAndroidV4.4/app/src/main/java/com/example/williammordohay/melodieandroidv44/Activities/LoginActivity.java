package com.example.williammordohay.melodieandroidv44.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.williammordohay.melodieandroidv44.Security.HashPassword.hashPassword;


import com.example.williammordohay.melodieandroidv44.R;

public class LoginActivity extends AppCompatActivity {

    Connection con;
    String databaseUsername,databasePassword,databaseName,databaseIp;
    String currentUsername, currentPassword;
    EditText loginUsername,loginPassword;
    Button bLogin;
    ProgressBar progressBar;
    TextView registerLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Getting the differents components
        loginUsername= (EditText) findViewById(R.id.loginUsername);
        bLogin = (Button) findViewById(R.id.loginButton);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        registerLink = (TextView) findViewById(R.id.registerLink);
        loginPassword.setHint(R.string.sign_up_password);

        progressBar.setVisibility(View.GONE);
        registerLink.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        //enter the different parameters for connection
        databaseIp="VAL-PROD-002";
        databaseName="MelodieNetTest";
        databaseUsername ="sa";
        databasePassword="melodienet";

        //check the login when click
        bLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                currentUsername =loginUsername.getText().toString();
                currentPassword =loginPassword.getText().toString();
                CheckLogin checkLogin = new CheckLogin();//the asyncktask that is used to check the login and password
                checkLogin.execute("");
            }
        });

    }
    public class CheckLogin extends AsyncTask<String,String,String> {

        String informUser="";
        Boolean LoginSuccefully=false;

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }


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
                        String queryUser= "select * from dbo.T2_UTILISATEURS where NOM_UTILISATEUR= '" + currentUsername.toString()+"'  ";
                        /*String query ="SELECT COUNT(*), \" + Constantes.T2_UTILISATEURS_NOM_UTILISATEUR + \", \" + Constantes.T2_UTILISATEURS_MOT_PASSE + \" \" +\n" +
                                "FROM \" + Constantes.T2_UTILISATEURS" +
                               "WHERE \" + Constantes.T2_UTILISATEURS_NOM_UTILISATEUR + \" = \"+ textBoxNomUtilisateur.Text.Replace(\"'\",\"''\") +" +
                                "GROUP BY \" + Constantes.T2_UTILISATEURS_NOM_UTILISATEUR + \", \" + Constantes.T2_UTILISATEURS_MOT_PASSE";*/
                        //String query = "select * from login where user_name= '" + currentUsername.toString() + "' and pass_word = '"+ currentPassword.toString() +"'  ";
                        Statement stmt = con.createStatement();
                        ResultSet rs1 = stmt.executeQuery(queryUser);

                        if(rs1.next())
                        {
                            if(checkPassword(stmt)){
                                informUser = "Login successful";
                                LoginSuccefully=true;
                                con.close();
                            }
                            else
                            {
                                informUser = "Login failed, wrong password";
                                LoginSuccefully = false;
                            }
                        }
                        else
                        {
                            informUser = "Login failed, wrong login";
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
            progressBar.setVisibility(View.GONE);
            Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
            if(LoginSuccefully)
            {
                Toast.makeText(LoginActivity.this , "Login Successfull" , Toast.LENGTH_LONG).show();
                startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                //finish();
            }
        }
    }

    public boolean checkPassword(Statement stmt){
        String querygetPassword = "select MOT_PASSE from dbo.T2_UTILISATEURS where NOM_UTILISATEUR= '" + currentUsername.toString()+"'  ";
        String currentPasswordHash = null;
        String passEnter,passBdd;
        boolean StringAreEqual=false;
        try {
            passEnter = hashPassword(currentPassword);
            ResultSet rs2 = stmt.executeQuery(querygetPassword);
            //String pass2S = rs2.toString();
            //passBdd = rs2.getBytes("mdp");

            StringBuilder builder = new StringBuilder();
            int columnCount = rs2.getMetaData().getColumnCount();
            while (rs2.next()) {
                for (int i = 0; i < columnCount;) {
                    builder.append(rs2.getString(i + 1));
                    if (++i < columnCount) builder.append("");
                }
            }
            passBdd = builder.toString();
            StringAreEqual =passEnter.equals(passBdd);
            //return(MessageDigest.isEqual (passEnter, passBdd));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return(StringAreEqual);
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
}
