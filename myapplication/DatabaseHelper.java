package com.example.myapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "plant_care.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PLANTS = "plants";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PLANT_NAME = "plant_name";
    public static final String COLUMN_REVIEW = "review";
    public static final String COLUMN_TIPS = "tips";

    private static final String CREATE_TABLE_PLANTS = "CREATE TABLE " + TABLE_PLANTS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_PLANT_NAME + " TEXT,"
            + COLUMN_REVIEW + " TEXT,"
            + COLUMN_TIPS + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PLANTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANTS);
        onCreate(db);
    }

    public boolean insertPlant(String plantName, String review, String tips) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PLANT_NAME, plantName);
        contentValues.put(COLUMN_REVIEW, review);
        contentValues.put(COLUMN_TIPS, tips);
        long id = db.insert(TABLE_PLANTS, null, contentValues);
        db.close();
        return id!=-1;
    }

    public int updatePlant(long id, String plantName, String review, String tips) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PLANT_NAME, plantName);
        contentValues.put(COLUMN_REVIEW, review);
        contentValues.put(COLUMN_TIPS, tips);
        return db.update(TABLE_PLANTS, contentValues, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deletePlant(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLANTS, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public Cursor getAllPlants() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_PLANTS, null, null, null, null, null, null);
    }
}
