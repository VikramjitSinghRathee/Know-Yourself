package com.example.vrathee.myapplication;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Main6Activity extends AppCompatActivity {

    private static final String TAG = "Main6Activity";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls;
    //private ArrayList<String> mImageUrls = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        View parentLayout = findViewById(android.R.id.content);
        final Snackbar snackbar = Snackbar.make(parentLayout,"Touch and hold to view the definition of the " +
                "Core Value", Snackbar.LENGTH_INDEFINITE);
        View snackbarView = snackbar.getView();
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setMaxLines(20);  // show multiple line
        snackbar.setAction("CLOSE", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();

        Log.d(TAG, "onCreate: started");
        initImageBitmaps();
    }
    
    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: intiliazed bitmaps");

        String[] strs;
        strs = new String[]{ "Authenticity","Achievement","Adventure","Authority","Autonomy",
        "Balance","Beauty","Boldness","Compassion","Challenge","Citizenship","Community","Competency","Contribution",
        "Creativity","Curiosity","Determination","Fairness","Faith","Fame","Friendship","Fun","Growth","Happiness","Honesty",
        "Humor","Influence","Inner_Harmony","Justice","Kindness","Knowledge","Leadership","Learning","Love","Loyalty","Meaningful_Work",
        "Openness","Optimism","Peace","Pleasure","Poise","Popularity","Recognition","Religion","Reputation","Respect","Responsibility","Security",
        "Self_Respect","Service","Spirituality","Stability","Success","Status","Trustworthiness","Wealth","Wisdom"};

        mNames.addAll(Arrays.asList(strs));
        int size = strs.length;


        String tempUrl = "https://f1gr.hjfile.cn/pic/20170906/201709060335318628.gif";
        mImageUrls = new ArrayList<String>(Collections.nCopies(size,tempUrl));

        // LUUL YOU NEED TO HAVE INTERNET ON YOUR PHONE TO DEBUG OTHERWISE, LOAD WILL FAIL

        initRecyclerView();

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: intialized recycler view");
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames,mImageUrls,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
