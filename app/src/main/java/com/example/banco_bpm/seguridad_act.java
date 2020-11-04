package com.example.banco_bpm;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.ExemptionMechanismException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class seguridad_act extends AppCompatActivity {

    private EditText et;
    private TextView tv;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguridad);

        et = (EditText)findViewById(R.id.contraseña_et);
        tv = (TextView) findViewById(R.id.encrip_tx);
    }

    private SecretKeySpec generateKey(String password)throws Exception{
        MessageDigest sh = MessageDigest.getInstance("SHA-256");
        byte[] key = password.getBytes("UTF-8");
        key = sh.digest(key);

        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        return secretKey;
    }

    public String Encriptar(String datos, String password)throws Exception{
        if(!et.getText().toString().isEmpty())
        {
            SecretKeySpec secretKey = generateKey(password);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] datoEncriptadoByte = cipher.doFinal(datos.getBytes());
            String datosEncriptadosString = Base64.encodeToString(datoEncriptadoByte,Base64.DEFAULT);
            return datosEncriptadosString;
        }
        else
        {
            Toast.makeText(this,"Ingrese contraseña", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public void Encriptar(View v){
        try {
            String mensaje = Encriptar(et.getText().toString(), tv.getText().toString());
            tv.setText(mensaje);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
