package com.example.orbannorbert.caradvertiser;

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Login extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    DatabaseReference veh=ref.child("users");
    View v;
    Button b1,b2;
    EditText ed1,ed2;
    TextView tx1;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_login, container, false);





        b1 = (Button)v.findViewById(R.id.login);
        ed1 = (EditText)v.findViewById(R.id.user_name);
        ed2 = (EditText)v.findViewById(R.id.password);

        b2 = (Button)v.findViewById(R.id.cancel);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = ed1.getText().toString();
                //betenni firebase- be
                final String paw=ed2.getText().toString();
                veh.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        for(DataSnapshot ds: dataSnapshot.getChildren()){

                            User usr=ds.getValue(User.class);
                            if(usr.getEmail().equals(userName)){
                                if(usr.getPassword().equals(paw))
                                {
                                    if(!usr.isDenied())
                                    {

                                        if(usr.getId()!=null&&mCallback!=null)
                                        {
                                            mCallback.respond2(usr.getId());
                                        }

                                       Advertises my=new Advertises();
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

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new Advertises();
                FragmentManager fm=getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.fragmentplace,fragment);
                ft.commit();
            }
        });


        return v;
    }


}
