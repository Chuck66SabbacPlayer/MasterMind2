package com.example.myapplication17;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
public class HistoriqueActivity extends AppCompatActivity {
    private ListView listViewParties;
    private DatabaseHelper databaseHelper;
    private Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);
        listViewParties = findViewById(R.id.listViewParties);
        databaseHelper = new DatabaseHelper(this);
        // Récupérer les parties depuis la base de données
        List<Party> partyList = databaseHelper.getAllParties();
        // Créer un adaptateur pour afficher les parties dans la ListView
        ArrayAdapter<Party> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, partyList);
        listViewParties.setAdapter(adapter);
        // Bouton pour revenir à MainActivity
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(HistoriqueActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Termine cette activité pour empêcher de revenir en arrière
        });
    }
}
