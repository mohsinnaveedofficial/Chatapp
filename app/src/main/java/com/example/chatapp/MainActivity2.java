package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

import com.example.chatapp.adapter.fragmentadapter;
import com.example.chatapp.frag.Fragment1;
import com.example.chatapp.frag.Fragment2;
import com.example.chatapp.frag.Fragment3;
import com.google.android.material.tabs.TabLayout;

public class MainActivity2 extends AppCompatActivity {
    Button camera, serach, menu;
    TabLayout tab;
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        camera = findViewById(R.id.camera);
        serach = findViewById(R.id.serach);
        menu = findViewById(R.id.menu);
        viewpager = findViewById(R.id.viewpager);
        tab = findViewById(R.id.tab);

        fragmentadapter fragmentadapter=new fragmentadapter(getSupportFragmentManager(),getApplicationContext());

          fragmentadapter.addfragment(new Fragment1(),"Chats");
          fragmentadapter.addfragment(new Fragment2(),"Status");
          fragmentadapter.addfragment(new Fragment3(),"Ca lls");
          viewpager.setAdapter(fragmentadapter);
          tab.setupWithViewPager(viewpager);

    }



}