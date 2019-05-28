package com.example.connie.brandee.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.connie.brandee.MainActivity;
import com.example.connie.brandee.R;

public class WrongResultActivity extends AppCompatActivity {
    private Button wrongButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_result);
        wrongButton = findViewById(R.id.continue_wrong_button);

        wrongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWrongAnimation();
            }
        });
    }

    public void setWrongAnimation() {
        Intent intent = new Intent(WrongResultActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
