package com.example.vrathee.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton imgbut1;
    ImageButton imgbut2;
    ImageButton imgbut3;
    ImageButton imgbut4;
    Button button1;
    Button button2;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgbut1 = (ImageButton) findViewById(R.id.imageButton1);
        imgbut2 = (ImageButton) findViewById(R.id.imageButton2);
        imgbut3 = (ImageButton) findViewById(R.id.imageButton3);
        imgbut4 = (ImageButton) findViewById(R.id.imageButton4);
        button1 = (Button) findViewById(R.id.value_list_button);
        button2 = (Button) findViewById(R.id.chart_button);
        imgbut1.setOnClickListener(this);   // this is the callback initiated when the button is created
        imgbut2.setOnClickListener(this);
        imgbut3.setOnClickListener(this);
        imgbut4.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.imageButton1:
                intent = new Intent(this, Main3Activity.class);
                break;

            case R.id.imageButton2:
                intent = new Intent(this, Main5Activity.class);
                break;

            case R.id.imageButton3:
                intent = new Intent(this, Main4Activity.class);
                break;

            case R.id.imageButton4:
                intent = new Intent(this, Main2Activity.class);
                break;

            case R.id.value_list_button:
                intent = new Intent(this, Main6Activity.class);
                break;

            case R.id.chart_button:
                intent = new Intent(this, Main7Activity.class);
                break;

            default:
                break;
        }
        startActivity(intent);
    }
}
