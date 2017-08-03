package com.example.peter.parkinglotapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import layout.fragment2;
import layout.fragment1;
import layout.fragment3;

public class MainActivity extends AppCompatActivity {
    String popResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);
        String username = "peter";
        String password = "password";
        Fragment defaultFrag = new fragment1();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_place,defaultFrag);
        transaction.commit();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        System.out.println("RESULT??????????@@@@@@@@@@@@@@@@@@@");
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){
                popResult = data.getStringExtra("result");
            }
        }
    }

    public void changeFragment(View view) {
        Fragment fragment;

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
    }
}

