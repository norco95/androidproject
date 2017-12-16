package com.example.orbannorbert.caradvertiser;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements Communicator{

    private FragmentManager fm=getSupportFragmentManager();
    private String loginId;
    Button addadvertise;
    Button advertisesButton;
    Button loginButton;
    Button myAdvertises;
    Button logout;
    Button profile;
    Button register;

    @Override
    public void respond(String text) {


        FragmentTransaction ft=fm.beginTransaction();
        Advertise add=new Advertise();
        add.updateText(text);
        ft.replace(R.id.fragmentplace,add);
        ft.commit();


    }

    @Override
    public void respond2(String text)
    {
        loginId=text;
        setMenu();
    }

    public String getLoginId(){
        return loginId;
    }

    @Override
    public void respond1(String text) {


        FragmentTransaction ft=fm.beginTransaction();
        AddAdvertisement add=new AddAdvertisement();
        add.updateText(text);
        ft.replace(R.id.fragmentplace,add);
        ft.commit();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAdvertises=(Button)findViewById(R.id.myAdvertises);
        myAdvertises.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                changeFragment(v);

            }
        });

        myAdvertises.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                changeFragment(v);

            }
        });

        profile=(Button)findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                    changeFragment(v);

            }
        });

        logout=(Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                loginId=null;
                setMenu();

            }
        });


        //  int image=R.drawable.kepu;
        //  ImageView imgView;
        //  imgView=(ImageView)findViewById(R.id.imageView2);
        //  imgView.setImageResource(image);

        loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                changeFragment(v);

            }
        });

        advertisesButton = (Button) findViewById(R.id.advertises);
        advertisesButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                changeFragment(v);

            }
        });

        addadvertise=(Button)findViewById(R.id.add);
        addadvertise.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                changeFragment(v);

            }
        });
        register=(Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                changeFragment(v);

            }
        });
        setMenu();


    }

    private void setMenu()
    {
        if(loginId!=null)
        {
            loginButton.setVisibility(View.GONE);
            addadvertise.setVisibility(View.VISIBLE);
            myAdvertises.setVisibility(View.VISIBLE);
            profile.setVisibility(View.VISIBLE);
            logout.setVisibility(View.VISIBLE);
            register.setVisibility(View.GONE);
        }
        else
        {
            register.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.VISIBLE);
            myAdvertises.setVisibility(View.GONE);
            addadvertise.setVisibility(View.GONE);
            profile.setVisibility(View.GONE);
            logout.setVisibility(View.GONE);
        }
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
        if(view==findViewById(R.id.add))
        {
            fragment = new AddAdvertisement();
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.fragmentplace,fragment);
            ft.commit();
        }

        if(view==findViewById(R.id.myAdvertises))
        {
            MyAdvertises my = new MyAdvertises();
            my.setLoginId(loginId);
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.fragmentplace,my);
            ft.commit();
        }
        if(view==findViewById(R.id.register))
        {
            Register my = new Register();
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.fragmentplace,my);
            ft.commit();
        }
        if(view==findViewById(R.id.profile))
        {
            Profile my = new Profile();
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.fragmentplace,my);
            ft.commit();
        }

        setMenu();

    }



}
