package com.example.connie.brandee;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // to read the json file from raw folder
    public String readFromFile() throws IOException {
        String ret = null;
        InputStream inputStream = getResources().openRawResource(R.raw.questions);
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receivedString = null;

            StringBuilder stringBuilder = new StringBuilder();
            while ((receivedString = bufferedReader.readLine()) != null) {
                stringBuilder.append(receivedString);
                inputStream.close();
                ret = stringBuilder.toString();
            }

        }

        return ret;
    }

    // this methods stores the json string into an arraylist called formlist
    public void parseJson(String ret) throws IOException {
        try {
            JSONObject obj = new JSONObject(ret);
            JSONArray jArry = obj.getJSONArray("Questions");
            ArrayList<String> formList = new ArrayList<>();


            for (int i = 0; i < jArry.length(); i++) {
                JSONObject jo_inside = jArry.getJSONObject(i);
                //Log.d("Details-->", jo_inside.getString("name"));
                String name = jo_inside.getString("name");


                //Add your values in your `ArrayList` as below:
                formList.add(name);


                for (i = 0; i < formList.size(); i++) {   // for each instance of the string from the arraylist
                    String charac = formList.get(i);   // each instance forms a string called charac
                    char[] charArray = charac.toCharArray(); // charac  converts each  string to a character array
                    int a = charArray.length; //we get the length of each chararray
                    int c = 14 - a; // then subtract from 20 which is the total input we want to populate for users to pick from

                    int w = 0;
                    ArrayList<Character> inputs = new ArrayList<>();
                    for (int j = 0; j < charArray.length; j++) inputs.add(charArray[j]);

                    while (w < c) {
                        w++;

                        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

                        Random random = new Random();
                        inputs.add(chars.charAt(random.nextInt(chars.length())));

                    }
                    Collections.shuffle(inputs);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}






