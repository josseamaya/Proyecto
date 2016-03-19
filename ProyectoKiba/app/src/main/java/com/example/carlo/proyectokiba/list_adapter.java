package com.example.carlo.proyectokiba;

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
 * Created by carlo on 18/3/2016.
 */
public class list_adapter extends ArrayAdapter<JSONObject> {
    public list_adapter (Context context, int textViewResourseId){
        super(context, textViewResourseId);
    }
    public list_adapter(Context context, int resourse, List<JSONObject> items){
        super(context,resourse,items);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View celda = convertView;
        if (celda==null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            celda= layoutInflater.inflate(R.layout.list_adapter,null);
        }

        TextView id = (TextView) celda.findViewById(R.id.tvid);
        TextView nombre=(TextView) celda.findViewById(R.id.tvnombre);
        TextView categoria=(TextView) celda.findViewById(R.id.tvbalance);
        NetworkImageView niv= (NetworkImageView)celda.findViewById(R.id.imgAvatar);

        JSONObject elemento=this.getItem(position);
        try {

            String imagen=elemento.getString("id");
            int img= Integer.parseInt(imagen);
            id.setText("Id: \t"+elemento.getString("id")+"\t\tNombre: \t"+elemento.getString("shortname"));
            nombre.setText("Categoria:\t "+ elemento.getString("category")+"\nLugar:\t"+elemento.getString("whereabouts"));
            categoria.setText("Descripcion:\t "+ elemento.getString("description")+"\nMenbresia:\t"+ elemento.getString("membership_type"));
            String url = "https://www.kiva.org/img/512/"+img+".jpg";
            niv.setImageUrl(url,MySingleton.getInstance(MainActivity.mContext).getImageLoader());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return celda;
    }


}