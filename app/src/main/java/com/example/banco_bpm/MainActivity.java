package com.example.banco_bpm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.reflect.Executable;

public class MainActivity extends AppCompatActivity {

    private EditText nombre,contraseña;
    private TextView textView;
    private ProgressBar proceso;
    private Button botonsesion;
    private String user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText)findViewById(R.id.edtx1);
        contraseña = (EditText)findViewById(R.id.edtx2);
        botonsesion = (Button)findViewById(R.id.btninicio);
        textView = (TextView)findViewById(R.id.tvMensaje);
        proceso = (ProgressBar)findViewById(R.id.progress);
        proceso.setVisibility(View.INVISIBLE);

        botonsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Task().execute();
            }
        });
    }

    class Task extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            proceso.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            for (int i = 1; i < 5; i++)
            {
                try {
                    Thread.sleep(1000);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            proceso.setVisibility(View.INVISIBLE);
            user = nombre.getText().toString().toUpperCase();
            pass = contraseña.getText().toString().toUpperCase();
            if (user.equals("ANDROID") && pass.equals("123"))
            {
                Intent i = new Intent(getBaseContext(), home_act.class);
                startActivity(i);
            }
            else
            {
                textView.setText("Usuario o contraseña incorrecto");
            }
        }
    }
}