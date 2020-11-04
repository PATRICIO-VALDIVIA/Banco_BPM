package com.example.banco_bpm;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import clase.AdminSQLiteOpenHelper;

import androidx.appcompat.app.AppCompatActivity;

public class clientes_act extends AppCompatActivity {
    private EditText edcodigo, ednombre, edsalario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        edcodigo = (EditText) findViewById(R.id.edtxcodigo);
        ednombre = (EditText) findViewById(R.id.edtxnombre);
        edsalario = (EditText) findViewById(R.id.edtxsalario);
    }

    public void GuardarCliente(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "ficheros", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        if (verificardatos() == true) {
            ContentValues cont = new ContentValues();
            cont.put("codigo", edcodigo.getText().toString());
            cont.put("nombre", ednombre.getText().toString());
            cont.put("salario", edsalario.getText().toString());

            db.insert("clientes", null, cont);
            db.close();

            Toast.makeText(this, "Has guardado los datos de un cliente", Toast.LENGTH_LONG).show();


        } else {
            Toast.makeText(this, "Ingrese los datos del cliente", Toast.LENGTH_LONG).show();
        }


    }

    public void Mostrarcliente(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "ficheros", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String codigo = edcodigo.getText().toString();

        if (!codigo.isEmpty()) {
            Cursor fila = db.rawQuery("SELECT nombre, salario FROM clientes WHERE codigo=" + codigo, null);

            if (fila.moveToFirst()) {
                ednombre.setText(fila.getString(0));
                edsalario.setText(fila.getString(1));
            } else {
                Toast.makeText(this, "No hay datos en la tabla de clientes", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "No exite el cliente con el codigo asociado", Toast.LENGTH_LONG).show();

        }
        db.close();
    }

    public void EliminarCliente(View v)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"ficheros", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String codigo = edcodigo.getText().toString();

        db.delete("clientes", "codigo="+codigo, null);
        db.close();

        Toast.makeText(this, "Ha eliminado un cliente", Toast.LENGTH_LONG).show();

    }
    public void Actualizarcliente(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "ficheros", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String codigo = edcodigo.getText().toString();

        ContentValues cont = new ContentValues();
        cont.put("codigo", edcodigo.getText().toString());
        cont.put("nombre", ednombre.getText().toString());
        cont.put("salario", edsalario.getText().toString());

        if (verificardatos()) {
            db.update("clientes", cont, "codigo=" + codigo, null);
            db.close();

            Toast.makeText(this, "Has actulizado los datos del cliente", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Debe ingresar el codigo del cliente", Toast.LENGTH_LONG).show();
        }
    }

        public boolean verificardatos()
        {
            if (!edcodigo.getText().toString().isEmpty() || !ednombre.getText().toString().isEmpty() || !edsalario.getText().toString().isEmpty())
            {
                return true;

            }
            else {
                return false;

            }
        }

    }


