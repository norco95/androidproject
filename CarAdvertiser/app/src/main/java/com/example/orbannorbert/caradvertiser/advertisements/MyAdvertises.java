package com.example.orbannorbert.caradvertiser.advertisements;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.orbannorbert.caradvertiser.Communicator;
import com.example.orbannorbert.caradvertiser.adapter.DataAdapter;
import com.example.orbannorbert.caradvertiser.ItemClickListener;
import com.example.orbannorbert.caradvertiser.MainActivity;
import com.example.orbannorbert.caradvertiser.R;
import com.example.orbannorbert.caradvertiser.model.User;
import com.example.orbannorbert.caradvertiser.model.Vehicle;
import com.example.orbannorbert.caradvertiser.model.ViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyAdvertises extends Fragment implements ItemClickListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    DatabaseReference veh=ref.child("vehicles");
    DatabaseReference user=ref.child("users");
    User owner=new User();
    String loginid;
    String id;
    Vehicle v;
    public void setLoginId(String id)
    {
        loginid=id;
    }
    DataAdapter adapter;
    private ArrayList<Vehicle> vehicles=new ArrayList<Vehicle>();
    private  View s;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_my_advertises, container, false);
        s=v;
        id=((MainActivity)getActivity()).getLoginId();
            veh.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        Vehicle v=ds.getValue(Vehicle.class);
                        if(v.getOwnerId()!=null) {
                            if (v.getOwnerId().equals(id)) {
                                if (!v.isSold()) {
                                    vehicles.add(v);
                                }
                            }
                        }
                    }
                    initViews(s);

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
        if(getActivity()!=null) {
            if (getActivity().getApplicationContext() != null) {
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
                recyclerView.setLayoutManager(layoutManager);
                ArrayList<ViewModel> androidVersions = prepareData();
                adapter = new DataAdapter(getActivity().getApplicationContext(), androidVersions);
                recyclerView.setAdapter(adapter);
                adapter.setClickListener(this);
            }
        }
    }

    Communicator mCallback;

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (Communicator) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement TextClicked");
        }
    }

    @Override
    public void onDetach() {
        mCallback = null; // => avoid leaking, thanks @Deepscorn
        super.onDetach();
    }

    @Override
    public void onClick(View view,int position) {
        mCallback.respond1(vehicles.get(position).getId());
    }

    private ArrayList<ViewModel> prepareData(){
        ArrayList<ViewModel> android_version = new ArrayList<>();
        for(Vehicle v : vehicles) {
            ViewModel androidVersion = new ViewModel();
            androidVersion.setText(v.getTitle());
            androidVersion.setDescription(v.getShortDescription());
            ArrayList<String> images=new ArrayList<>();
            images=v.getImages();
            androidVersion.setImage(images.get(0));
            androidVersion.setPrice(v.getPrice());
            android_version.add(androidVersion);
        }
        return android_version;
    }
}
