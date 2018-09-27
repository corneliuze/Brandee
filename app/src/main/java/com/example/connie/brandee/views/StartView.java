package com.example.connie.brandee.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.connie.brandee.MainActivity;
import com.example.connie.brandee.R;

public class StartView extends Activity {

    Button  playButton, soundButton;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_view);

        playButton  = findViewById(R.id.play);

        soundButton = findViewById(R.id.sound_button);
        }
    public void openMainActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


}