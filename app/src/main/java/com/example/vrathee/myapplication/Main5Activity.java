package com.example.vrathee.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main5Activity extends AppCompatActivity implements View.OnClickListener {

    ImageButton imgbut1;
    ImageButton imgbut2;
    ImageButton imgbut3;
    ImageButton imgbut4;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int scale_value;
    int old_scale_value;
    int cur_value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        // this is the date generation for naming the file
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);

        // opening the shared preference corresponding to current date
        sharedPreferences = getSharedPreferences("scale_value_"+formattedDate, Context.MODE_PRIVATE);
        Toast.makeText(this,"the sharedpreference name is "+"scale_value_"+formattedDate, Toast.LENGTH_LONG).show();
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        old_scale_value = sharedPreferences.getInt("neutral",0);


        imgbut1 = (ImageButton) findViewById(R.id.imageButton1);
        imgbut2 = (ImageButton) findViewById(R.id.imageButton2);
        imgbut3 = (ImageButton) findViewById(R.id.imageButton3);
        imgbut4 = (ImageButton) findViewById(R.id.imageButton4);
        imgbut1.setOnClickListener(this);
        imgbut2.setOnClickListener(this);
        imgbut3.setOnClickListener(this);
        imgbut4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.imageButton1:
                scale_value = 1;
                setSharedPreferences(scale_value);
                Toast.makeText(this,"Data saved", Toast.LENGTH_LONG).show();
                cur_value = sharedPreferences.getInt("neutral",0);
                Toast.makeText(this,"the data loaded is "+String.valueOf(cur_value), Toast.LENGTH_LONG).show();
                break;

            case R.id.imageButton2:
                scale_value = 2;
                setSharedPreferences(scale_value);
                Toast.makeText(this,"Data saved", Toast.LENGTH_LONG).show();
                cur_value = sharedPreferences.getInt("neutral",0);
                Toast.makeText(this,"the data loaded is "+String.valueOf(cur_value), Toast.LENGTH_LONG).show();
                break;

            case R.id.imageButton3:
                scale_value = 3;
                setSharedPreferences(scale_value);
                Toast.makeText(this,"Data saved", Toast.LENGTH_LONG).show();
                cur_value = sharedPreferences.getInt("neutral",0);
                Toast.makeText(this,"the data loaded is "+String.valueOf(cur_value), Toast.LENGTH_LONG).show();
                break;

            case R.id.imageButton4:
                scale_value = 4;
                setSharedPreferences(scale_value);
                Toast.makeText(this,"Data saved", Toast.LENGTH_LONG).show();
                cur_value = sharedPreferences.getInt("neutral",0);
                Toast.makeText(this,"the data loaded is "+String.valueOf(cur_value), Toast.LENGTH_LONG).show();
                break;

            default:
                break;
        }
    }

    public void setSharedPreferences(int scale_value){
        if(old_scale_value != 0)
        {
            scale_value = scale_value + old_scale_value;
            editor.putInt("neutral",scale_value);
        }
        editor.putInt("neutral",scale_value);
        editor.commit();
    }
}
