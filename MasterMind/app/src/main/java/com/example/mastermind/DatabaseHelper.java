package com.example.myapplication17;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mastermind.db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    private static final String TABLE_PARTIES = "partie";

    // Columns
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PLAYER_EMAIL = "email";
    private static final String COLUMN_SECRET_CODE_ID = "code_id";
    private static final String COLUMN_SECRET_CODE = "code";
    private static final String COLUMN_NUM_COLORS = "num_colors";
    private static final String COLUMN_RESULT = "result";
    private static final String COLUMN_NUM_ATTEMPTS = "num_attempts";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table query
        String createTableQuery = "CREATE TABLE " + TABLE_PARTIES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_PLAYER_EMAIL + " TEXT," +
                COLUMN_SECRET_CODE_ID + " INTEGER," +
                COLUMN_SECRET_CODE + " TEXT," +
                COLUMN_NUM_COLORS + " INTEGER," +
                COLUMN_RESULT + " TEXT," +
                COLUMN_NUM_ATTEMPTS + " INTEGER" + ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTIES);
        // Create tables again
        onCreate(db);
    }

    // Add a new party to the database
    public void addParty(Party party) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PLAYER_EMAIL, party.getPlayerEmail());
        values.put(COLUMN_SECRET_CODE_ID, party.getSecretCodeId());
        values.put(COLUMN_SECRET_CODE, party.getSecretCode());
        values.put(COLUMN_NUM_COLORS, party.getNumColors());
        values.put(COLUMN_RESULT, party.getResult());
        values.put(COLUMN_NUM_ATTEMPTS, party.getNumAttempts());
        // Inserting Row
        db.insert(TABLE_PARTIES, null, values);
        db.close();
    }

    // Get all parties from the database
    public List<Party> getAllParties() {
        List<Party> partyList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PARTIES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Loop through all rows and add to list
        if (cursor.moveToFirst()) {
            do {
                Party party = new Party();
                party.setId(cursor.getInt(0));
                party.setPlayerEmail(cursor.getString(1));
                party.setSecretCodeId(cursor.getInt(2));
                party.setSecretCode(cursor.getString(3));
                party.setNumColors(cursor.getInt(4));
                party.setResult(cursor.getString(5));
                party.setNumAttempts(cursor.getInt(6));
                // Adding party to list
                partyList.add(party);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return partyList;
    }
}