package com.example.cjignacio.tapsharky;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class howto extends AppCompatActivity {

    CustomAdapter adapter;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howto);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        adapter = new CustomAdapter (this);
        viewPager.setAdapter(adapter);
    }
}
