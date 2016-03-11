package com.example.joseamaya.proyectoandroidlistview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class Jose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jose);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String url = "https://api.myjson.com/bins/4flfz";
        getUsuarios(url);

    }

    public void getUsuarios (String url){
        final Context context=this;

        JsonObjectRequest jor= new JsonObjectRequest(
                url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String usuarios = null;
                        try {
                            usuarios = response.getString("usuarios");
                            JSONArray arregloUsuarios = new JSONArray(usuarios);
                            JSONObject perfil = (JSONObject) arregloUsuarios.get(3);
                            String joseNombre = perfil.getString("nombre");
                            String joseApellido = perfil.getString("apellido");
                            String joseDescripcion = perfil.getString("descripcion");
                            String joseTelefono = perfil.getString("telefono");
                            String joseCorreo = perfil.getString("email");
                            String imagen = perfil.getString("imagen");


                            TextView tvJoseNombre=(TextView)findViewById(R.id.textViewJoseNombre);
                            tvJoseNombre.setText(joseNombre);

                            TextView tvJoseApellido=(TextView)findViewById(R.id.textViewJoseApellido);
                            tvJoseApellido.setText(joseApellido);

                            TextView tvJoseDescripcion=(TextView)findViewById(R.id.textViewJoseDescripcion);
                            tvJoseDescripcion.setText(joseDescripcion);

                            TextView tvJoseTelefono=(TextView)findViewById(R.id.textViewJoseTelefono);
                            tvJoseTelefono.setText(joseTelefono);

                            TextView tvJoseCorreo=(TextView)findViewById(R.id.textViewJoseCorreo);
                            tvJoseCorreo.setText(joseCorreo);

                            NetworkImageView avatar = (NetworkImageView) findViewById(R.id.networkImageViewJose);
                            avatar.setImageUrl("http://imgur.com/" + imagen + ".jpg", MySingleton.getInstance(context).getImageLoader());


                        } catch (JSONException e) {
                            e.printStackTrace();
                            TextView tvJoseNombre=(TextView)findViewById(R.id.textViewJoseNombre);
                            tvJoseNombre.setText("error");

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

        MySingleton.getInstance(this).addToRequestQueue(jor);
    }


}
