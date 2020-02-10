package com.example.stomatoloskaordinacija_aplikacija;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class doktor_login extends AppCompatActivity {

    private Button login_btn,login_pacijent;
    private EditText username,password;
    private static final String DB_URL = "jdbc:mysql://192.168.43.138/baza";
    private static final String USER = "bane";
    private static final String PASS = "bane";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doktor_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);


       login_pacijent = findViewById(R.id.login_pacijent);

        login_pacijent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(doktor_login.this,MainActivity.class);

                startActivity(intent);

            }
        });

        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                new doktor_login.MyTask().execute();
            }
        });

    }

    private class MyTask extends AsyncTask<Void,Void,Void> {

        private int i = 0;
        int id = 0;
        String ime = "";
        String datum_pregleda = "";
        String opis_pregleda = "";
        int brojac = 0;

        @Override

        protected Void doInBackground(Void... voids) {



            try{

                Class.forName("com.mysql.jdbc.Driver");

                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);



                Statement st = conn.createStatement();

                String sql = "Select * from zubar";



                final ResultSet rs = st.executeQuery(sql);







                //Store data to array

                while(rs.next()){



                    String usernameBaza = rs.getString(2);
                    String passwordBaza = rs.getString(3);

                    if(usernameBaza.equals(username.getText().toString()) && passwordBaza.equals(password.getText().toString())){
                        brojac = 1;

                        Intent intent = new Intent(doktor_login.this,doktor_meni.class);
                        intent.putExtra("username", usernameBaza);
                        startActivity(intent);

                    }



                }








                conn.close();



            }catch (Exception e){

                e.printStackTrace();

            }

            return null;

        }

        @Override

        protected void onPostExecute(Void Result){
            if(brojac==1){
                Toast.makeText(doktor_login.this, "Dobrodošli!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(doktor_login.this, "Pogrešan unos! Pokusajte ponovo...", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(Result);

        }

    }

}
