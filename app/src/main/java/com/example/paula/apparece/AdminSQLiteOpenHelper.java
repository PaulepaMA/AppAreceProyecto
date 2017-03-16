package com.example.paula.apparece;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class AdminSQLiteOpenHelper  extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, 1);
    }

    //creamos la tabla en la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table cosas(nombre text primary key, descripcion text, situacion text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //añade los datos de la base de datos a un aray list cuando el tercer campo es 'Localizado'
    public ArrayList llenar_lv(){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM cosas where situacion='Localizado'";
        Cursor registros = database.rawQuery(q,null);
        if(registros.moveToFirst()){
            do{
                lista.add(registros.getString(0));
                lista.add(registros.getString(1));
            }while(registros.moveToNext());
        }
        return lista;

    }

    //añade los datos de la base de datos a un aray list cuando el tercer campo es 'Perdido'
    public ArrayList llenar_perdido(){
        ArrayList<String> lista2 = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String r = "SELECT * FROM cosas where situacion='Perdido'";
        Cursor registros = database.rawQuery(r,null);
        if(registros.moveToFirst()){
            do{
                lista2.add(registros.getString(0));
            }while(registros.moveToNext());
        }
        return lista2;

    }
}