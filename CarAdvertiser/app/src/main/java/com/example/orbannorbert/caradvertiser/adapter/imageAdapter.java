package com.example.orbannorbert.caradvertiser.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.orbannorbert.caradvertiser.ItemClickListener;
import com.example.orbannorbert.caradvertiser.R;
import com.example.orbannorbert.caradvertiser.model.ImaginesModel;

import java.util.ArrayList;

public class imageAdapter extends RecyclerView.Adapter<imageAdapter.ViewHolder> {

    private ArrayList<ImaginesModel> images;
    private Context context;
    private ItemClickListener clickListener;

    public imageAdapter(Context context,ArrayList<ImaginesModel> android_versions) {
        this.context = context;
        this.images = android_versions;
    }

    @Override
    public imageAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.images, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
       // Log.d("imgs:",images.get(i).getName());
        Glide.with(context)
                .load(images.get(i).getName())
                .override(600, 200)
                .into(viewHolder.img);
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView img;
        public ViewHolder(View view) {
            super(view);
            img = (ImageView)view.findViewById(R.id.img);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onClick(view, getAdapterPosition());
        }
    }
}
