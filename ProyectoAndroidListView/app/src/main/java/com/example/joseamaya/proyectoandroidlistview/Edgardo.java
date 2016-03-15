package com.example.joseamaya.proyectoandroidlistview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Edgardo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edgardo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        String url="https://api.myjson.com/bins/4flfz";

        JsonObjectRequest responseEdgardo=new JsonObjectRequest(url,
                new Response.Listener< JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray usuarios=response.getJSONArray("usuarios");
                            JSONObject edgardo=usuarios.getJSONObject(0);

                            TextView nombres=(TextView)findViewById(R.id.textViewEdgardoNombres);
                            nombres.setText(edgardo.getString("nombre").toString());
                            TextView apellidos=(TextView)findViewById(R.id.textViewEdgardoApellidos);
                            apellidos.setText(edgardo.getString("apellido").toString());
                            TextView telefono=(TextView)findViewById(R.id.textViewEdgardoTelefono);
                            telefono.setText(edgardo.getString("telefono").toString());
                            TextView correo=(TextView)findViewById(R.id.textViewEdgardoCorreo);
                            correo.setText(edgardo.getString("email").toString());
                            TextView facebook=(TextView)findViewById(R.id.textViewEdgardoFacebook);
                            facebook.setText(edgardo.getString("facebook").toString());
                            TextView descripcion=(TextView)findViewById(R.id.textViewEdgardoDescripcion);
                            descripcion.setText(edgardo.getString("descripcion").toString());
                            NetworkImageView niv=(NetworkImageView)findViewById(R.id.imageViewEdgardo);
                            String image=edgardo.getString("imagen");
                            niv.setImageUrl("http://imgur.com/"+image+".jpg",MySingleton.getInstance(MainActivity.mContext).getImageLoader());

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){

                    }
                }
        );

        MySingleton.getInstance(this).addToRequestQueue(responseEdgardo);
    }

}
