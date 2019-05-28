package com.example.connie.brandee.views;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.example.connie.brandee.MainActivity;
import com.example.connie.brandee.R;

public class StartView extends Activity {

    Button playButton;
    private LottieAnimationView lottieAnimationView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_view);

        playButton = findViewById(R.id.play);
        lottieAnimationView = findViewById(R.id.empty_img);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

    }

    public void openMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }


}