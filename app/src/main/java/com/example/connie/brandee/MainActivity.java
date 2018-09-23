package com.example.connie.brandee;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.connie.brandee.models.Questions;
import com.example.connie.brandee.views.AnswerAdapter;
import com.example.connie.brandee.views.InputAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements InputAdapter.InputClickListener,
    AnswerAdapter.AnswerClickListener, AnswerAdapter.AnswerFilledListener{
    ImageView logoImage;
    RecyclerView answerRecyclerView, inputRecyxlerView;
    Questions currentQuestion;
    ArrayList<Character> inputs;

    Context context;
    InputAdapter inputAdapter;
    AnswerAdapter answerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logoImage = findViewById(R.id.logo_image_view);
        answerRecyclerView = findViewById(R.id.answer_recyclerview);
        inputRecyxlerView = findViewById(R.id.input_recyclerview);
        context = getApplicationContext();
        try{
            parseJson(readFromFile());
        }catch (IOException e){

        }

        Glide.with(getApplicationContext())
                .load(Uri.parse("file:///android_asset/" + currentQuestion.getLogo()))
                .into(logoImage);
        inputAdapter = new InputAdapter(context, inputs,  this,  currentQuestion.getName().length());
        answerAdapter = new AnswerAdapter(context, currentQuestion.getName().toUpperCase(),this, this);

        inputRecyxlerView.setLayoutManager(new GridLayoutManager(context, 7));
        inputRecyxlerView.setAdapter(inputAdapter);

        int numOfBoxes = currentQuestion.getName().length()< 6 ? currentQuestion.getName().length() : 6;
        answerRecyclerView.setLayoutManager(new GridLayoutManager(context, numOfBoxes));
        answerRecyclerView.setAdapter(answerAdapter);
    }


    // this methods stores the json string into an arraylist called formlist
    public void parseJson(String ret) throws IOException {

            ArrayList<Questions> allQuestions = new Gson()//this is where the gson library is used to convert .json files to an array list
                    .fromJson(ret, new TypeToken<ArrayList<Questions>>() {
                    }.getType());

            currentQuestion = allQuestions.get(4);
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

        @Override
    public void onInputClick(int position, char cr){
        answerAdapter.addNewCharacter(position, cr);
        }

        @Override
        public void onAnswerClicked(char cr){
        inputAdapter.onReturnCharacter(cr);
        }
        public void onAnswerFilled(boolean isCorrect){
        if (isCorrect){
            Toast.makeText(context, "you are correct!!!", Toast.LENGTH_LONG).show();
        }else {

            Toast.makeText(context, "you are wrong!!!", Toast.LENGTH_LONG).show();
        }
        }
    }









