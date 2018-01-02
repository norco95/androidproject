package com.example.orbannorbert.caradvertiser;


/**
 * Created by OrbanNorbert on 2017. 11. 23..
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;



public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<ViewModel> android;
    private Context context;
    private ViewHolder mviewHolder;
    private ItemClickListener clickListener;


    public DataAdapter(Context context,ArrayList<ViewModel> android) {
        this.android = android;
        this.context = context;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.advertisement, viewGroup, false);
        mviewHolder= new ViewHolder(view);
        return mviewHolder;
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {



       // String uri=storref.child("images/kepu.jpg").getDownloadUrl();
       //Log.d("url:",uri);
        //android.get(i).getImage();
      // StorageReference sr= storref.child("images/"+ android.get(i).getImage());


        viewHolder.tv_android.setText(android.get(i).getText());
        Glide.with(context)
                .load(android.get(i).getImage())
                .override(800, 800)
                .into(viewHolder.img_android);
       // viewHolder.img_android.setImageResource(R.drawable.kepu);
        viewHolder.description.setText(android.get(i).getDescription());
        viewHolder.price.setText(Double.toString(android.get(i).getPrice())+" EURO");
    }

    @Override
    public int getItemCount() {
        return android.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        private TextView tv_android;
        private ImageView img_android;
        private TextView description;
        private TextView price;
        public ViewHolder(View view) {
            super(view);
            description=(TextView)view.findViewById(R.id.tv_description);
            tv_android = (TextView)view.findViewById(R.id.tv_android);
            img_android = (ImageView) view.findViewById(R.id.img_android);
            price=(TextView) view.findViewById(R.id.price);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onClick(view, getAdapterPosition());
        }
    }

}