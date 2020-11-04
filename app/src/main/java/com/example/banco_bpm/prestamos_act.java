package com.example.banco_bpm;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class prestamos_act extends AppCompatActivity {

    private Spinner espUser, espSaldo;
    private TextView tv;
    private Button btnDeuda, btnPrestamo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestamos);

        espUser = (Spinner)findViewById(R.id.cliente_sp);
        espSaldo = (Spinner)findViewById(R.id.saldo_sp);
        tv = (TextView)findViewById(R.id.calculo_et);
        btnDeuda = (Button)findViewById(R.id.deuda_btn);
        btnPrestamo = (Button)findViewById(R.id.prestamo_btn);

        String[] listaClientes = {"AXEL","ROXANA"};
        String[] listaPrestamos = {"CREDITO HIPOTECARIO","CREDITO AUTOMOTRIZ"};
        ArrayAdapter<String> adapt = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listaClientes);
        ArrayAdapter<String> adapts = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listaPrestamos);
        espUser.setAdapter(adapt);
        espSaldo.setAdapter(adapts);
    }

    public void calcularPrestamo(View v)
    {
        try {
            tv.setText("Su saldo final es:"+(Usuario()+Credito()));
        }
        catch (Exception e)
        {
            tv.setText("Ha ocurrido un error");
        }
    }

    public void calcularDeuda(View v)
    {
        try {
            tv.setText("Su deuda final es:"+((Usuario()+Credito())/Deuda()));
        }
        catch (Exception e)
        {
            tv.setText("Ha ocurrido un error");
        }
    }

    public int Credito()
    {
        String credito = espSaldo.getSelectedItem().toString();

        if (credito.equals("CREDITO HIPOTECARIO")) {
            return 1000000;
        } else if (credito.equals("CREDITO AUTOMOTRIZ")) {
            return 500000;
        }
        else
        {
            return 0;
        }
    }

    private int Usuario()
    {
        String usuario = espUser.getSelectedItem().toString();

        if(usuario.equals("AXEL"))
        {
            return 750000;
        }
        else if(usuario.equals("ROXANA"))
        {
            return 900000;
        }
        else
        {
            return 0;
        }
    }

    private int Deuda()
    {
        String i = espSaldo.getSelectedItem().toString();
        if (i.equals("CREDITO HIPOTECARIO"))
        {
            return 12;
        }
        else if (i.equals("CREDITO AUTOMOTRIZ"))
        {
            return 8;
        }
        else
        {
            return 0;
        }
    }

}
