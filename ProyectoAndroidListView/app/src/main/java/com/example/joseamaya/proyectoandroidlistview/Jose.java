package com.example.joseamaya.proyectoandroidlistview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.joseamaya.proyectoandroidlistview.R.id.textViewJoseNombre;


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
