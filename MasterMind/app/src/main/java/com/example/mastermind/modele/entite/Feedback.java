package com.example.mastermind.modele.entite;

public class Feedback {
    private int validPostion;
    private int wrongPosition;
    public Feedback(int validPosition, int wrongPosition) {
        this.validPostion = validPosition;
        this.wrongPosition = wrongPosition;
    }

    public int getValidPostion() {
        return validPostion;
    }

    public int getWrongPosition() {
        return wrongPosition;
    }
}
