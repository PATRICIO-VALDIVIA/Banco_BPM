package com.example.banco_bpm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

public class home_act extends AppCompatActivity {

    private int[] images = {R.drawable.a, R.drawable.b, R.drawable.c};
    private ViewFlipper vf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        vf =(ViewFlipper)findViewById(R.id.slider);

        for (int i= 0; i < images.length; i++)
        {
            flip_image(images[i]);
        }
    }

    protected void flip_image(int i)
    {
        ImageView view =new ImageView(this);
        view.setBackgroundResource(i);

        vf.addView(view);
        vf.setFlipInterval(2500);
        vf.setAutoStart(true);
        vf.setInAnimation(this, android.R.anim.fade_in);
        vf.setOutAnimation(this, android.R.anim.fade_out);
    }

    public void Clientes(View v)
    {
        Intent i = new Intent(this, clientes_act.class);
        startActivity(i);
    }

    public void Prestamos(View v)
    {
        Intent i = new Intent(this, prestamos_act.class);
        startActivity(i);
    }

    public void Seguridad(View v)
    {
        Intent i = new Intent(this, seguridad_act.class);
        startActivity(i);
    }

    public void Info(View v)
    {
        Intent i = new Intent(this, info_act.class);
        startActivity(i);
    }
}
