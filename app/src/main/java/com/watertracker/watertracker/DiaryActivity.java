package com.watertracker.watertracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class DiaryActivity extends AppCompatActivity {
    String[] arrayReceived;
    int i;
    int current_position;
    TextView textView;
    String[][] all_data;
    Button right, left;
    private static final String FILE_NAME = "example.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        //get the array bundle in the intent
        Bundle bundle = getIntent().getExtras();
        arrayReceived = bundle.getStringArray("Key");
        i = Integer.parseInt(bundle.getString("linesCount"));
        current_position = i-1;

        //the two back and forth buttons
        right = findViewById(R.id.button4);
        left = findViewById(R.id.button5);

        //load the data in the received array onto the page
        fill_info(arrayReceived);

        //call a function to load the data on the text file into a 2D matrix
        this.all_data = loadMatrix(FILE_NAME, all_data);
    }

    //method to populate the activity with the data
    public void fill_info(String[] data_array){

        //if this is the first entry, hide the back button
        if(current_position==0){
            left.setVisibility(View.INVISIBLE);
        }
        else{left.setVisibility(View.VISIBLE);}

        //if this is the last entry, hide next button
        if(current_position==i-1){
            right.setVisibility(View.INVISIBLE);
        }
        else{right.setVisibility(View.VISIBLE);}

        //take the first array item which is the date, and write onto the date header
        textView = findViewById(R.id.textView5);
        textView.setText(data_array[0]);

        //go through all the other text fields and set their values
        textView = findViewById(R.id.editText6);
        textView.setText(data_array[1]);

        textView = findViewById(R.id.editText7);
        textView.setText(data_array[2]);

        textView = findViewById(R.id.editText8);
        textView.setText(data_array[3]);

        textView = findViewById(R.id.editText9);
        textView.setText(data_array[4]);

        textView = findViewById(R.id.editText10);
        textView.setText(data_array[5]);

        textView = findViewById(R.id.editText11);
        textView.setText(data_array[6]);

        textView = findViewById(R.id.editText12);
        textView.setText(data_array[7]);

        textView = findViewById(R.id.editText13);
        textView.setText(data_array[8]);

        textView = findViewById(R.id.editText14);
        textView.setText(data_array[9]);

        //to display the total usage too
        textView = findViewById(R.id.editText15);
        textView.setText(data_array[10]);
    }

    //method to load data from text file into matrix
    public String[][] loadMatrix(String fileName, String[][] all_data) {
        FileInputStream fis = null;
        try {
            fis = openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String output;
            all_data = new String[i][10];

            int j = i;
                while ((output = br.readLine()) != null) {
                    all_data[i-j] = output.split(" ");
                    j--;
                }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return all_data;
    }

    //Listener methods for the back and forth buttons. This one is for the left press
    public void leftPress(View view) {
        current_position--;
        fill_info(this.all_data[current_position]);
    }

    public void rightPress(View view){
        current_position++;
        fill_info(this.all_data[current_position]);
    }

    public void overview(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void calculator(View view){
        Intent intent = new Intent(this, Calculator_activity.class);
        startActivity(intent);
    }
}
