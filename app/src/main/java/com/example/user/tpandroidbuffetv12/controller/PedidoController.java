package com.example.user.tpandroidbuffetv12.controller;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.tpandroidbuffetv12.R;
import com.example.user.tpandroidbuffetv12.activity.MenuActivity;
import com.example.user.tpandroidbuffetv12.activity.PedidoActivity;
import com.example.user.tpandroidbuffetv12.adapHold.MyAdapter;
import com.example.user.tpandroidbuffetv12.adapHold.MyAdapterPedido;
import com.example.user.tpandroidbuffetv12.model.Pedido;
import com.example.user.tpandroidbuffetv12.model.Producto;

/**
 * Created by alumno on 09/05/2017.
 */

public class PedidoController {

    private PedidoActivity contexto;
    private RecyclerView recycler;
    private LinearLayoutManager layoutManager;
    private MyAdapterPedido adapter;

    public PedidoController(PedidoActivity context){

        this.contexto = context;
        recycler = (RecyclerView)contexto.findViewById(R.id.listPedido);

        layoutManager = new LinearLayoutManager(context);
        recycler.setLayoutManager(layoutManager);

        //TODO
        //Cambiar por los productos del pedido
        adapter = new MyAdapterPedido(Producto.getStaticListMenus(),context);
        recycler.setAdapter(adapter);

    }
}
