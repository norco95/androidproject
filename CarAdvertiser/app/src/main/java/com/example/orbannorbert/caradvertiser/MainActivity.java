package com.example.orbannorbert.caradvertiser;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Button myAdvertises=(Button)findViewById(R.id.myAdvertises);
       myAdvertises.setVisibility(View.GONE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        //  int image=R.drawable.kepu;
        //  ImageView imgView;
        //  imgView=(ImageView)findViewById(R.id.imageView2);
        //  imgView.setImageResource(image);

        final Button loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                changeFragment(v);

            }
        });

        final Button advertisesButton = (Button) findViewById(R.id.advertises);
        advertisesButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
               changeFragment(v);

            }
        });




        ref.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("asd","asd");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("asd","cancelled");

            }
        });


    }





    public void changeFragment(View view)
    {
        Fragment fragment;

        View f= findViewById(R.id.fragmentplace);

        if(view==findViewById(R.id.login))
        {
            fragment = new Login();
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.fragmentplace,fragment);
            ft.commit();
        }
        if(view==findViewById(R.id.advertises))
        {
            fragment = new Advertises();
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();

            ft.replace(R.id.fragmentplace,fragment);

            ft.commit();
        }


    }



}
