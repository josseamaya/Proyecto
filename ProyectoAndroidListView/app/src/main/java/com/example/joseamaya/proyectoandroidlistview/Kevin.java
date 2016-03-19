package com.example.joseamaya.proyectoandroidlistview;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Network;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Kevin extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kevin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        String urlJson = "https://api.myjson.com/bins/4flfz";


        JsonObjectRequest myResponse = new JsonObjectRequest(
                urlJson,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray usuarios = response.getJSONArray("usuarios");
                            JSONObject me = usuarios.getJSONObject(4);

                            TextView nombre = (TextView) findViewById(R.id.txtNombre);
                            TextView apellido = (TextView) findViewById(R.id.txtApellido);
                            TextView tel = (TextView) findViewById(R.id.txtTel);
                            TextView correo = (TextView) findViewById(R.id.txtCorreo);
                            TextView face = (TextView) findViewById(R.id.txtFace);
                            TextView descripcion = (TextView) findViewById(R.id.txtDescripcion);

                            nombre.setText(me.getString("nombre").toString());

                            apellido.setText(me.getString("apellido").toString());

                            tel.setText(me.getString("telefono").toString());

                            correo.setText(me.getString("email").toString());

                            face.setText(me.getString("facebook").toString());

                            descripcion.setText(me.getString("descripcion").toString());

                            NetworkImageView netImageKavx = (NetworkImageView) findViewById(R.id.NetImage);
                            String imagenId = me.getString("imagen");
                            netImageKavx.setImageUrl("http://imgur.com/" +
                                            imagenId + ".jpg",
                                    MySingleton.getInstance(MainActivity.mContext).getImageLoader());

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.mContext, "No se pudo realizar el Request!", Toast.LENGTH_LONG).show();

                    }
                }

        );

        MySingleton.getInstance(this).addToRequestQueue(myResponse);




        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

   private String getKevinTel (){

       TextView tel = (TextView) findViewById(R.id.txtTel);

        String num = tel.getText().toString();

       return num;
   }

    public void onClickFaceKevin(View view) throws Exception 	{

 //       TextView face = (TextView) findViewById(R.id.txtFace);

 //       String enlace = face.getText().toString();

        String enlace = "https://www.facebook.com/kavx1001";
        Intent intent = null;
        intent = new Intent(intent.ACTION_VIEW,Uri.parse(enlace));
        startActivity(intent);
    }

    public void onclickSmsKevin (View sms) {

        Uri uri = Uri.parse("smsto:" + getKevinTel());
        Intent enviaSms = new Intent(Intent.ACTION_SENDTO, uri);
        enviaSms.putExtra("sms_body","");
        startActivity(enviaSms);

    }

    public void onClickWatsAppKevin (View v)
    {
        Uri uri = Uri.parse("smsto:" + 98408763);
        Intent wp = new Intent(Intent.ACTION_SENDTO, uri);
        wp.putExtra("sms_body", "");
        wp.setPackage("com.whatsapp");
        startActivity(wp);
    }

    public void onClickLlamarKevin(View v) {

        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getKevinTel())));
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Kevin Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.joseamaya.proyectoandroidlistview/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Kevin Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.joseamaya.proyectoandroidlistview/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
