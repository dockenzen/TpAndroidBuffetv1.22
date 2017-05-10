package com.example.user.tpandroidbuffetv12.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;

import com.example.user.tpandroidbuffetv12.R;
import com.example.user.tpandroidbuffetv12.controller.PedidoController;

/**
 * Created by alumno on 09/05/2017.
 */

public class PedidoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pedido);

      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.appbar2);
        setSupportActionBar(toolbar);
*/
        FloatingActionButton loguear = (FloatingActionButton) findViewById(R.id.deleteProd);

        PedidoController pd = new PedidoController(this);

    }
}
