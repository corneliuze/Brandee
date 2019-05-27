package com.example.connie.brandee.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.connie.brandee.MainActivity;
import com.example.connie.brandee.R;

public class RightResultActivity extends AppCompatActivity {
    private Button rightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        rightButton = findViewById(R.id.continue_right_button);

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RightResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
