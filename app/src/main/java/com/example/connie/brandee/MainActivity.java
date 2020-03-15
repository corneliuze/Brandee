package com.example.connie.brandee;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.connie.brandee.data.PreferenceManager;
import com.example.connie.brandee.models.QuestionsModel;
import com.example.connie.brandee.views.AnswerAdapter;
import com.example.connie.brandee.views.InputAdapter;
import com.example.connie.brandee.views.RightResultActivity;
import com.example.connie.brandee.views.WrongResultActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements InputAdapter.InputClickListener,
        AnswerAdapter.AnswerClickListener, AnswerAdapter.AnswerFilledListener {

    private ImageView logoImage;
    private RecyclerView answerRecyclerView, inputRecyxlerView;
    private QuestionsModel currentQuestion;
    private ArrayList<Character> inputs;
    private Context context;
    private ArrayList<QuestionsModel> allQuestions;
    private InputAdapter inputAdapter;
    private AnswerAdapter answerAdapter;
    private PreferenceManager preferenceManager;
    public static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_main);
        logoImage = findViewById(R.id.logo_image_view);
        answerRecyclerView = findViewById(R.id.answer_recyclerview);
        inputRecyxlerView = findViewById(R.id.input_recyclerview);
        context = getApplicationContext();
        preferenceManager = new PreferenceManager(context);


        try {
            parseJson(readFromFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadImageToView();
        inputAdapter = new InputAdapter(context, inputs, this, currentQuestion.getName().length());
        answerAdapter = new AnswerAdapter(context, currentQuestion, this, this);

        inputRecyxlerView.setLayoutManager(new GridLayoutManager(context, 7));
        inputRecyxlerView.setAdapter(inputAdapter);

        int numOfBoxes = currentQuestion.getName().length() < 6 ? currentQuestion.getName().length() : 6;
        answerRecyclerView.setLayoutManager(new GridLayoutManager(context, numOfBoxes));
        answerRecyclerView.setAdapter(answerAdapter);
    }


    public QuestionsModel getRandomUnAnsweredQuestions(ArrayList<QuestionsModel> allQuestions) {
        if (preferenceManager.getAnsweredQuestions().split(",").length - 1 < allQuestions.size()) {
            int questionIndex = new Random().nextInt(allQuestions.size());
            QuestionsModel questionsModel = allQuestions.get(questionIndex);
            boolean answered = preferenceManager.isQuestionAnswered(questionsModel.getId());
            if (answered) {
                return getRandomUnAnsweredQuestions(allQuestions);
            } else
                return questionsModel;
        }
        return null;
    }

    // this methods stores the json string into an arraylist
    public void parseJson(String ret) {

        //this is where the gson library is used to convert question.json files to an array list
        allQuestions = new Gson()
                .fromJson(ret, new TypeToken<ArrayList<QuestionsModel>>() {
                }.getType());

        //fetching  current question
        getQuestionsTodisplay();


        // each name from  questions in questions.json forms a string called charac
        String charac = currentQuestion.getName().toUpperCase();
        // charac  converts each  string to a character array
        char[] charArray = charac.toCharArray();
        //we get the length of each chararray
        int a = charArray.length;
        // then subtract from 14 which is the total input we want to populate for users to pick from
        int c = 14 - a;
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
        //shuffle the char array to make the options harder to guess
        Collections.shuffle(inputs);
    }

    //used the glide library
    public void loadImageToView() {
        if (currentQuestion == null) {
            Glide.with(getApplicationContext())
                    .load(Uri.parse("file:///android_asset/" + currentQuestion.getLogo()))
                    .into(logoImage);
        }
        if (currentQuestion != null) {
            Glide.with(getApplicationContext())
                    .load(Uri.parse("file:///android_asset/" + currentQuestion.getLogo()))
                    .into(logoImage);
        } else {
            Toast.makeText(this, "You have finished the game", Toast.LENGTH_LONG).show();
        }
    }

    // to read the json file from raw folder using apache library
    public String readFromFile() throws IOException {
        InputStream inputStream = getResources().openRawResource(R.raw.questions);
        return IOUtils.toString(inputStream); // apache library
    }

    @Override
    public void onInputClick(int position, char cr) {
        answerAdapter.addNewCharacter(cr);
    }

    @Override
    public void onAnswerClicked(char cr) {
        inputAdapter.onReturnCharacter(cr);
    }

    @Override
    public void onAnswerFilled(boolean isCorrect, QuestionsModel questionsModel) {
        if (isCorrect) {
            preferenceManager.addToAnsweredQuestion(questionsModel.getId());
            preferenceManager.setCurrentQuestions(null);
            openResult();
        } else {
            openWrongResult();
        }

    }

    public void getQuestionsTodisplay() {
        currentQuestion = preferenceManager.getCurrentQuestion();
        if (currentQuestion == null) {
            Log.i(TAG, "FIRST NULL");
            currentQuestion = getRandomUnAnsweredQuestions(allQuestions);
        }
        if (currentQuestion == null) {
            Log.i(TAG, "second null");
            Toast.makeText(context, "You have completed the game.", Toast.LENGTH_SHORT).show();
            return;
        }
        preferenceManager.setCurrentQuestions(currentQuestion);

    }

    public void openResult() {
        Intent intent = new Intent(MainActivity.this, RightResultActivity.class);
        startActivity(intent);
    }

    public void openWrongResult() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        Toast.makeText(this, "Wrong Answer, Try Again!!!", Toast.LENGTH_LONG).show();
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            vibrator.vibrate(200);
        }

    }
}













