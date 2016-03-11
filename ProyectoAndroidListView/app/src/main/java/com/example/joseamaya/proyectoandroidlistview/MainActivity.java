package com.example.joseamaya.proyectoandroidlistview;

import android.content.Context;
import android.location.GpsStatus;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext=this;


    }
    public void getUsuarios (){
        final Context context=this;
        JsonObjectRequest jor= new JsonObjectRequest(
                "https://api.myjson.com/bins/4flfz",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray usuarios = response.getJSONArray("usuarios");
                            ArrayList<JSONObject> dataSourse = new ArrayList<>();
                            for (int i = 0; i < usuarios.length(); i++) {
                                dataSourse.add(usuarios.getJSONObject(i));
                            }
                            //celdaComplejaAdapter adapter = new celdaComplejaAdapter(context, 0, dataSourse);
                           // ((ListView) findViewById(R.id.listViewUsuarios)).setAdapter(adapter);
                            //NECESITO EL celaComplejaAdapter.java y el celda_compleja.xml CHICOS!!!!
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //algun mensaje de error
                    }
                }
        );

        MySingleton.getInstance(mContext).addToRequestQueue(jor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
