package com.example.fwd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.Model;

import java.util.ArrayList;

public class ImageRetriveAdapter extends RecyclerView.Adapter<ImageRetriveAdapter.MyViewHolder> {

    private ArrayList<ImageUploadModel> mList;
    private Context context;


    public ImageRetriveAdapter(Context context, ArrayList<ImageUploadModel> mlist){

        this.context = context;
        this.mList = mlist;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.image_upload_layout, parent, false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with (context).load(mList.get(position).getImager()).into(holder.imageView);

        TextView textView = holder.textView;
        String text = mList.get(position).getDescription();

        textView.setText(text);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.recyclerImage);
            textView = itemView.findViewById(R.id.txtDescription);

        }
    }
}
