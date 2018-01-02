package com.example.orbannorbert.caradvertiser;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;


public class Advertise extends Fragment {

    Vehicle selectedvehicle;
    private String Id=null;
    TextView price;
    TextView description;
    TextView model;
    TextView type;
    TextView km;
    TextView fueltype;
    TextView numberofdors;
    TextView horsepower;
    ArrayList<String>images=new ArrayList<String>();
    TextView year;
    TextView engine;
    TextView bodytype;
    TextView phonenumber;
    TextView email;
    TextView name;
    View s;

    public void updateText(String text){
        Id=text;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_advertise, container, false);
        s=v;
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
        phonenumber=(TextView)v.findViewById(R.id.phonenumber);
        email=(TextView)v.findViewById(R.id.email);
        name=(TextView)v.findViewById(R.id.name);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        DatabaseReference veh=ref.child("vehicles");
        DatabaseReference user=ref.child("users");
        veh.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){

                    Vehicle v=ds.getValue(Vehicle.class);
                    if(v.getId()==Id)
                    {
                        price.setText("Price: "+Double.toString(v.getPrice())+" Euro");
                        description.setText("Description: "+v.getDescription());
                        model.setText("Model: "+v.getType());
                        type.setText("Type: "+v.getSubType());
                        km.setText("Km: "+Integer.toString(v.getKm()));
                        fueltype.setText("Fuel Type: "+v.getFuelType());
                        horsepower.setText("Horsepower: "+Integer.toString(v.getHorsePower()));
                        numberofdors.setText("Number Of Dors : "+Integer.toString(v.getNumberOfDoors()));
                        year.setText("Year : "+Integer.toString(v.getYear()));
                        engine.setText("Engine: "+Double.toString(v.getEngine()));
                        bodytype.setText("Body Type: "+v.getBodyType());
                        images=v.getImages();
                        initView(s);
                        selectedvehicle=v;
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("asdas","canacelled");

            }
        });
        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){

                    User v=ds.getValue(User.class);
                    if(v.getId().equals(selectedvehicle.getOwnerId()))
                    {
                        phonenumber.setText("Phone Number: "+v.getPhoneNumber());
                        name.setText("Name: "+v.getFirstName()+" "+v.getLastName());
                        email.setText("Email: "+v.getEmail() );

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

   private void initView(View v)
   {
       RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
       recyclerView.setHasFixedSize(true);
       RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
       recyclerView.setLayoutManager(layoutManager);

       ArrayList imgs = prepareData();
       imageAdapter adapter = new imageAdapter(getContext(),imgs);
       recyclerView.setAdapter(adapter);

   }

    private ArrayList prepareData(){

        ArrayList img = new ArrayList<>();
        for(int i=0;i<images.size();i++){
            ImaginesModel imgmodel = new ImaginesModel();
            imgmodel.setName(images.get(i));
            img.add(imgmodel);
        }
        return img;
    }

}
