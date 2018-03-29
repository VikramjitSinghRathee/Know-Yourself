package com.example.vrathee.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton imgbut1;
    ImageButton imgbut2;
    ImageButton imgbut3;
    ImageButton imgbut4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgbut1 = (ImageButton) findViewById(R.id.imageButton1);
        imgbut2 = (ImageButton) findViewById(R.id.imageButton2);
        imgbut3 = (ImageButton) findViewById(R.id.imageButton3);
        imgbut4 = (ImageButton) findViewById(R.id.imageButton4);
        imgbut1.setOnClickListener(this);   // this is the callback initiated when the button is created
        imgbut2.setOnClickListener(this);
        imgbut3.setOnClickListener(this);
        imgbut4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        /*
        switch (v.getId()) {

            case R.id.imageButton1:
                // do your code
                break;

            case R.id.imageButton2:
                // do your code
                break;

            case R.id.imageButton3:
                // do your code
                break;

            case R.id.imageButton4:
                // do your code
                break;

            default:
                break;
        }*/
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
