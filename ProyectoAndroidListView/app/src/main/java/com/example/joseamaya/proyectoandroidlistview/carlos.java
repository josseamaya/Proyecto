package com.example.joseamaya.proyectoandroidlistview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
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

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;

public class carlos extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carlos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String url = "https://api.myjson.com/bins/4flfz";

        JsonObjectRequest responseCarlos = new JsonObjectRequest(
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray usuarios = response.getJSONArray("usuarios");
                            JSONObject carlos = usuarios.getJSONObject(0);

                            TextView nombre = (TextView) findViewById(R.id.textViewCarlosNombres);
                            nombre.setText(carlos.getString("nombre").toString());

                            TextView apellido = (TextView) findViewById(R.id.textViewCarlosApellidos);
                            apellido.setText(carlos.getString("apellido").toString());

                            TextView telefono = (TextView) findViewById(R.id.textViewCarlosTelefono);
                            telefono.setText(carlos.getString("telefono").toString());

                            TextView correo = (TextView) findViewById(R.id.textViewCarlosCorreo);
                            correo.setText(carlos.getString("email").toString());

                            TextView facebook = (TextView) findViewById(R.id.textViewCarlosFacebook);
                            facebook.setText(carlos.getString("facebook").toString());

                            TextView descripcion = (TextView) findViewById(R.id.textViewCarlosDesrcripcion);
                            descripcion.setText(carlos.getString("descripcion").toString());

                            NetworkImageView niv = (NetworkImageView) findViewById(R.id.imageViewCarlos);
                            String imagen = carlos.getString("imagen");
                            niv.setImageUrl("http://imgur.com/" +
                                            imagen + ".jpg",
                                    MySingleton.getInstance(MainActivity.mContext).getImageLoader());

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

        MySingleton.getInstance(this).addToRequestQueue(responseCarlos);



    }

    public void enviaSMS(int tel) {
        Uri uri = Uri.parse("smsto:" + tel);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body","");
        startActivity(it);
    }

    public void llamar(String tel) {
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel)));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void onClickLlamarCarlos(View view){
        TextView telefono=(TextView)findViewById(R.id.textViewCarlosTelefono);
        String tel=telefono.getText().toString();
        llamar(tel);
    }

    public void onClickSMSCarlos(View view){
        TextView telefono=(TextView)findViewById(R.id.textViewCarlosTelefono);
        String tel=telefono.getText().toString();
        int tel1=Integer.parseInt(tel);
        enviaSMS(tel1);
    }


}
