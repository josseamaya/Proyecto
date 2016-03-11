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
    private 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jose);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mContext=this;

    }
    public void kivaLlenarInformacion(){
        String url = "http://api.kivaws.org/v1/loans/newest.json";
    public void kivaLlenarList(){
        String url = "http://api.kivaws.org/v1/loans/newest.json";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        String loans = null;
                        try {
                            loans = response.getString("loans");
                            JSONArray arregloPersonas = new JSONArray(loans);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });
        MySingleton.getInstance(MainActivity.mContext).addToRequestQueue(jsObjRequest);
    }

    }


}
