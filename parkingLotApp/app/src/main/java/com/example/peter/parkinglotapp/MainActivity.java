package com.example.peter.parkinglotapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

import layout.fragment1;
import layout.fragment2;
import layout.fragment3;
import layout.fragment4;
import layout.fragment5;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);
        String username = "peter";
        String password = "password";
    }

    public void changeFragment(View view) {
        Fragment fragment;
        Fragment oldFrag;

        if (view == findViewById(R.id.button1)) {
            fragment = new fragment1();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_place,fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
        if (view == findViewById(R.id.button2)) {
            fragment = new fragment2();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_place,fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
        if (view == findViewById(R.id.button3)) {
            fragment = new fragment3();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_place,fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
        if (view == findViewById(R.id.button4)) {
            fragment = new fragment4();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_place,fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
        if (view == findViewById(R.id.button5)) {
            fragment = new fragment5();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_place,fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}

