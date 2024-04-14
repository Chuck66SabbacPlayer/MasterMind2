package com.example.mastermind;

import android.graphics.Color;
import android.os.Bundle;

import androidx.gridlayout.widget.GridLayout;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mastermind.modele.dao.Dao;
import com.example.mastermind.modele.entite.Feedback;
import com.example.mastermind.presenteur.Partie;

public class JeuActivity extends AppCompatActivity implements View.OnClickListener {

    GridLayout grid;
    GridLayout palette;

    private Partie partie;
    //button
    Button confirmButton;
    Button backButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jeu);

        Dao.getInstance().getAll();
        Dao.getInstance().getColor();
        partie = new Partie();

        //recupere les views et les mettre dans leurs variables correspondante
        grid = findViewById(R.id.gameGrid);
        palette = findViewById(R.id.paletteGrid);


        confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(this);
        backButton = findViewById(R.id.backGameButton);
        backButton.setOnClickListener(this);

        setUpGrid();
        setUpPalette();

        //cache l'heure en haut de l'ecran et le navigation UI en bas de l'ecran
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        for(int i = 0; i < partie.getSolution().getCode().length; i++) {
            Log.d("solution: ", partie.getSolution().getColor(i));
        }
    }

    private void setUpPalette() {
        if(Parametre.COLOR_COUNT <= 4) {
            palette.setColumnCount(Parametre.COLOR_COUNT);
            palette.setRowCount(1);
        }
        else {
            palette.setColumnCount(4);
            palette.setRowCount(2);
        }

        for(int i = 0; i < Parametre.COLOR_COUNT; i++) {
            ImageView image = new ImageView(this);
            image.setImageDrawable(getDrawable(R.drawable.rounded_square));
            Log.d("colors", partie.getColor(i));
            image.setBackgroundColor(Color.parseColor("#"+partie.getColor(i)));
            GridLayout.LayoutParams param = new GridLayout.LayoutParams();
            param.rightMargin = 20;
            param.width = 175;
            param.height = 175;
            image.setLayoutParams(param);
            palette.addView(image);

            image.setTag(i);
            image.setOnClickListener(this);
        }
    }

    private void setUpGrid() {
        //regle bien les grid selon les parametre choisi
        grid.setColumnCount(Parametre.LENGTH + 2);
        grid.setRowCount(Parametre.TRIES);
        addRow();

    }

    /**
     * rajoute le prochain try
     */
    private void addRow() {
        TextView info1 = new TextView(this);
        TextView info2 = new TextView(this);
        grid.addView(info1);

        for(int i = 0; i < Parametre.LENGTH; i++) {
            ImageView image = new ImageView(this);
            image.setImageDrawable(getDrawable(R.drawable.circle));
            GridLayout.LayoutParams param = new GridLayout.LayoutParams();
            param.rightMargin = 20;
            param.width = 125;
            param.height = 125;
            image.setLayoutParams(param);
            image.setBackgroundColor(Color.parseColor(partie.getCurrentTry().getColor(i)));
            image.setTag("c"+(i+partie.getTry()*Parametre.LENGTH));
            image.setOnClickListener(this);
            grid.addView(image);

        }
        grid.addView(info2);
        partie.setSelected(1);
    }

    public void updateInfo(View v) {
        Feedback feedback = partie.checkCode();
        TextView info1 = (TextView) grid.getChildAt(partie.getTry() * (Parametre.LENGTH + 2));
        Log.d("info1", info1.toString());
        TextView info2 = (TextView) grid.getChildAt(partie.getTry() * (Parametre.LENGTH + 2) + Parametre.LENGTH + 1);
        Log.d("info2", info2.toString());
        info1.setText(""+feedback.getValidPostion());
        info2.setText(""+feedback.getWrongPosition());
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.backGameButton) {
            finish();
        }
        else if(v.getId() == R.id.abandonButton) {
            //a implementer quand l'historique sera fini
        }
        else if (v.getId() == R.id.newButton) {
            //a implementer
        }
        else if(v.getId() == R.id.confirmButton) {
            updateInfo(v);
            partie.incrementTry();
            addRow();
        }
        else {
            if(v.getTag().toString().contains("c")) {
                int tmpIndex = 1 + Integer.parseInt(((String) v.getTag()).substring(1));
                Log.d("click", "tmpIndex: "+tmpIndex);
                if(tmpIndex >= partie.getTry()*Parametre.LENGTH)
                    partie.setSelected(tmpIndex - partie.getTry()*Parametre.LENGTH);
            }
            else {
                Log.d("click", "selectedIndex: "+partie.getSelected());
                grid.getChildAt(partie.getTry() * (Parametre.LENGTH +2) + partie.getSelected()).setBackgroundColor(Color.parseColor("#"+partie.getColor(((int) v.getTag()))));
                partie.changeColor(partie.getSelected()-1, partie.getColor((int) v.getTag()));
                if (partie.getSelected() < Parametre.LENGTH) {
                    partie.setSelected(partie.getSelected()+1);
                }
            }
        }


    }
}