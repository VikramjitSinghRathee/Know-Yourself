package com.example.vrathee.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;



import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();
    private Context mcontext; // need default constructor for the RecyclerViewAdapter which is below.

    public RecyclerViewAdapter(ArrayList<String> mImageNames, ArrayList<String> mImage, Context mcontext) {
        this.mImageNames = mImageNames;
        this.mImage = mImage;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_list,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called"); // should print corresponding to number of items.
        
        Glide.with(mcontext)
                .asBitmap()
                .load(mImage.get(position))
                .into(holder.image);

        holder.imagename.setText(mImageNames.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on " + mImageNames.get(position));
                Toast.makeText(mcontext, mImageNames.get(position),Toast.LENGTH_SHORT).show();

            // UPDATING OF THE DATA AFTER CLICKING ON THE VIEW

                // this is the date generation for naming the file
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c);



                // OBTAINING OLD DATA AND SAVING THE NEW ONE
                sharedPreferences = mcontext.getSharedPreferences("value_scale", Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();

                // we have to remove these so that old data is not deleted
                //editor.clear();
                //editor.apply();

                Toast.makeText(mcontext,"the sharedpreference name is "+"value_scale", Toast.LENGTH_LONG).show();
                int old_scale_value = sharedPreferences.getInt(mImageNames.get(position),0);
                setSharedPreferences(mImageNames.get(position), old_scale_value);

                // FOR TESTING PURPOSES
                Toast.makeText(mcontext,"Data saved", Toast.LENGTH_LONG).show();
                int cur_value = sharedPreferences.getInt(mImageNames.get(position),0);
                Toast.makeText(mcontext,"the data loaded is "+String.valueOf(cur_value), Toast.LENGTH_LONG).show();
            }
        });

        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Resources res = mcontext.getResources();
                String valueNameInfo = mImageNames.get(position);

                // deftype string looks for name in the strings.xml. this is important, for drawable it's drawable
                int resID = mcontext.getResources().getIdentifier(valueNameInfo,"string", mcontext.getPackageName());
                String infotext = res.getString(resID)+ mImageNames.get(position);;

                // we can get correct info for the position clicked by editing strings.xml and
                // naming each string in strings.xml corresponding to mImageNames.get(position)

                final Snackbar snackbar = Snackbar.make(v,infotext, Snackbar.LENGTH_INDEFINITE);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(20);  // show multiple line
                snackbar.setAction("ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
                return true;
            }
        });

        // for info need to load correct info from xml file
        // for time IMPLEMENT SOMETHING SINCE UTC
        // FOR THE NAME OF THE VALUE LIST IMPLEMENT SINGLE NAME INSTEAD OF FORMATTED DATE NAME
        // 1. for now open shared preference here single file and keep appending data to it based on clicks
        // 2. after 1 week (make a code to check maybe have it in the name of the file also), delete the old file and create a new file
        // 3. now all the keys are 0 and data from clicks are getting appended to that until next week.
        // 4. load the top 5 keys from the shared pref and plot the pie chart
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView imagename;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imagename = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }

    public void setSharedPreferences(String name, int old_scale_value){
        editor = sharedPreferences.edit();
        //editor.clear();
        int scale_value;
        if(old_scale_value != 0)
        {
            scale_value =  old_scale_value + 5;
            editor.putInt(name,scale_value);
        }
        else
        {
            editor.putInt(name, 5);
        }
        //editor.commit();
        editor.apply();
    }
}
