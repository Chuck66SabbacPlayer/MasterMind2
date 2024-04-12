package com.example.mastermind;

import android.os.Bundle;

import androidx.gridlayout.widget.GridLayout;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class JeuActivity extends AppCompatActivity implements View.OnClickListener {

    GridLayout grid;
    GridLayout palette;
    private int selectedIndex;
    private int tries = 0;

    //button
    Button confirmButton;
    Button backButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jeu);

        //recupere les views et les mettre dans leurs variables correspondante
        grid = findViewById(R.id.gameGrid);
        palette = findViewById(R.id.paletteGrid);


        confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(this);
        backButton = findViewById(R.id.backGameButton);
        backButton.setOnClickListener(this);

        setUpGrid();
        setUpPalette();




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
        //does not work as wanted
        //TODO: clean up code
        //plusieurs lignes du code sont non necessaire
        for(int i = 0; i < Parametre.COLOR_COUNT; i++) {
            ImageView image = new ImageView(this);
            image.setImageDrawable(getDrawable(R.drawable.rounded_square));
            //GradientDrawable drawable = (GradientDrawable) image.getBackground();

            //drawable.setColor(getColor(Parametre.colors[i]));
            //image.setBackgroundColor(Parametre.colors[i]);

            image.setBackgroundColor(getColor(Parametre.colors[i]));
            GridLayout.LayoutParams param = new GridLayout.LayoutParams();
            param.rightMargin = 20;
            param.width = 225;
            param.height = 225;
            image.setLayoutParams(param);
            palette.addView(image);

            image.setTag(i);
            image.setOnClickListener(this);

        }
    }

    private void setUpGrid() {
        //regle bien les grid selon les parametre choisi
        grid.setColumnCount(Parametre.LENGTH);
        grid.setRowCount(Parametre.TRIES);
        addRow();

    }

    /**
     * rajoute le prochain try
     */
    private void addRow() {
        for(int i = 0; i < Parametre.LENGTH; i++) {
            ImageView image = new ImageView(this);
            image.setImageDrawable(getDrawable(R.drawable.circle));
            GridLayout.LayoutParams param = new GridLayout.LayoutParams();
            param.rightMargin = 20;
            param.width = 125;
            param.height = 125;
            image.setLayoutParams(param);
            image.setBackgroundColor(getColor(R.color.gray));
            image.setTag("c"+(i+tries*Parametre.LENGTH));
            image.setOnClickListener(this);
            grid.addView(image);

        }
        selectedIndex = 0;
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
            tries++;
            addRow();
        }
        else {
            if(v.getTag().toString().contains("c")) {
                int tmpIndex = Integer.parseInt(((String) v.getTag()).substring(1));
                Log.d("click", "tmpIndex: "+tmpIndex);
                if(tmpIndex >= tries*Parametre.LENGTH)
                    selectedIndex = tmpIndex - tries*Parametre.LENGTH;
            }
            else {
                Log.d("click", "selectedIndex: "+selectedIndex);
                grid.getChildAt(tries * Parametre.LENGTH + selectedIndex).setBackgroundColor(getColor(Parametre.colors[(int) v.getTag()]));
                if (selectedIndex < Parametre.LENGTH - 1) {
                    selectedIndex++;
                }
            }
        }


    }
}