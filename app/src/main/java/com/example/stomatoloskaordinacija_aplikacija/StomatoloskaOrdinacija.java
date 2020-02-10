package com.example.stomatoloskaordinacija_aplikacija;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StomatoloskaOrdinacija extends AppCompatActivity {

    private Button id_zakazi_pregled, id_pregledi, id_filter, id_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stomatoloska_ordinacija);

        Bundle extras = getIntent().getExtras();
        final String username = extras.getString("username");



        id_zakazi_pregled = findViewById(R.id.id_zakazi_pregled);
        id_pregledi = findViewById(R.id.id_pregledi);
        id_filter = findViewById(R.id.id_filter);
        id_logout = findViewById(R.id.id_logout);


        id_zakazi_pregled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StomatoloskaOrdinacija.this,ZakaziPregled.class);
                StomatoloskaOrdinacija.this.startActivity(intent);
            }
        });

        id_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(StomatoloskaOrdinacija.this,MainActivity.class);
                StomatoloskaOrdinacija.this.startActivity(inten);
            }
        });

        id_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(StomatoloskaOrdinacija.this,Zaposleni.class);
                StomatoloskaOrdinacija.this.startActivity(inte);
            }
        });


        id_pregledi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(StomatoloskaOrdinacija.this,SviPregledi.class);
                inte.putExtra("username", username);
                StomatoloskaOrdinacija.this.startActivity(inte);
            }
        });








    }
}
