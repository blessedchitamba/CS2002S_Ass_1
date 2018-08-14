package com.watertracker.watertracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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


        if(savedInstanceState!=null){
            setContentView(R.layout.activity_calculator_activity);
            fillInfo(savedInstanceState.getStringArray("data"));
            LinearLayout rTotal = findViewById(R.id.linearLayout2);
            rTotal.setVisibility(View.GONE);
        }
        else {
            setContentView(R.layout.activity_calculator_activity);
            LinearLayout rTotal = findViewById(R.id.linearLayout2);
            rTotal.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        strings = getInfo(strings);
        savedInstanceState.putStringArray("data", strings);
    }

    /*@Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        fillInfo(savedInstanceState.getStringArray("data"));
    }*/

    @Override
    public void onResume(){
        super.onResume();
    }

    //save button listener method
    public void save(View view) {

        //fill the array with the user input
        strings = getInfo(strings);

        writeToFile(strings, FILE_NAME);

        //get count of lines
        lines_count = countLines(FILE_NAME, lines_count);

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

    public String[] getInfo(String[] emptyArray){
        //load the input strings into the array. clear the text in each input field at each count
        editText = findViewById(R.id.date);
        emptyArray[0] = editText.getText().toString();
        //editText.getText().clear();

        editText = findViewById(R.id.input1);
        emptyArray[1] = editText.getText().toString();
        //editText.getText().clear();

        editText = findViewById(R.id.input2);
        emptyArray[2] = editText.getText().toString();
        //editText.getText().clear();

        editText = findViewById(R.id.input3);
        emptyArray[3] = editText.getText().toString();
        //editText.getText().clear();

        editText = findViewById(R.id.input4);
        emptyArray[4] = editText.getText().toString();
        //editText.getText().clear();

        editText = findViewById(R.id.input5);
        emptyArray[5] = editText.getText().toString();
        //editText.getText().clear();

        editText = findViewById(R.id.input6);
        emptyArray[6] = editText.getText().toString();
       // editText.getText().clear();

        editText = findViewById(R.id.input7);
        emptyArray[7] = editText.getText().toString();
        //editText.getText().clear();

        editText = findViewById(R.id.input8);
        emptyArray[8] = editText.getText().toString();
        //editText.getText().clear();

        editText = findViewById(R.id.input9);
        emptyArray[9] = editText.getText().toString();
        //editText.getText().clear();

        return emptyArray;
    }

    //method to write the inputted values to file
    public void writeToFile(String[] dataArray, String FILE_NAME){

        //start writing the strings into file. iterate through strings array
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_APPEND);
            for (int i=0; i<10; i++) {
                fos.write(dataArray[i].getBytes());
                fos.write(" ".getBytes());

                if(i!=0){
                    total_usage+=Integer.parseInt(dataArray[i]);
                }
            }
            dataArray[10]=Integer.toString(total_usage);
            fos.write(dataArray[10].getBytes());
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
    }

    //method to count the number of lines currently in the file
    public int countLines(String FILE_NAME, int lines_count){
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

        return lines_count;
    }

    public void fillInfo(String[] savedArray){
        //load the previous state's data into new instance
        editText = findViewById(R.id.date);
        editText.setText(savedArray[0]);

        editText = findViewById(R.id.input1);
        editText.setText(savedArray[1]);

        editText = findViewById(R.id.input2);
        editText.setText(savedArray[2]);

        editText = findViewById(R.id.input3);
        editText.setText(savedArray[3]);

        editText = findViewById(R.id.input4);
        editText.setText(savedArray[4]);

        editText = findViewById(R.id.input5);
        editText.setText(savedArray[5]);

        editText = findViewById(R.id.input6);
        editText.setText(savedArray[6]);

        editText = findViewById(R.id.input7);
        editText.setText(savedArray[7]);

        editText = findViewById(R.id.input8);
        editText.setText(savedArray[8]);

        editText = findViewById(R.id.input9);
        editText.setText(savedArray[9]);
    }

}

