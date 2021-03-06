package com.example.user.tpandroidbuffetv12.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.tpandroidbuffetv12.Http.Hilo;
import com.example.user.tpandroidbuffetv12.Http.HttpConnection;
import com.example.user.tpandroidbuffetv12.Http.ThreadConexion;
import com.example.user.tpandroidbuffetv12.R;
import com.example.user.tpandroidbuffetv12.activity.MenuActivity;
import com.example.user.tpandroidbuffetv12.activity.PedidoActivity;
import com.example.user.tpandroidbuffetv12.adapHold.MyAdapter;
import com.example.user.tpandroidbuffetv12.adapHold.MyAdapterPedido;
import com.example.user.tpandroidbuffetv12.model.Pedido;
import com.example.user.tpandroidbuffetv12.model.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alumno on 09/05/2017.
 */

public class PedidoController implements DialogInterface.OnClickListener {

    private PedidoActivity contexto;
    private RecyclerView recycler;
    private LinearLayoutManager layoutManager;
    private static MyAdapterPedido adapter;

    public PedidoController(PedidoActivity context){

        this.contexto = context;
        recycler = (RecyclerView)contexto.findViewById(R.id.listPedido);

        layoutManager = new LinearLayoutManager(context);
        recycler.setLayoutManager(layoutManager);
        adapter = new MyAdapterPedido(MenuActivity.pedido.getLista(),context);
        recycler.setAdapter(adapter);

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        ThreadConexion threadConexion = new ThreadConexion();
        Thread hilo = new Thread(new Hilo(threadConexion, HttpConnection.pathUrl+"/pedidos/nuevo",4));
        hilo.start();
        this.contexto.finish();
    }

    public static void modificarPedido()
    {
        adapter.notifyDataSetChanged();
    }

}

