package com.example.orbannorbert.caradvertiser;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Advertises extends Fragment {

    /*private final String android_version_names[] = {
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
*/

    private ArrayList<Vehicle> vehicles=new ArrayList<Vehicle>();
    private  View s;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_advertises, container, false);

        s=v;
        /*
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
*/
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        DatabaseReference veh=ref.child("vehicles");
        veh.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    ViewModel ve=new ViewModel();
                    Vehicle v=ds.getValue(Vehicle.class);
                    vehicles.add(v);
                    initViews(s);
                }


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("asdas","canacelled");

            }
        });


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
        for(Vehicle v : vehicles)
        {
            ViewModel androidVersion = new ViewModel();
            androidVersion.setText(v.getTitle());
            androidVersion.setDescription(v.getShortDescription());
            ArrayList<String> images=new ArrayList<>();
            images=v.getImages();
            androidVersion.setImage(images.get(0));
            android_version.add(androidVersion);
        }




        return android_version;
    }

}
