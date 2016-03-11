package com.example.joseamaya.proyectoandroidlistview;

import android.content.Context;
import android.content.Intent;
import android.location.GpsStatus;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
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
    Integer cont=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext=this;
        String url="https://api.myjson.com/bins/4flfz";
        getUsuarios(url);

        ListView lv2=(ListView) findViewById(R.id.listViewUsuarios);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cont = position;
                if (position == 0) // carlos
                {
                    Intent intent=new Intent(mContext,carlos.class);
                    startActivity(intent);

                }
                if (position == 1)//daniel
                {

                }
                if (position == 2)//edgardo
                {

                }
                if (position == 3)//jose
                {
                    Intent intent = new Intent(mContext, Jose.class);
                    startActivity(intent);
                }
                if (position == 4)//kevin
                {

                }


            }
        });


    }
    public void getUsuarios (String url){
        final Context context=this;

        JsonObjectRequest jor= new JsonObjectRequest(
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray usuarios = response.getJSONArray("usuarios");
                            ArrayList<JSONObject> dataSourse = new ArrayList<>();
                            for (int i = 0; i < usuarios.length(); i++) {
                                dataSourse.add(usuarios.getJSONObject(i));
                            }
                            TextView tvprueba=(TextView)findViewById(R.id.textViewPrueba);
                            tvprueba.setText(usuarios.toString());

                            CeldaComplejaAdapter adapter = new CeldaComplejaAdapter(context, 0, dataSourse);
                           ((ListView) findViewById(R.id.listViewUsuarios)).setAdapter(adapter);

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
