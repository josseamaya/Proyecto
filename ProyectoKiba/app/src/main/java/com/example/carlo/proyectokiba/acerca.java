package com.example.carlo.proyectokiba;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.volley.toolbox.NetworkImageView;

public class acerca extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String url="https://scontent-fra3-1.xx.fbcdn.net/hphotos-xlt1/v/t1.0-9/12814378_1555965911382794_3772761800038022570_n.jpg?oh=d2e05289c469be36df4e7f4b76994eba&oe=5792B343";


        NetworkImageView niv= (NetworkImageView)findViewById(R.id.imageView);
        niv.setImageUrl(url,MySingleton.getInstance(MainActivity.mContext).getImageLoader());



    }

}
