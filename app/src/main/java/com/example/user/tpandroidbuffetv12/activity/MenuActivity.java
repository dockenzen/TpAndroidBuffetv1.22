package com.example.user.tpandroidbuffetv12.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;


import com.example.user.tpandroidbuffetv12.R;
import com.example.user.tpandroidbuffetv12.adapHold.MyAdapter;
import com.example.user.tpandroidbuffetv12.controller.MenuController;
import com.example.user.tpandroidbuffetv12.model.Pedido;
import com.example.user.tpandroidbuffetv12.model.Producto;
import com.example.user.tpandroidbuffetv12.view.VistaPedidoEnCurso;

import java.util.ArrayList;

/**
 * Created by USER on 2/5/2017.
 */

public class MenuActivity extends AppCompatActivity{

     public Pedido pedido;
     private MenuController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        pedido = new Pedido();
        mc = new MenuController(this);

        ArrayList<Producto> lista = Producto.getStaticListMenus();

       // VistaPedidoEnCurso v = new VistaPedidoEnCurso(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return  true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Log.d("Click", "Opcion del menu "+ item.getItemId() );
        Intent intento;
        switch (item.getItemId()){
            case 2131558587:
                mc.cambiarProductos(Producto.getStaticListBebidas());
                Log.d("Click", "Opcion del menu "+ item.getTitle() );
                break;
            case 2131558588:
                mc.cambiarProductos(Producto.getStaticListMenus());
                Log.d("Click", "Opcion del menu "+ item.getTitle() );
                break;
            case 2131558589:
                mc.cambiarProductos(Producto.getStaticListSnacks());
                Log.d("Click", "Opcion del menu "+ item.getTitle() );
                break;
            case 2131558590:
                Log.d("Ver Pedido", "Opcion del menu "+ item.getTitle() );
                //intento = new Intent(getApplicationContext(),PedidoActivity.class);
                //startActivity(intento);
                //pasarle la data del pedido

                break;
            case 2131558591:
                SharedPreferences share = getSharedPreferences("miConfig",MODE_PRIVATE);
                String[] array = LoginActivity.getData();
                share.edit().putString(array[0],"");
                share.edit().putString(array[1],"");
                share.edit().putBoolean(array[2],false);
                share.edit().apply();

               finish();
                //intento = new Intent(getApplicationContext(),LoginActivity.class);
                //startActivity(intento);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
