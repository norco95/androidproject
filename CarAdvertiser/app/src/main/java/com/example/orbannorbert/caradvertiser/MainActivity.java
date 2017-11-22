package com.example.orbannorbert.caradvertiser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advertisement);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        int image=R.drawable.kepu;
        ImageView imgView;
        imgView=(ImageView)findViewById(R.id.imageView2);

        imgView.setImageResource(image);
        ref.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("as","ad");
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("asd","cancelled");

            }
        });

    }
}
