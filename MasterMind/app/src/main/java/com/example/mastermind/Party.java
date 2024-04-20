package com.example.myapplication17;

public class Party {
    private int id;
    private String playerEmail;
    private int secretCodeId;
    private String secretCode;
    private int numColors;
    private String result;
    private int numAttempts;
    // Constructeur
    public Party() { }
    // Getters et setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPlayerEmail() {
        return playerEmail;
    }
    public void setPlayerEmail(String playerEmail) {
        this.playerEmail = playerEmail;
    }
    public int getSecretCodeId() {
        return secretCodeId;
    }
    public void setSecretCodeId(int secretCodeId) {
        this.secretCodeId = secretCodeId;
    }
    public String getSecretCode() {
        return secretCode;
    }
    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }
    public int getNumColors() {
        return numColors;
    }
    public void setNumColors(int numColors) {
        this.numColors = numColors;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public int getNumAttempts() {
        return numAttempts;
    }
    public void setNumAttempts(int numAttempts) {
        this.numAttempts = numAttempts;
    }
}
