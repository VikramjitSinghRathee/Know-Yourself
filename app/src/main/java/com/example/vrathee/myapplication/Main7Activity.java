package com.example.vrathee.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main7Activity extends AppCompatActivity {

    float rand_values[] = {98.8f,123.8f,161.6f,24.2f,52f};
    String randNames[] = {"swag","mvp","bling","trill","best"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        setupPieChart();
    }

    private void setupPieChart() {

        // list of pie entries
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i<rand_values.length;i++){
            pieEntries.add(new PieEntry(rand_values[i],randNames[i]));
        }

        // set dataset for piechart
        PieDataSet set = new PieDataSet(pieEntries, "Test Chart");
        set.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(set);

        // make chart
        PieChart chart = (PieChart) findViewById(R.id.pieChart);
        chart.setData(data);
        chart.animateY(1000);

        // set description
        Description description = new Description();
        description.setText("GUCCI GANG");
        chart.setDescription(description);

        // refresh
        chart.invalidate();


    }
}
