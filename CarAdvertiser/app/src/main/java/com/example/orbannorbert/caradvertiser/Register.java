package com.example.orbannorbert.caradvertiser;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Register extends Fragment {


    ArrayList<String>vehicles=new ArrayList<>();
    Button registerButton,cancelButton;
    EditText userName,firstName, lastName, email, phoneNumber, password;
    private View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_register, container, false);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference();
        final DatabaseReference veh=ref.child("users");
        registerButton = (Button)v.findViewById(R.id.register);
        userName = (EditText)v.findViewById(R.id.user_name);
        firstName = (EditText)v.findViewById(R.id.first_name);
        lastName = (EditText)v.findViewById(R.id.last_name);
        email = (EditText)v.findViewById(R.id.email);
        phoneNumber = (EditText)v.findViewById(R.id.phone_number);

        password=(EditText)v.findViewById(R.id.password);
        cancelButton = (Button)v.findViewById(R.id.cancel);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstN = firstName.getText().toString();
                String lastN = lastName.getText().toString();
                String email_ = email.getText().toString();
                String phoneN = phoneNumber.getText().toString();
                String psw = password.getText().toString();
                //betenni firebase- be
                User user=new User();
                String key = database.getReference("todoList").push().getKey();
                user.setId(key);
                user.setEmail(email_);
                user.setFirstName(firstN);
                user.setLastName(lastN);
                user.setPhoneNumber(phoneN);
                user.setPassword(psw);
                user.setDenied(false);
                user.setVehicleIds(vehicles);
                veh.child(key).setValue(user);
                Login my = new Login();
                FragmentManager fm=getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                ft.replace(R.id.fragmentplace,my);
                ft.commit();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
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
