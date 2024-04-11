package com.example.mastermind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

        btnJouer.setOnClickListener(this);

        btnConfig.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnJouer) {
            Intent intent = new Intent(MainActivity.this, JeuActivity.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.btnConfig) {
            Intent intent = new Intent(MainActivity.this, ReglageActivity.class);
            startActivity(intent);
        }
    }
}
