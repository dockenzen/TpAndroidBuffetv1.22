package com.example.user.tpandroidbuffetv12.adapHold;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.tpandroidbuffetv12.Http.Hilo;
import com.example.user.tpandroidbuffetv12.Http.HttpConnection;
import com.example.user.tpandroidbuffetv12.Http.ThreadConexion;
import com.example.user.tpandroidbuffetv12.R;
import com.example.user.tpandroidbuffetv12.activity.MenuActivity;
import com.example.user.tpandroidbuffetv12.model.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 2/5/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Producto> lista = new ArrayList<Producto>();
    private MenuActivity acc;

    public MyAdapter(MenuActivity main){
        this.traerProductos();
        this.acc = main;
    }

    public MyAdapter(ArrayList<Producto> productos,MenuActivity main){
        this.lista = productos;
        this.acc = main;
    }

    //EL INFLATE TRANSFROMA EL XML DEL LAYOUT EN UN OBJETO DE TIPO VIEW
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu,parent,false);
        MyViewHolder m = new MyViewHolder(v, this.acc);
        return m;
    }

    // llena el objeto creado con la info que corresponde
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Producto producto = lista.get(position);
        if(producto.getImagenDescargada() == null) {
            ThreadConexion threadConexion = new ThreadConexion(producto, this);
            Thread hilo = new Thread(new Hilo(threadConexion,producto.getUrlImagen(),1));
            hilo.start();
            holder.imagen.setImageResource(R.drawable.notfound);
        }
        else{
            holder.imagen.setImageBitmap(producto.getImagenDescargada());
        }
        holder.nombre.setText(producto.getNombre());
        holder.precio.setText(String.format("%.2f",producto.getPrecio()));
        holder.cantidadElementos.setText(String.valueOf(this.calcularItems()));
        //producto.setImagenDescargada();//bitmap o byte[] ?


        Log.d("MVH","2 ON BIND VIEW HOLDER");
    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    private int calcularItems() {
        int acumulador = 0;
        for (Producto prod : this.acc.pedido.getLista()) {
            acumulador += prod.getCantidad();
        }
        return acumulador;
    }

    private void traerProductos()
    {
        Thread hiloProductos = new Thread(new Hilo(new ThreadConexion(this.lista,this),HttpConnection.pathUrl+"/productos/",3));
        hiloProductos.start();
        this.notifyDataSetChanged();
    }


}
