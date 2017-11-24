package com.example.orbannorbert.caradvertiser;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Advertises extends Fragment {

    private final String android_version_names[] = {
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat",
            "Lollipop",
            "Marshmallow"
    };

    private final String android_image_urls[] = {
            "description1 djsakjdhaskjdhkajhdkashdkjsahdk",
            "description2 djsakjdhaskjdhkajhdkashdkjsahdk",
            "description3 djsakjdhaskjdhkajhdkashdkjsahdk",
            "description4 djsakjdhaskjdhkajhdkashdkjsahdk",
            "description5 djsakjdhaskjdhkajhdkashdkjsahdk",
            "description6 djsakjdhaskjdhkajhdkashdkjsahdk",
            "description7 djsakjdhaskjdhkajhdkashdkjsahdk",
            "description8 djsakjdhaskjdhkajhdkashdkjsahdk",
            "description9 djsakjdhaskjdhkajhdkashdkjsahdk",
            "description10 djsakjdhaskjdhkajhdkashdkjsahdk"
    };

    View s;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_advertises, container, false);
        s=v;
        RecyclerView mRecyclerView= (RecyclerView)v.findViewById(R.id.card_recycler_view);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if(dy > 0) //check for scroll down
                {
                    LinearLayoutCompat l=(LinearLayoutCompat) s.findViewById(R.id.searchLayout);

                    l.setVisibility(s.INVISIBLE);
                    l.setVisibility(s.GONE);

                }
                else
                {
                    LinearLayoutCompat l=(LinearLayoutCompat) s.findViewById(R.id.searchLayout);
                    l.setVisibility(s.VISIBLE);
                }

            }
        });


        initViews(v);
        return v;
    }


    private void initViews(View v){
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(),1);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<ViewModel> androidVersions = prepareData();
        DataAdapter adapter = new DataAdapter(getActivity().getApplicationContext(),androidVersions);
        recyclerView.setAdapter(adapter);

    }
    private ArrayList<ViewModel> prepareData(){

        ArrayList<ViewModel> android_version = new ArrayList<>();
        for(int i=0;i<android_version_names.length;i++){
            ViewModel androidVersion = new ViewModel();
            androidVersion.setText(android_version_names[i]);
            androidVersion.setDescription(android_image_urls[i]);
            androidVersion.setImage("kep.png");
            android_version.add(androidVersion);
        }
        return android_version;
    }

}
