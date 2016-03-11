package com.example.joseamaya.proyectoandroidlistview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;



public class Jose extends AppCompatActivity {
    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jose);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mContext = this;

        String url = "http://api.kivaws.org/v1/loans/newest.json";

    }


}
