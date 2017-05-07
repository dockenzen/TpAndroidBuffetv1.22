package com.example.user.tpandroidbuffetv12.controller;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.user.tpandroidbuffetv12.R;
import com.example.user.tpandroidbuffetv12.activity.MenuActivity;
import com.example.user.tpandroidbuffetv12.adapHold.MyAdapter;
import com.example.user.tpandroidbuffetv12.model.Producto;

import java.util.ArrayList;


/**
 * Created by USER on 3/5/2017.
 */

public class MenuController {

    private MenuActivity contexto;
    private RecyclerView recycler;
    private LinearLayoutManager layoutManager;
    private MyAdapter adapter;


    public MenuController(MenuActivity context){

        this.contexto = context;
        recycler = (RecyclerView)contexto.findViewById(R.id.list);

        layoutManager = new LinearLayoutManager(context);
        recycler.setLayoutManager(layoutManager);

        adapter = new MyAdapter(Producto.getStaticListMenus(),context);
        recycler.setAdapter(adapter);

    }

    public void cambiarProductos(ArrayList<Producto> nuevos){

        recycler.swapAdapter(new MyAdapter(nuevos,contexto),false);

        adapter.notifyDataSetChanged();

    }



}
