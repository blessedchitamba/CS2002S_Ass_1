package com.watertracker.watertracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String FILE_NAME = "example.txt";
    private TextView textview;
    ArrayList<String> theDisplay = new ArrayList<>(10);
    ArrayAdapter adapter;
    ListView list;
    private int linesCount;
    private int current_total;
    private double average;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = findViewById(R.id.theList);

       // textview = findViewById(R.id.entries_display);

        //read data from file and display in ListView
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String output;
            linesCount=0;

            while ((output = br.readLine()) != null) {
                theDisplay.add(output.split(" ")[0]+": "+output.split(" ")[10]+"litres.");
                linesCount++;
                current_total+=Integer.parseInt(output.split(" ")[10]);
            }

            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, theDisplay);
            list.setAdapter(adapter);
            textview = findViewById(R.id.editText2);
            textview.setText(Integer.toString(current_total)+getString(R.string.litres));

            average = Math.round(((double)current_total/linesCount));
            textview = findViewById(R.id.editText3);
            textview.setText(Double.toString(average)+getString(R.string.litres));

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
    }

    @Override
    protected void onResume(){
        super.onResume();
        linesCount=0;
        current_total=0;
        average=0;

        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String output;
            theDisplay.clear();

            while ((output=br.readLine()) != null) {
                theDisplay.add(output.split(" ")[0]+": "+output.split(" ")[10]+"litres.");
                current_total+=Integer.parseInt(output.split(" ")[10]);
                linesCount++;
            }
            adapter=null;
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, theDisplay);
            list.setAdapter(adapter);

            textview = findViewById(R.id.editText2);
            textview.setText(Integer.toString(current_total)+getString(R.string.litres));

            average = Math.round(((double)current_total/linesCount));
            textview = findViewById(R.id.editText3);
            textview.setText(Double.toString(average)+getString(R.string.litres));

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
    }

    public void newEntry(View view) {
        Intent intent = new Intent(this, Calculator_activity.class);
        startActivity(intent);
    }

}
