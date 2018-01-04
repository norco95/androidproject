package com.example.orbannorbert.caradvertiser.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.orbannorbert.caradvertiser.MainActivity;
import com.example.orbannorbert.caradvertiser.R;
import com.example.orbannorbert.caradvertiser.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * This class provides handling a user's profile.
 */
public class Profile extends Fragment {

    TextView firstName, lastName, email, phoneNumber, password;
    View v;
    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        firstName = (TextView)v.findViewById(R.id.first_name);
        lastName = (TextView)v.findViewById(R.id.last_name);
        email = (TextView)v.findViewById(R.id.email);
        phoneNumber = (TextView)v.findViewById(R.id.phone_number);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        DatabaseReference users=ref.child("users");
        id=((MainActivity)getActivity()).getLoginId();

        /**
         * Change user's data.
         */
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    User usr=ds.getValue(User.class);
                    if(usr.getId().equals(id)) {
                        firstName.setText("First name: " + usr.getFirstName());
                        lastName.setText("Last name: " + usr.getLastName());
                        email.setText("Email: " + usr.getEmail());
                        phoneNumber.setText("Phone number: " +usr.getPhoneNumber());
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
