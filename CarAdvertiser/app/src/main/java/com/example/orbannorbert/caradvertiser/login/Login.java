package com.example.orbannorbert.caradvertiser.login;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.orbannorbert.caradvertiser.advertisements.Advertises;
import com.example.orbannorbert.caradvertiser.Communicator;
import com.example.orbannorbert.caradvertiser.R;
import com.example.orbannorbert.caradvertiser.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * This class is responsible for login.
 * It makes a simple login with user name and password
 */
public class Login extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();
    DatabaseReference vehiclesNode = databaseReference.child("users");
    View view;
    Button b1,b2;
    EditText ed1,ed2;
    TextView tx1;
    Communicator mCallback;

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            mCallback = (Communicator) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement TextClicked");
        }
    }

    @Override
    public void onDetach() {
        mCallback = null; // avoid leaking
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);
        b1 = (Button)view.findViewById(R.id.login);
        ed1 = (EditText)view.findViewById(R.id.user_name);
        ed2 = (EditText)view.findViewById(R.id.password);
        b2 = (Button)view.findViewById(R.id.cancel);

        /**
         * Button login was pressed. The user makes a login.
         */
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userName = ed1.getText().toString();
                final String paw=ed2.getText().toString();
                vehiclesNode.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                            User usr = ds.getValue(User.class);
                            if(usr.getEmail().equals(userName)){
                                if(usr.getPassword().equals(paw)) {
                                    if(!usr.isDenied()) {
                                        if(usr.getId() != null && mCallback != null) {
                                            mCallback.respond2(usr.getId());
                                        }
                                        Advertises my = new Advertises();
                                        FragmentManager fm;
                                        if(getActivity()!=null) {
                                            fm = getActivity().getSupportFragmentManager();
                                            FragmentTransaction ft = fm.beginTransaction();
                                            ft.replace(R.id.fragmentplace, my);
                                            ft.commit();
                                        }
                                    }
                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("asdas","canacelled");
                    }
                });
            }
        });

        /**
         * Button cancel was pressed. Change fragment.
         */
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Advertises();
                FragmentManager fm=getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.fragmentplace,fragment);
                ft.commit();
            }
        });
        return view;
    }
}
