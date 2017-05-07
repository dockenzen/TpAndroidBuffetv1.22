package com.example.user.tpandroidbuffetv12.adapHold;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.tpandroidbuffetv12.R;
import com.example.user.tpandroidbuffetv12.activity.MenuActivity;
import com.example.user.tpandroidbuffetv12.model.Producto;

import java.util.List;

/**
 * Created by USER on 2/5/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Producto> lista;
    private MenuActivity acc;

    public MyAdapter(List<Producto> lis,MenuActivity main){this.lista=lis;
    this.acc = main;}

    //EL INFLATE TRANSFROMA EL XML DEL LAYOUT EN UN OBJETO DE TIPO VIEW
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu,parent,false);
        MyViewHolder m = new MyViewHolder(v, this.acc);
        return m;
    }

    // llena el objeto creado con la info que corresponde
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Producto per = lista.get(position);
        holder.nombre.setText(per.getNombre());
        holder.precio.setText(String.format("%.2f",per.getPrecio()));

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

}
