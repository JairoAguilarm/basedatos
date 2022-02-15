package com.example.basedatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.basedatos.transacciones.Transacciones;

public class ActivityIngresar extends AppCompatActivity {

    EditText nombres;
    EditText apellidos;
    EditText edad;
    EditText correo;
    EditText direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        Button btn = (Button) findViewById(R.id.volver);
        nombres = (EditText) findViewById(R.id.ettNombres);
        apellidos = (EditText) findViewById(R.id.ettApellidos);
        edad = (EditText) findViewById(R.id.ettEdad);
        correo = (EditText) findViewById(R.id.ettCorreo);
        direccion = (EditText) findViewById(R.id.ettDireccion);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarPersona();
            }
        });

        Button volver = (Button) findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AgregarPersona() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, nombres.getText().toString());
        valores.put(Transacciones.apellidos, apellidos.getText().toString());
        valores.put(Transacciones.edad, edad.getText().toString());
        valores.put(Transacciones.correo, correo.getText().toString());
        valores.put(Transacciones.direccion, direccion.getText().toString());

        Long registro = db.insert(Transacciones.tablapersonas, Transacciones.id, valores);

        Toast.makeText(getApplicationContext(), " INSERCCION EXITOSA " + registro.toString(), Toast.LENGTH_LONG).show();

        db.close();

        ClearScreen();

    }

    private void ClearScreen() {
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        correo.setText("");
        direccion.setText("");
    }

}