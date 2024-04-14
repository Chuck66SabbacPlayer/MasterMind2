package com.example.mastermind.modele.dao;

import android.util.Log;

import com.example.mastermind.modele.entite.Code;
import com.example.mastermind.modele.entite.Solution;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class JsonService {
    private static Code[] list;
    private static JSONArray colors;
    private static final String ENTRY_POINT = "http://10.0.2.2:3000";
    private static JSONArray json;

    public Code[] getPattern(int nbColor) {
        new Thread() {

            final String ENTRY_POINT = "http://10.0.2.2:3000";
            OkHttpClient client = new OkHttpClient();

            public void run() {
                ObjectMapper objectMapper = new ObjectMapper();
                Request request = new Request.Builder()
                        .url(ENTRY_POINT + "/codesSecrets?nbCouleurs=" + nbColor)
                        .get()
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    ResponseBody responseBody = response.body();
                    String jsonS = responseBody.string();
                    list = objectMapper.readValue(jsonS, Code[].class);


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
        return list;
    }

    public JSONArray getAll() {
        new Thread() {
            OkHttpClient client = new OkHttpClient();

            public void run() {
                ObjectMapper objectMapper = new ObjectMapper();
                Request request = new Request.Builder()
                        .url(ENTRY_POINT + "/codesSecrets")
                        .get()
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    ResponseBody responseBody = response.body();
                    String jsonS = responseBody.string();
                    //je n'arrivais pas a faire fonctionner ObjectMapper donc je vais utiliser le JSONArray
                    json = new JSONArray(jsonS);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();

        return json;
    }

    public static JSONArray getJson() {
        return json;
    }

    public JSONArray getColors() {
        new Thread() {
            OkHttpClient client = new OkHttpClient();

            public void run() {
                ObjectMapper objectMapper = new ObjectMapper();
                Request request = new Request.Builder()
                        .url(ENTRY_POINT + "/couleursDisponibles")
                        .get()
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    ResponseBody responseBody = response.body();
                    String jsonS = responseBody.string();
                    JSONObject jsonO = new JSONObject(jsonS);
                    colors = jsonO.getJSONArray("liste");

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();

        return colors;
    }
}
