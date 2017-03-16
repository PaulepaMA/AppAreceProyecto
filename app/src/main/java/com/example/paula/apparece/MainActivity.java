package com.example.paula.apparece;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv1 ;
    ArrayList<String> lista1;
    ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv1 = (ListView)findViewById(R.id.lv1);
        //llenamos el listview con un arraylist que nos devuelve la base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        lista1 = admin.llenar_lv();
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista1);
        lv1.setAdapter(adaptador);
    }

        //hacemos visible el menú
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

        //enlazamos cada opción del menú con un activity
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id. perdido) {
            Intent i = new Intent(this, Perdido.class );
            startActivity(i);
            return true;
        }
        if (id == R.id. lista) {
            Intent i = new Intent(this, MainActivity.class );
            startActivity(i);
            return true;
        }
        if (id == R.id. nuevo) {
            Intent i = new Intent(this, Nuevo.class );
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
