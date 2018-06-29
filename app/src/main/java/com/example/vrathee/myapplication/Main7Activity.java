package com.example.vrathee.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.DoubleStream;

public class Main7Activity extends AppCompatActivity {

    float rand_values[] = {0,0,0,0,0};
    String randNames[] = {"default","default","default","default","default"};

    Map<Integer, String> map=new HashMap<Integer, String>();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        sharedPreferences = getApplicationContext().getSharedPreferences("value_scale", Context.MODE_PRIVATE);
        Map<String,?> keys = sharedPreferences.getAll();

        for(Map.Entry<String,?> entry : keys.entrySet()){
            map.put(Integer.parseInt(entry.getValue().toString()),entry.getKey());
        }

        /* FOR TESTING

        for(Map.Entry<String,Integer> entrys : map.entrySet()){
            Log.d("nops values",entrys.getKey() + ": " +
                    entrys.getValue().toString());
        }


        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry me = (Map.Entry)iterator.next();
            Log.d("before values",me.getKey() + ": " +
                    me.getValue());
        }

        Set set2 = Tmap.entrySet();
        Iterator iterator2 = set2.iterator();
        while(iterator2.hasNext()) {
            Map.Entry me2 = (Map.Entry)iterator2.next();
            Log.d("after values",me2.getKey() + ": " +
                    me2.getValue().toString());
        }*/


        // GET ONLY THE TOP 5 VALUES
        Map<Integer, String> Tmap = new TreeMap<Integer, String>(map);
        ArrayList<Integer> newkey = new ArrayList<Integer>(Tmap.keySet());
        int jj = 0;
        for(int i=newkey.size()-1; i>=0;i--){
            Log.d("after2 values",Tmap.get(newkey.get(i)));

            // GET ONLY THE TOP 5 VALUES
            randNames[jj]= Tmap.get(newkey.get(i));
            rand_values[jj]= (float) newkey.get(i);
            jj++;
            if (i<=newkey.size()-5){
                break;
            }
        }

        // UPDATING THE ARRAY OF VALUES GOING INTO PIECHART RELATIVE TO TOTAL SUM
        float val_sum = 0;
        for (float element : rand_values) {
            val_sum += element;
        }
        for (int x =0;x <rand_values.length;x++){
            rand_values[x] = rand_values[x] / val_sum * 100; // redundant but will leave it here as of now
        }


        // SETUP PIE CHART
        setupPieChart();
    }

    private void setupPieChart() {

        // list of pie entries
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i<rand_values.length;i++){
            pieEntries.add(new PieEntry(rand_values[i],randNames[i]));
        }

        // set dataset for piechart
        PieDataSet set = new PieDataSet(pieEntries, "");
        set.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(set);

        // make chart
        PieChart chart = (PieChart) findViewById(R.id.pieChart);
        chart.setData(data);
        chart.animateY(1000);

        //chart.setCenterTextSize(100);
        //Typeface typeface=Typeface.createFromAsset(getAssets(), "fonts/your font.ttf");
        //chart.setCenterTextTypeface(typeface);

        // set description
        Description description = new Description();
        description.setText("Value Importance Breakdown");
        chart.setDescription(description);

        // refresh
        chart.invalidate();


    }
}
