package com.example.mastermind.modele.dao;

import android.util.Log;

import com.example.mastermind.modele.entite.Code;
import com.example.mastermind.modele.entite.Solution;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class Dao {
    private Code[] codes;
    private String[] colors;

    private static Dao instance = null;


    public static Dao getInstance() {
    if(instance == null)
        instance = new Dao();
    return instance;
    }

    private Dao() {
    }

    public static Code[] getList(int nbColor) {
        return new JsonService().getPattern(nbColor);
    }

    public Code[] getAll() {
        JSONArray json = new JsonService().getJson();
        codes = new Code[json.length()];
        convertJsonToCode(json);
        return codes;
    }
    public Code getRandomSolution (int nbColor, int length) {
        ArrayList<Code> validCodes = new ArrayList<>();
        for(Code code : codes) {
            if(code.getNbCouleurs() == nbColor && code.getCode().length == length) {
                //rajoute a la liste si le nombre de couleur est le meme
                validCodes.add(code);
            }
        }
        Random r = new Random();
        return validCodes.get(r.nextInt(validCodes.size()-1));
    }

    private void convertJsonToCode(JSONArray json) {
        for(int i = 0; i < json.length(); i++) {
            try {
                JSONObject obj = json.getJSONObject(i);
                JSONArray arr = obj.getJSONArray("code");
                //get l'array code dans le json et le transforme en String[]
                String[] colors = new String[arr.length()];
                for(int j = 0; j < arr.length(); j++) {
                    colors[j] = arr.getString(j);
                }
                codes[i] = new Code(obj.getInt("id"), colors, obj.getInt("nbCouleurs"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String[] getColor() {
        JSONArray json = new JsonService().getColors();
        colors = new String[json.length()];
        for(int i = 0; i < json.length(); i++){
            try {
                colors[i] = json.getString(i);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return colors;
    }
}
