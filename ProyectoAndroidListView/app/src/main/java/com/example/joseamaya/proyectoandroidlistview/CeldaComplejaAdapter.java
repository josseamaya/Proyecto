package com.example.joseamaya.proyectoandroidlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by carlo on 10/3/2016.
 */
public class CeldaComplejaAdapter extends ArrayAdapter<JSONObject> {
    public CeldaComplejaAdapter (Context context, int textViewResourseId){
        super(context, textViewResourseId);
    }
    public CeldaComplejaAdapter(Context context, int resourse, List<JSONObject> items){
        super(context,resourse,items);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View celda = convertView;
        if (celda==null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            celda= layoutInflater.inflate(R.layout.celda_compleja,null);
        }

        TextView nombre_usurio = (TextView) celda.findViewById(R.id.textViewNombreApellido);
        TextView telefono_usuario=(TextView) celda.findViewById(R.id.textViewTelefono);
        NetworkImageView niv= (NetworkImageView)celda.findViewById(R.id.networkImageViewFoto);

        JSONObject elemento=this.getItem(position);
        try {
            String imagen=elemento.getString("imagen");
            nombre_usurio.setText(elemento.getString("nombre")+elemento.getString("apellido"));
            telefono_usuario.setText(elemento.getString("telefono"));
            niv.setImageUrl("http://imgur.com/" +
                            imagen + ".jpg",
                    MySingleton.getInstance(MainActivity.mContext).getImageLoader());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return celda;
    }


}
