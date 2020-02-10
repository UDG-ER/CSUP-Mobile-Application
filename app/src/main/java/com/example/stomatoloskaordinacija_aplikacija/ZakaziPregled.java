package com.example.stomatoloskaordinacija_aplikacija;



import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ZakaziPregled extends AppCompatActivity {

    private Button dugme;
    private EditText ime,prezime,datum,broj_tel,adresa,opis,datum_pregleda;
    private CheckBox muski,zenski;
    String pol = "";

    private static final String DB_URL = "jdbc:mysql://192.168.43.138/baza";
    private static final String USER = "bane";
    private static final String PASS = "bane";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakazi_pregled);


        dugme = findViewById(R.id.dugme);

        muski = findViewById(R.id.muski);
        zenski = findViewById(R.id.zenski);

        ime = findViewById(R.id.ime);
        prezime = findViewById(R.id.prezime);
        datum = findViewById(R.id.datum);
        broj_tel = findViewById(R.id.broj_tel);
        adresa = findViewById(R.id.adresa);
        opis = findViewById(R.id.opis);
        datum_pregleda = findViewById(R.id.datum_pregleda);


        muski.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zenski.setChecked(false);
            }
        });

        zenski.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                muski.setChecked(false);

            }
        });

        dugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (muski.isChecked()  || zenski.isChecked()) {

                    if (muski.isChecked()) {

                        pol = "Muski";

                    }

                    else {
                        pol = "Zenski";
                    }

                }

                else {
                    return;
                }

                Send objSend = new Send();
                objSend.execute();


            }
        });
    }

    private class Send extends AsyncTask<String, String,String>{
        String msg = "";

        String ime2 = ime.getText().toString();
        String prezime2 = prezime.getText().toString();
        String datum2 = datum.getText().toString();
        String broj_tel2 = broj_tel.getText().toString();
        String adresa2 = adresa.getText().toString();
        String opis2 = opis.getText().toString();
        String datum_pregleda2 = datum_pregleda.getText().toString();





        @Override
        protected String doInBackground(String... strings) {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                if(conn == null){
                    msg = "Greška u konekciji";
                }
                else{

                    String query = "INSERT INTO zakazivanje (ime, prezime, pol, datum, broj_tel, adresa, opis_pregleda, datum_pregleda) VALUES('"+ime2+"','"+prezime2+"','"+pol+"','"+datum2+"','"+broj_tel2+"','"+adresa2+"','"+opis2+"','"+datum_pregleda2+"')";
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(query);
                    msg = "Uspješno ste zakazali pregled!";


                }
                conn.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return msg;
        }

        protected void onPostExecute(String msg){
            Toast.makeText(ZakaziPregled.this, msg, Toast.LENGTH_SHORT).show();

            ime.setText("");
            prezime.setText("");
            datum.setText("");
            broj_tel.setText("");
            adresa.setText("");
            opis.setText("");
            datum_pregleda.setText("");
            muski.setChecked(false);
            zenski.setChecked(false);


        }




    }


}

