package com.joseprincipe.multasapp.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.joseprincipe.multasapp.db.DatabaseHelper;
import com.joseprincipe.multasapp.models.Multa;
import com.joseprincipe.multasapp.tables.MultaTable;

import java.util.ArrayList;
import java.util.List;

public class MultaDAO {

    final private DatabaseHelper dbHelper;
    final private String[] columns = {MultaTable.C_ID,
            MultaTable.C_PLACA,
            MultaTable.C_CAUSAMULTA,
            MultaTable.C_TIPO,
            MultaTable.C_DNI,
            MultaTable.C_MONTO,
            MultaTable.C_NOMBRE
    };

    public MultaDAO(Context context) {
        dbHelper = DatabaseHelper.getInstance(context);
    }

    public void save(Multa multa) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MultaTable.C_PLACA, multa.getPlaca());
        values.put(MultaTable.C_CAUSAMULTA, multa.getCausa());
        values.put(MultaTable.C_TIPO, multa.getTipo());
        values.put(MultaTable.C_DNI, multa.getDni());
        values.put(MultaTable.C_MONTO, multa.getMonto());

        if(multa.getId()== -1) {
            db.insert(MultaTable.TABLE_NAME, null, values);
        }else{
            // actualizar
            db.update(MultaTable.TABLE_NAME, values, "id=?",
                    new String[]{String.valueOf(multa.getId())});
        }
        db.close();
    }

    public List<Multa> all() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(MultaTable.TABLE_NAME, columns, null, null, null, null, MultaTable.C_ID + " desc");
        List<Multa> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(MultaTable.C_ID));
                String placa = cursor.getString(cursor.getColumnIndex(MultaTable.C_PLACA));
                String causa = cursor.getString(cursor.getColumnIndex(MultaTable.C_CAUSAMULTA));
                String tipo = cursor.getString(cursor.getColumnIndex(MultaTable.C_TIPO));
                String monto = cursor.getString(cursor.getColumnIndex(MultaTable.C_MONTO));
                String dni = cursor.getString(cursor.getColumnIndex(MultaTable.C_DNI));
                String nombre = cursor.getString(cursor.getColumnIndex(MultaTable.C_NOMBRE));

                Multa multa = new Multa(placa, causa, tipo, monto, dni,nombre);

                multa.setId(id);
                list.add(multa);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public int delete(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows =  db.delete(MultaTable.TABLE_NAME, MultaTable.C_ID + "=?",
                new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }

    public Multa get(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(MultaTable.TABLE_NAME, columns, "id=?",
                new String[]{String.valueOf(id)}, null, null, null);
        Multa multa = null;
        if (cursor.moveToFirst()) {
            String placa = cursor.getString(cursor.getColumnIndex(MultaTable.C_PLACA));
            String causa = cursor.getString(cursor.getColumnIndex(MultaTable.C_CAUSAMULTA));
            String tipo = cursor.getString(cursor.getColumnIndex(MultaTable.C_TIPO));
            String dni = cursor.getString(cursor.getColumnIndex(MultaTable.C_DNI));
            String nombre = cursor.getString(cursor.getColumnIndex(MultaTable.C_NOMBRE));
            String monto = cursor.getString(cursor.getColumnIndex(MultaTable.C_MONTO));
            multa = new Multa(placa, causa, tipo, dni, nombre, monto);
            multa.setId(id);
        }
        cursor.close();
        db.close();
        return multa;
    }
}
