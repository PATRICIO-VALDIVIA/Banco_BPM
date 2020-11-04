package com.example.banco_bpm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class info_act extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        videoView = (VideoView)findViewById(R.id.videoView2);

        String ruta ="android.resource://"+getPackageName()+"/"+R.raw.video;
        Uri uri = Uri.parse(ruta);
        videoView.setVideoURI(uri);

        MediaController media = new MediaController(this);
        videoView.setMediaController(media);
        videoView.start();
    }

    public void Maps(View v)
    {
        Intent i = new Intent(this, Maps_act.class);
        startActivity(i);
    }
}
