package com.example.mastermind.modele.entite;

import android.util.Log;

import com.example.mastermind.Parametre;

import java.util.Arrays;

public class Code {
    private int nbCouleurs;
    private String[] code;

    private int id;


    public Code() {
        code = new String[Parametre.LENGTH];
        //initialise les couleurs a gris
        for(int i = 0; i < code.length; i++) {
            code[i] = "#ff999999";
        }
        nbCouleurs = Parametre.LENGTH;
        id = -1;
    }

    public Code(int id, String[] code, int nbCouleurs) {
        this.id = id;
        this.code = code;
        this.nbCouleurs = nbCouleurs;
    }

    public String[] getCode() {
        return code;
    }

    public String getColor(int index) {
        return code[index];
    }

    public void setColor(int index, String color) {
        code[index] = color;
    }

    public Feedback compareCode(Code cCode) {
        int validPosition = 0;
        int wrongPosition = 0;

        //besoin de faire des copies pour eviter de modifier les valeurs original
        String[] localCode = Arrays.copyOf(this.code, this.code.length);
        String[] compareCode = Arrays.copyOf(cCode.getCode(), cCode.getCode().length);

        //check pour la bonne position
        for(int i = 0; i < compareCode.length; i++) {
            Log.d("debug", ""+localCode[i]);
            Log.d("debug", ""+compareCode[i]);

            if(localCode[i] != null && compareCode[i] != null && localCode[i].equals(compareCode[i])) {
                localCode[i] = null;
                compareCode[i] = null;
                validPosition++;
            }
        }
        for(int i = 0; i < localCode.length; i++) {
            boolean done = false;
            for (int j = 0; j < localCode.length && !done; j++) {
                if (localCode[j] != null && compareCode[i] != null && localCode[j].equals(compareCode[i])) {
                    localCode[j] = null;
                    compareCode[i] = null;
                    wrongPosition++;
                    done = true;
                }
            }
        }
        return new Feedback(validPosition, wrongPosition);
    }

    /**
     * Retourne -1 si id n'est pas definie (dans le cas ou Code est utiliser pour representer l'input du joueur)
     * @return -1 si id est non definie
     *          sinon id correspondant au code secret
     */
    public int getId() {
        return id;
    }

    public int getNbCouleurs() {
        return nbCouleurs;
    }
}
