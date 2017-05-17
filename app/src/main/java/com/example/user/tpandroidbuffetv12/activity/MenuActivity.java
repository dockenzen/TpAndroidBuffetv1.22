package com.example.user.tpandroidbuffetv12.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.example.user.tpandroidbuffetv12.Http.Hilo;
import com.example.user.tpandroidbuffetv12.R;
import com.example.user.tpandroidbuffetv12.controller.MenuController;
import com.example.user.tpandroidbuffetv12.model.Dialogo;
import com.example.user.tpandroidbuffetv12.model.Pedido;
import com.example.user.tpandroidbuffetv12.model.PedidoAlertListener;
import com.example.user.tpandroidbuffetv12.model.Producto;
import java.util.ArrayList;

/**
 * Created by USER on 2/5/2017.
 */

public class MenuActivity extends AppCompatActivity
{

     public static Pedido pedido;
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

        Log.d("Click", "Opcion del menu "+ item.getTitle() );
        Intent intento;
        String titulo = item.getTitle().toString();
        if(titulo.equals("Cafe/Bebidas")||titulo.equals("Coffee/Drinks") ) {
            mc.cambiarProductos(Producto.getStaticListBebidas());
            Log.d("Click", "Opcion del menu " + item.getTitle());
        }
        if(titulo.equals("Menus") ) {
                mc.cambiarProductos(Producto.getStaticListMenus());
                Log.d("Click", "Opcion del menu "+ item.getTitle() );
        }
        if(titulo.equals("Snacks") ) {
            mc.cambiarProductos(Producto.getStaticListSnacks());
            Log.d("Click", "Opcion del menu " + item.getTitle());
        }
        if(titulo.equals("Ver Pedido")||titulo.equals("View Order") ) {
            Dialogo d = new Dialogo();
            d.setListener(new PedidoAlertListener());
            if (MenuActivity.pedido.getLista().size() == 0) {
                d.setTitulo("Error");
                d.setMensaje(getString(R.string.dialogo_menuvacio));
                d.show(getFragmentManager(), "Error pedido");
            } else {
                Log.d("Ver Pedido", "Opcion del menu " + item.getTitle());
                intento = new Intent(getApplicationContext(), PedidoActivity.class);
                startActivity(intento);
            }
        }
        if(titulo.equals("Desloguear")|| titulo.equals("Logout")){
                SharedPreferences share = getSharedPreferences("miConfig",MODE_PRIVATE);
                String[] array = LoginActivity.getData();
                share.edit().putString(array[0],"");
                share.edit().putString(array[1],"");
                share.edit().putBoolean(array[2],false);
                share.edit().apply();
                finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
