package com.example.user.tpandroidbuffetv12.Http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.user.tpandroidbuffetv12.activity.MenuActivity;
import com.example.user.tpandroidbuffetv12.activity.PedidoActivity;
import com.example.user.tpandroidbuffetv12.activity.RegisterActivity;
import com.example.user.tpandroidbuffetv12.adapHold.MyAdapter;
import com.example.user.tpandroidbuffetv12.model.Producto;
import com.example.user.tpandroidbuffetv12.model.Usuario;

import java.util.List;

/**
 * Created by alumno on 01/06/2017.
 */

public class ThreadConexion implements Handler.Callback {

    Producto producto;
    Bitmap bitmap;
    RecyclerView.Adapter a;
    List<Producto> lista;
    Usuario user;

    public ThreadConexion() {
    }
    public ThreadConexion(Usuario user) {
        this.user = user;
    }
    public ThreadConexion(Producto prod, RecyclerView.Adapter adapter) {
        this.producto = prod;
        this.a = adapter;
    }
    public ThreadConexion(List<Producto> prod, RecyclerView.Adapter adapter) {
        this.lista = prod;
        this.a = adapter;
    }

    @Override
    public boolean handleMessage(Message msg) {
        //tiene que ser algo que este definido dentro del thread de la grafica ese algo
        //que reciba los mensajes
        //identificador para relacionar ocn algo.
        HttpConnection httpConnection = new HttpConnection();
        JsonParse jsonParse = new JsonParse();
        switch(msg.arg1) {
            case 1:
                Log.d("FOTO", "RECIBIR FOTO ");
                byte[] bytes = httpConnection.getBytesDataByGET(msg.obj.toString());
                bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                producto.setImagenDescargada(bitmap);
                break;
            case 2:
                try{
                    String json = httpConnection.getStringDataByGET(msg.obj.toString());
                    Log.d("USUARIOS", json);
                    Usuario.esta = jsonParse.validarUsuario(json);
                }
                catch (Exception e) {
                    Log.d("CASE2", e.toString());
                }
                break;
            case 3:
                try {
                    String json = httpConnection.getStringDataByGET(msg.obj.toString());
                    jsonParse.listaProductos(json);
                    this.lista.addAll(Producto.getStaticListMenus());
                    this.a.notifyDataSetChanged();
                    Log.d("PRODUCTOS",Producto.getStaticListBebidas().toString());
                }
                catch (Exception e){
                    Log.d("CASE3",e.toString());
                }
                break;
            case 4:
                try {
                String pedidoJson = jsonParse.toJsonPedido(MenuActivity.pedido);
                httpConnection.getBytesDataByPOST(msg.obj.toString(),pedidoJson);
                Producto.limpiarCantidades();
                MenuActivity.pedido.getLista().clear();
                }
                catch (Exception e){
                    Log.d("CASE4",e.toString());
                }
                break;
            case 6:
                //get data user for email
                try {
                    String jsonUser = httpConnection.getStringDataByGET(msg.obj.toString());
                    jsonParse.getUserFromEmail(jsonUser);
                }
                catch (Exception e){
                    Log.d("CASE5",e.toString());
                }
                break;
        }
        return true;
    }
}
