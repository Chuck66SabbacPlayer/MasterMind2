package com.example.mastermind.modele.entite;

public class Solution {

    private int id;
    private String[] colors;
    private int nbColors;

    public Solution(int id, String[] colors, int nbColors) {
        this.id = id;
        this.colors = colors;
        this.nbColors = nbColors;
    }

    public int getId() {
        return id;
    }

    public String[] getColors() {
        return colors;
    }

    public int getNbColors() {
        return nbColors;
    }
}
