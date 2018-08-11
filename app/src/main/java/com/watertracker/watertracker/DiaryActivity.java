package com.watertracker.watertracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DiaryActivity extends AppCompatActivity {
    String[] arrayReceived;
    TextView textView;
    TextView tView;
    View child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        //get the array bundle in the intent
        Bundle bundle = getIntent().getExtras();
        arrayReceived = bundle.getStringArray("Key");

        //take the first array item which is the date, and write onto the date header
        textView = findViewById(R.id.textView5);
        textView.setText(arrayReceived[0]);

        //go through all the other text fields and set their values
        textView = findViewById(R.id.editText6);
        textView.setText(arrayReceived[1]);

        textView = findViewById(R.id.editText7);
        textView.setText(arrayReceived[2]);

        textView = findViewById(R.id.editText8);
        textView.setText(arrayReceived[3]);

        textView = findViewById(R.id.editText9);
        textView.setText(arrayReceived[4]);

        textView = findViewById(R.id.editText10);
        textView.setText(arrayReceived[5]);

        textView = findViewById(R.id.editText11);
        textView.setText(arrayReceived[6]);

        textView = findViewById(R.id.editText12);
        textView.setText(arrayReceived[7]);

        textView = findViewById(R.id.editText13);
        textView.setText(arrayReceived[8]);

        textView = findViewById(R.id.editText14);
        textView.setText(arrayReceived[9]);

    }

}
