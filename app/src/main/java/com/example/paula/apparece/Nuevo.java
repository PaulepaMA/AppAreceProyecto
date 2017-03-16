package com.example.paula.apparece;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Nuevo extends AppCompatActivity {
    private EditText et1,et2;
    private RadioButton rb1,rb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        rb1=(RadioButton)findViewById(R.id.rb1);
        rb2=(RadioButton)findViewById(R.id.rb2);

    }

    //añade un nuevo elemento a la base de datos
    public void add(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nom = et1.getText().toString();
        String descri = et2.getText().toString();
        String situ=null;
        if (rb1.isChecked()==true) {
            situ = "Localizado";
        } else
        if (rb2.isChecked()==true) {
            situ = "Perdido";
        }
        ContentValues registro = new ContentValues();
        registro.put("nombre", nom);
        registro.put("descripcion", descri);
        registro.put("situacion", situ);
        bd.insert("cosas", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        Toast.makeText(this, "Se ha añadido a la lista",
                Toast.LENGTH_SHORT).show();
    }

    //modifica un elemento existende en la base de datos por el nombre
    public void modificar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nom = et1.getText().toString();
        String descri = et2.getText().toString();
        String situ=null;
        if (rb1.isChecked()==true) {
            situ = "Localizado";
        } else
        if (rb2.isChecked()==true) {
            situ = "Perdido";
        }
        ContentValues registro = new ContentValues();
        registro.put("nombre", nom);
        registro.put("descripcion", descri);
        registro.put("situacion", situ);
        int cant = bd.update("cosas", registro, "nombre='" + nom + "'", null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "Se ha modificado la localización de "+ nom,
                    Toast.LENGTH_SHORT).show();

        else
            Toast.makeText(this, "No tenemos localizado "+ nom,
                    Toast.LENGTH_SHORT).show();
        et1.setText("");
        et2.setText("");
    }

    //hace visible el menú
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
