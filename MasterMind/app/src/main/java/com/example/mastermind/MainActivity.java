package com.example.mastermind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnJouer;
    private Button btnConfig;
    private Button btnHistorique;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJouer = findViewById(R.id.btnJouer);
        btnConfig = findViewById(R.id.btnConfig);
        btnHistorique = findViewById(R.id.btnHistorique);

        btnJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ouvre l'activité JeuActivity
                Intent intent = new Intent(MainActivity.this, JeuActivity.class);
                startActivity(intent);
            }
        });

        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ouvre l'activité Reglage
                Intent intent = new Intent(MainActivity.this, ReglageActivity.class);
                startActivity(intent);
            }
        });

        btnHistorique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ouvre l'activité Parametre
                Intent intent = new Intent(MainActivity.this, Parametre.class);
                startActivity(intent);
            }
        });
    }
}
