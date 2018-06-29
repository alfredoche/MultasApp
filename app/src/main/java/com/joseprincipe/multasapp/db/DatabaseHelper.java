package com.joseprincipe.multasapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.joseprincipe.multasapp.models.Multa;
import com.joseprincipe.multasapp.tables.MultaTable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "midatabase.db";
    private static DatabaseHelper instancia;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context){
        if(instancia == null){
            instancia = new DatabaseHelper(context);
        }
        return instancia;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + MultaTable.TABLE_NAME + " (" +
                MultaTable.C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MultaTable.C_PLACA + " TEXT," +
                MultaTable.C_TIPO + " TEXT," +
                MultaTable.C_CAUSAMULTA + " TEXT," +
                MultaTable.C_DNI + " TEXT," +
                MultaTable.C_MONTO + " TEXT," +
                MultaTable.C_NOMBRE + " TEXT" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                          int versionActual,
                          int nuevaVersion) {
        sqLiteDatabase.execSQL("DROP TABLE "+MultaTable.TABLE_NAME);

        this.onCreate(sqLiteDatabase);

    }
}
