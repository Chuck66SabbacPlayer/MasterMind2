package com.example.mastermind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ReglageActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

     SeekBar lengthBar;
     TextView lengthView;

     SeekBar colorBar;
     TextView colorView;

     SeekBar triesBar;
     TextView triesView;

     Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reglage);

        //recupere les views et les mets dans les valeurs correspondante
        lengthBar = findViewById(R.id.lengthBar);
        lengthView = findViewById(R.id.lengthView);

        colorBar = findViewById(R.id.colorBar);
        colorView = findViewById(R.id.colorView);

        triesBar = findViewById(R.id.triesBar);
        triesView = findViewById(R.id.triesView);

        backButton = findViewById(R.id.backReglageButton);

        //initialise la valeur des bar et des textView
        lengthBar.setProgress(Parametre.LENGTH - 2);
        lengthView.setText(Parametre.LENGTH+"");

        colorBar.setProgress(Parametre.COLOR_COUNT - 2);
        colorView.setText(Parametre.COLOR_COUNT +"");

        triesBar.setProgress(Parametre.TRIES - 8);
        triesView.setText(Parametre.TRIES+"");

        //set le listener pour les seekBar
        lengthBar.setOnSeekBarChangeListener(this);
        colorBar.setOnSeekBarChangeListener(this);
        triesBar.setOnSeekBarChangeListener(this);

        //set le listener pour le bouton de retour
        //va ramener l'user au main menu avec les parametre
        backButton.setOnClickListener(this);

    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(seekBar.getId() == R.id.lengthBar) {
            lengthView.setText(2+progress+"");
        }
        else if(seekBar.getId() == R.id.colorBar) {
            colorView.setText(2+progress+"");
        }
        else if(seekBar.getId() == R.id.triesBar) {
            triesView.setText(8+progress+"");
        }
    }

    @Override
    public void onClick(View v) {
        Intent backIntent = new Intent();
        Parametre.LENGTH = lengthBar.getProgress()+2;
        Parametre.COLOR_COUNT = colorBar.getProgress()+2;
        Parametre.TRIES = triesBar.getProgress()+8;
        finish();
    }



    //implementation non utiliser
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

}