package com.watertracker.watertracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calculator_activity extends AppCompatActivity {

    private static final String EXTRA_MESSAGE = "com.watertracker.watertracker.MESSAGE";
    private String[] strings = new String[10];
    private static final String FILE_NAME = "example.txt";
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_activity);
    }

    public void save(View view) {
        //load the input strings into the array. clear the text in each input field at each count
        editText = findViewById(R.id.date);
        strings[0] = editText.getText().toString();
        editText.getText().clear();

        editText = findViewById(R.id.input1);
        strings[1] = editText.getText().toString();
        editText.getText().clear();

        editText = findViewById(R.id.input2);
        strings[2] = editText.getText().toString();
        editText.getText().clear();

        editText = findViewById(R.id.input3);
        strings[3] = editText.getText().toString();
        editText.getText().clear();

        editText = findViewById(R.id.input4);
        strings[4] = editText.getText().toString();
        editText.getText().clear();

        editText = findViewById(R.id.input5);
        strings[5] = editText.getText().toString();
        editText.getText().clear();

        editText = findViewById(R.id.input6);
        strings[6] = editText.getText().toString();
        editText.getText().clear();

        editText = findViewById(R.id.input7);
        strings[7] = editText.getText().toString();
        editText.getText().clear();

        editText = findViewById(R.id.input8);
        strings[8] = editText.getText().toString();
        editText.getText().clear();

        editText = findViewById(R.id.input9);
        strings[9] = editText.getText().toString();
        editText.getText().clear();

        //start writing the strings into file. iterate through strings array
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_APPEND);
            for (String s: strings) {
                fos.write(s.getBytes());
                fos.write(" ".getBytes());
            }
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show();
    } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Exit button listener method
    public void exit(View view){

    }
}

