package com.example.stomatoloskaordinacija_aplikacija;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class SviPregledi extends AppCompatActivity {

     String[][] spaceProbes;

    private static final String DB_URL = "jdbc:mysql://192.168.43.138/baza";
    private static final String USER = "bane";
    private static final String PASS = "bane";
    private String username;

    static String[] spaceProbeHeaders = {"ID","Ime i Prezime","Datum Pregleda","Opis pregleda"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svi_pregledi);

        Bundle extras = getIntent().getExtras();
         username = extras.getString("username");


        new SviPregledi.MyTask().execute();




    }

    private class MyTask extends AsyncTask<Void,Void,Void> {

        private int i = 0;
        int id = 0;
        String ime = "";
        String imeTest = "";
        String datum_pregleda = "";
        String opis_pregleda = "";

        @Override

        protected Void doInBackground(Void... voids) {



            try{

                Class.forName("com.mysql.jdbc.Driver");

                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);



                Statement st = conn.createStatement();

                String sql = "Select * from zakazivanje where ime = '" + username + "'" ;



                final ResultSet rs = st.executeQuery(sql);



                int a = 0;


                rs.last();
                a = rs.getRow();
                rs.beforeFirst();



spaceProbes = new String[a][];
                //Store data to array
int brojac = 0;
                while(rs.next()){


                    id = rs.getInt(1);
                    ime = rs.getString(2) + " " + rs.getString(3);
                    imeTest = rs.getString(2);
                    datum_pregleda = rs.getString(9);
                    opis_pregleda = rs.getString(8);


                    String id2 = Integer.toString(id);


    spaceProbes[brojac] = new String[]{id2, ime, datum_pregleda, opis_pregleda};
    brojac = brojac + 1;






                }





                conn.close();



            }catch (Exception e){

                e.printStackTrace();

            }

            return null;

        }

        @Override

        protected void onPostExecute(Void Result){
            final TableView<String[]> tableView = (TableView<String[]>) findViewById(R.id.tableView);






            tableView.setHeaderBackgroundColor(Color.parseColor("#00e5ff"));
            tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(SviPregledi.this,spaceProbeHeaders));
            tableView.setColumnCount(4);

            tableView.setDataAdapter(new SimpleTableDataAdapter(SviPregledi.this,spaceProbes));

            super.onPostExecute(Result);

        }

    }





}
