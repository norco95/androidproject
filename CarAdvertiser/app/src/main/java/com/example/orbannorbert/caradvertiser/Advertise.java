package com.example.orbannorbert.caradvertiser;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Advertise extends Fragment {

    private String Id=null;
    TextView price;
    TextView description;
    TextView model;
    TextView type;
    TextView km;
    TextView fueltype;
    TextView numberofdors;
    TextView horsepower;

    TextView year;
    TextView engine;
    TextView bodytype;

    public void updateText(String text){
        Id=text;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_advertise, container, false);
        price=(TextView)v.findViewById(R.id.price);
        description=(TextView)v.findViewById(R.id.description);
        model=(TextView)v.findViewById(R.id.model);
        type=(TextView)v.findViewById(R.id.type);
        km=(TextView)v.findViewById(R.id.km);
        fueltype=(TextView)v.findViewById(R.id.fueltype);
        numberofdors=(TextView)v.findViewById(R.id.numberofdors);
        horsepower=(TextView)v.findViewById(R.id.horsepower);
        year=(TextView)v.findViewById(R.id.year);
        engine=(TextView)v.findViewById(R.id.engine);
        bodytype=(TextView)v.findViewById(R.id.bodytype);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        DatabaseReference veh=ref.child("vehicles");
        veh.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for(DataSnapshot ds: dataSnapshot.getChildren()){

                    Vehicle v=ds.getValue(Vehicle.class);
                    if(v.getId()==Id)
                    {
                        price.setText("Price: "+Double.toString(v.getPrice()));
                        description.setText("Description: "+v.getDescription());
                        model.setText("Model: "+v.getType());
                        type.setText("Type: "+v.getSubType());
                        km.setText("Km: "+Integer.toString(v.getKm()));
                        fueltype.setText("Price: "+v.getFuelType());
                        horsepower.setText("Horsepower: "+Integer.toString(v.getHorsePower()));
                        numberofdors.setText("Number Of Dors : "+Integer.toString(v.getNumberOfDoors()));
                        year.setText("Year : "+Integer.toString(v.getYear()));
                        engine.setText("Engine: "+Double.toString(v.getEngine()));
                        bodytype.setText("Body Type: "+v.getBodyType());
                    }

                }


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("asdas","canacelled");

            }
        });


        return v;
    }


}
