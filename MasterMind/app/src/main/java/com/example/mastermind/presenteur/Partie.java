package com.example.mastermind.presenteur;

import com.example.mastermind.Parametre;
import com.example.mastermind.modele.entite.Code;
import com.example.mastermind.modele.entite.Feedback;
import com.example.mastermind.modele.entite.Mastermind;

public class Partie {

    private Code[] display;
    private Mastermind game;
    private String[] colors;

    private int selected = 0;
    public Partie() {
        //creer la solution
        game = new Mastermind();
        //garde les couleurs dans cette classe pour easier access
        colors = game.getColors();
    }

    /**
     * @return le code de l'essai present
     */
    public Code getCurrentTry() {
        return game.getPartie()[game.getTriesNb()];
    }

    /**
     * va modifier au complet le code de l'essai presenty
     * @param code est le nouveu code pur l'essai
     */
    public void setCurrentTry(Code code) {
        game.getPartie()[game.getTriesNb()] = code;
    }

    /**
     * va changer la couleur du current try a l'index donner
     * @param index est l'index dont on veut modifier la couleur
     * @param color est la couleur qu'on veut mettre a l'index donner
     */
    public void changeColor(int index, String color) {
        game.getPartie()[game.getTriesNb()].setColor(index, color);
    }

    public int getTry() {
        return game.getTriesNb();
    }

    public String getColor(int index) {
        return game.getColors()[index];
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public void setTry(int tryNb) {
        game.setTriesNb(tryNb);
    }

    public int incrementTry() {
        game.setTriesNb(game.getTriesNb()+1);
        return game.getTriesNb();
    }

    public Feedback checkCode() {
        return game.getSolution().compareCode(game.getPartie()[game.getTriesNb()]);
    }

    public Code getSolution() {
        return game.getSolution();
    }
}
