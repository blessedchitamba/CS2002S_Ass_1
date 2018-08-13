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
    private String[] strings = new String[11];
    private static final String FILE_NAME = "example.txt";
    private EditText editText;
    private int lines_count = 0;
    private int total_usage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_activity);
    }

    //save button listener method
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
            for (int i=0; i<10; i++) {
                fos.write(strings[i].getBytes());
                fos.write(" ".getBytes());

                if(i!=0){
                    total_usage+=Integer.parseInt(strings[i]);
                }
            }
            strings[10]=Integer.toString(total_usage);
            fos.write(strings[10].getBytes());
            fos.write("\n".getBytes());
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

        //go through each line in the file counting
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String output;

            while ((br.readLine()) != null) {
                lines_count++;
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

        //build the intent to go to the diary entry activity
        //use Bundle class to pass the whole strings array into the intent, instead of one by one
        Intent intent = new Intent(this, DiaryActivity.class);
        Bundle array = new Bundle();
        array.putStringArray("Key", strings);
        array.putString("linesCount", Integer.toString(lines_count));
        intent.putExtras(array);
        startActivity(intent);
    }

    //Exit button listener method
    public void exit(View view){
        super.onBackPressed();
    }
}

