package com.example.niva.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

public class Main2Activity extends AppCompatActivity {

    private LinearLayout layout;
    private Button cmdSpeak;
    private TextToSpeech textToSpeech;//declare the object
    private TextView inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //initializing
        SharedPreferences sp = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        int color = sp.getInt("color", Color.WHITE);

        cmdSpeak = (Button)findViewById(R.id.button2);
        layout = (LinearLayout)findViewById(R.id.layout);
        layout.setBackgroundColor(color);
        inputText = (TextView)findViewById(R.id.textView2);
        inputText.setText(getIntent().getStringExtra("txtInput"));
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.GERMANY);
                }
            }
        }); //initialize the object in ‘onCreate’ method


        cmdSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.speak(inputText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
//inside the listener of the button

            }
        });
    }
}
