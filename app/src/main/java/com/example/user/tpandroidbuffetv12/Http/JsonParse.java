package com.example.user.tpandroidbuffetv12.Http;

import android.util.Log;

import com.example.user.tpandroidbuffetv12.model.Producto;
import com.example.user.tpandroidbuffetv12.model.Usuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alumno on 01/06/2017.
 */

public class JsonParse {

    public void listaProductos(String json){
        List<Producto> lista = new ArrayList<>();
        try {
            JSONArray productos = new JSONArray(json);
            for(int i = 0; i<productos.length();i++) {
                JSONObject jsonObject = productos.getJSONObject(i);
                String nombre = jsonObject.getString("nombre");
                String tipoMenu = jsonObject.getString("tipoMenu");
                double precio = jsonObject.getDouble("precio");
                String imagen = jsonObject.getString("imagen");
                Log.d("tipoMenu", tipoMenu);
                switch (tipoMenu) {
                    case "Snack":
                        Producto.getStaticListSnacks().add(new Producto(nombre, tipoMenu, precio, imagen));
                        break;
                    case "Principal":
                        Producto.getStaticListMenus().add(new Producto(nombre, tipoMenu, precio, imagen));
                        break;
                    case "Bebida":
                        Producto.getStaticListBebidas().add(new Producto(nombre, tipoMenu, precio, imagen));
                        break;
                }
            }
        } catch (Exception e) {
            Log.d("ERROR", e.getMessage());
        }
    }

    public static Boolean validarUsuario(String str) {
        Boolean rta = false;
        Log.d("MORNIN", str);
        try {
            JSONObject jsonObject = new JSONObject(str.substring(str.indexOf("{"), str.lastIndexOf("}") + 1));

            if(jsonObject.getInt("codigo") == 200) {
                rta = true;
            }
            if(jsonObject.getInt("codigo") == 400) {
                rta = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("ERROR: ", e.getMessage().toString());
        }
        return rta;
    }
}
