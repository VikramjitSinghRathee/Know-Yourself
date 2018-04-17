package com.example.vrathee.myapplication;

import android.content.Context;
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

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";

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
            }
        });

        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // MAYBE THINK ABOUT REPLACING SNACKBAR WITH A DIALOG ??

                final Snackbar snackbar = Snackbar.make(v, "his is a totally random paragraph that doesnt " +
                        "actually mean anything, and is really talking about " +
                        "the random paragraph, there isnt much to worry sbout " +
                        "because its just a random paragraph, don’t worry, " +
                        "everything will be ok when random paragraph is by your side! " +
                        "Random paragraph is a completely random typed text, hense the " +
                        "name ‘random paragraph’.  "+mImageNames.get(position), Snackbar.LENGTH_INDEFINITE);
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
}
