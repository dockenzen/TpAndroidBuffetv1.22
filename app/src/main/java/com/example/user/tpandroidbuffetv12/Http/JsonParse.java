package com.example.user.tpandroidbuffetv12.Http;

import android.util.Log;

import com.example.user.tpandroidbuffetv12.model.Pedido;
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

        if(Producto.getStaticListSnacks().size() == 0 && Producto.getStaticListMenus().size() == 0 && Producto.getStaticListBebidas().size() == 0) {
            try {
                JSONArray productos = new JSONArray(json);
                for (int i = 0; i < productos.length(); i++) {
                    JSONObject jsonObject = productos.getJSONObject(i);
                    String nombre = jsonObject.getString("nombre");
                    String tipoMenu = jsonObject.getString("tipoMenu");
                    double precio = jsonObject.getDouble("precio");
                    String imagen = jsonObject.getString("imagen");
                    //Log.d("tipoMenu", tipoMenu);
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

    public String toJsonPedido(Pedido pedido) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        try {

            for (Producto prod: pedido.getLista()) {
                JSONObject jProducto = new JSONObject();
                jProducto.put("tipoMenu",prod.getTipoMenu());
                jProducto.put("nombre",prod.getNombre());
                jProducto.put("precio",prod.getPrecio());
                jProducto.put("cantidad",prod.getCantidad());
                jProducto.put("imagen",prod.getUrlImagen());
                jsonArray.put(jProducto);
            }
            jsonObject.put("usuario",Usuario.user.getEmail());
            jsonObject.put("listaProductos", jsonArray);
            jsonObject.put("total", pedido.getTotal());
        } catch (Exception e) {
            Log.d("json", e.toString());
        }
        return jsonObject.toString();
    }

    public String toJsonUser(Usuario user) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nombre",user.getNombre());
            jsonObject.put("apellido",user.getApellido());
            jsonObject.put("dni",user.getDNI());
            jsonObject.put("mail",user.getEmail());
            jsonObject.put("clave",user.getContraseña());
        } catch (Exception e) {
            Log.d("json", e.toString());
        }
        return jsonObject.toString();
    }

    public void getUserFromEmail(String userData){
        Usuario user = null;
        try {
            JSONArray array = new JSONArray(userData);

            for (int i = 0; i < array.length() ; i++){
                JSONObject jsonObject= array.getJSONObject(i);
                String nombre = jsonObject.getString("nombre");
                String apellido = jsonObject.getString("apellido");
                String dni = jsonObject.getString("dni");
                String email = jsonObject.getString("mail");
                String clave = jsonObject.getString("clave");
                user = new Usuario(nombre,apellido,clave,dni,email);
            }
        }catch (Exception e){
        Log.d("json", e.toString());
        }
        Usuario.user = user;
    }
}
