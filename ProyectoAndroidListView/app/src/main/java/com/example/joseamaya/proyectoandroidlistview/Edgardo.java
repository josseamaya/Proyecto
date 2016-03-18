package com.example.joseamaya.proyectoandroidlistview;

import android.Manifest;
import android.content.Context;
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

public class Edgardo extends AppCompatActivity {
    String LlamarEdgardoTelefono = "";

    private static Context mContext;

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
                            JSONObject edgardo=usuarios.getJSONObject(2);


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

                            NetworkImageView FotoEdgardo=(NetworkImageView)findViewById(R.id.imageViewEdgardo);
                            String image=edgardo.getString("imagen");
                            FotoEdgardo.setImageUrl("http://imgur.com/"+image+".jpg",MySingleton.getInstance(MainActivity.mContext).getImageLoader());

                            LlamarEdgardoTelefono=edgardo.getString("telefono");



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

   public void onClickLlamarEdgardo(View v){
       Intent IntentLamar=new Intent(Intent.ACTION_CALL);
       IntentLamar.setData(Uri.parse(LlamarEdgardoTelefono));
       if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        startActivity(IntentLamar);

    }

    public void onClickSMSEdgardo (View v){
        Intent IntentSMS= new Intent(Intent.ACTION_VIEW);
        IntentSMS.setType("vnd.android-dir/mms-sms");
        IntentSMS.putExtra("address", LlamarEdgardoTelefono);
        IntentSMS.putExtra("sms_body", "");
        startActivity(IntentSMS);

    }

    public void onClickWhatsappEdgardo(View v){
        Uri uri=Uri.parse("SMSTo:"); //Falta agregar el telefono (duda si hay que agregar el telefono directamente(con numeros Ej. 88136776)
        //o con la variable LLamarEdgardoTelefono ya arratra el telefono// )
        Intent Whatsapp=new Intent(Intent.ACTION_SENDTO,uri);
        Whatsapp.putExtra("sms_body","");
        Whatsapp.setPackage("com.whatsapp");
        startActivity(Whatsapp);

    }

    public void onClickFacebookEdgardo(View v){
        String linkFacebook="https://www.facebook.com/edgardo.e.espana";
        Intent intentFacebook=null;
        intentFacebook=new Intent(intentFacebook.ACTION_VIEW,Uri.parse(linkFacebook));
        startActivity(intentFacebook);

    }
}

