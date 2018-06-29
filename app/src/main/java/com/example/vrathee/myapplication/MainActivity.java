package com.example.vrathee.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1;
    Button button2;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.value_list_button);
        button2 = (Button) findViewById(R.id.chart_button);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

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
