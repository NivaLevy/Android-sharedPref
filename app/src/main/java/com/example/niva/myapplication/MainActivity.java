package com.example.niva.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private Button cmdSubmit;
    private EditText txtInput;
    private RadioGroup rdoGroup;
    private LinearLayout layout;
    private int color;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing
        cmdSubmit = (Button)findViewById(R.id.button);
        txtInput = (EditText)findViewById(R.id.editText);
        rdoGroup = (RadioGroup)findViewById(R.id.group);
        layout = (LinearLayout)findViewById(R.id.layout);
        sp = getSharedPreferences("MyPref", MODE_PRIVATE);
        //get color from sharedPref
        layout.setBackgroundColor(sp.getInt("color", Color.WHITE));
        editor = sp.edit();

        cmdSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rdoGroup.getCheckedRadioButtonId();

                if(selectedId == R.id.radioButton)
                    color = Color.RED;
                if(selectedId == R.id.radioButton2)
                    color = Color.BLUE;
                if(selectedId == R.id.radioButton3)
                    color = Color.YELLOW;
                //color background
                layout.setBackgroundColor(color);
                //save to sharedPref

                editor.putInt("color", color);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                intent.putExtra("txtInput", txtInput.getText().toString());
                startActivity(intent);
            }
        });
    }
}
