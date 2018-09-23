package com.example.connie.brandee;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.connie.brandee.models.Questions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView logoImage;
    RecyclerView answerRecyclerView, inputRecyxlerView;
    Questions currentQuestion;
    ArrayList<Character> inputs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        logoImage = findViewById(R.id.logo_image_view);
        answerRecyclerView = findViewById(R.id.answer_recyclerview);
        inputRecyxlerView = findViewById(R.id.input_recyclerview);
        try{
            parseJson(readFromFile());
        }catch (IOException e){

        }

        Glide.with(getApplicationContext())
                .load(Uri.parse("file:///android_asset/" + currentQuestion.getLogo()))
                .into(logoImage);
    }


    // this methods stores the json string into an arraylist called formlist
    public void parseJson(String ret) throws IOException {

            ArrayList<Questions> allQuestions = new Gson()//this is where the gson library is used to convert .json files to an array list
                    .fromJson(ret, new TypeToken<ArrayList<Questions>>() {
                    }.getType());

            currentQuestion = allQuestions.get(0);
            String charac = currentQuestion.getName().toUpperCase();   // each instance forms a string called charac
            char[] charArray = charac.toCharArray(); // charac  converts each  string to a character array
            int a = charArray.length; //we get the length of each chararray
            int c = 14 - a; // then subtract from 20 which is the total input we want to populate for users to pick from

            int w = 0;
            inputs = new ArrayList<>();
            for (int j = 0; j < charArray.length; j++) {
                inputs.add(charArray[j]);
            }
            while (w < c) {
                w++;

                String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

                Random random = new Random();
                inputs.add(chars.charAt(random.nextInt(chars.length())));

            }
            Collections.shuffle(inputs);
        }

// to read the json file from raw folder
        public String readFromFile ()throws IOException {

            InputStream inputStream = getResources().openRawResource(R.raw.questions);
            return IOUtils.toString(inputStream);
        }
    }









