package com.example.mastermind.modele.entite;

import android.util.Log;

import com.example.mastermind.Parametre;
import com.example.mastermind.modele.dao.Dao;

public class Mastermind {
    //la solution du mastermind
    private Code solution;

    //le try auquel on est rendu
    private int triesNb = 0;

    //les reponses de chaque essai
    private Feedback[] feedbacks;

    //ce qui est afficher pour l'utilisateur
    private Code[] partie;
    private String[] colors;


    public Mastermind() {
        iniPartie();
        Dao dao = Dao.getInstance();
        Log.d("color",""+Parametre.COLOR_COUNT);
        Log.d("length",""+Parametre.LENGTH);
        solution = dao.getRandomSolution(Parametre.COLOR_COUNT, Parametre.LENGTH);
        colors = Dao.getInstance().getColor();
        feedbacks = new Feedback[Parametre.TRIES];

    }


    public Code getSolution() {
        return solution;
    }

    public void setSolution(Code solution) {
        this.solution = solution;
    }

    public int getTriesNb() {
        return triesNb;
    }

    public void setTriesNb(int triesNb) {
        this.triesNb = triesNb;
    }

    public Feedback[] getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Feedback[] feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Code[] getPartie() {
        return partie;
    }

    public void setPartie(Code[] partie) {
        this.partie = partie;
    }

    public String[] getColors() {
        return colors;
    }

    private void iniPartie() {
        partie = new Code[Parametre.TRIES];
        for(int i = 0; i < partie.length; i++) {
            partie[i] = new Code();
        }
    }
}
