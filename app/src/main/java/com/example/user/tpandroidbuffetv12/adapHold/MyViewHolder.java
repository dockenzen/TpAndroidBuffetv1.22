package com.example.user.tpandroidbuffetv12.adapHold;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.tpandroidbuffetv12.Http.Hilo;
import com.example.user.tpandroidbuffetv12.R;
import com.example.user.tpandroidbuffetv12.activity.MenuActivity;
import com.example.user.tpandroidbuffetv12.activity.PedidoActivity;
import com.example.user.tpandroidbuffetv12.model.Dialogo;
import com.example.user.tpandroidbuffetv12.model.PedidoAlertListener;
import com.example.user.tpandroidbuffetv12.model.Producto;

/**
 * Created by USER on 2/5/2017.
 */

public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

    View v;
    TextView nombre;
    TextView precio;
    ImageView imagen;
    FloatingActionButton boton;
    Button enviarPedido;
    MenuActivity acc;
    EditText cantidadElementos;
    EditText importeEstimado;


    public MyViewHolder(View itemView,MenuActivity ac) {
        super(itemView);
        this.acc =ac;
        this.v = itemView;
        this.imagen = (ImageView) this.v.findViewById(R.id.imageView2);
        this.precio = (TextView) this.v.findViewById(R.id.txtPrecioProducto);
        this.nombre = (TextView) this.v.findViewById(R.id.txtNombreProducto);
        this.importeEstimado = (EditText) this.acc.findViewById(R.id.editTxtPrecioEstimado);
        this.cantidadElementos = (EditText) this.acc.findViewById(R.id.txtCantidadElementos);
        this.boton = (FloatingActionButton) this.v.findViewById(R.id.addProd);
        this.enviarPedido = (Button) this.acc.findViewById(R.id.enviarPedidoBtn);
        this.boton.setOnClickListener(this);
        this.enviarPedido.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if(v.getId() == this.boton.getId())
        {
            Integer cantidad = 0;
            Producto o = new Producto(this.nombre.getText().toString(),Double.parseDouble(this.precio.getText().toString()),1,"");
            Boolean esta = this.acc.pedido.getLista().contains(o);
            if(!esta)
            {
                this.acc.pedido.add(o);
            }
            else
            {
                (this.acc.pedido.getLista().get(this.acc.pedido.getLista().indexOf(o))).setCantidad(1);
            }

            Double estimado = calcularEstimado();
            this.acc.pedido.setTotal(estimado);
            this.importeEstimado.setText("$"+estimado.toString());
            this.cantidadElementos.setText(String.valueOf(this.calcularItems()));
        }
        if(v.getId() == this.enviarPedido.getId())
        {
            if(this.acc.pedido.getLista().size() == 0)
            {
                Dialogo d = new Dialogo();
                d.setListener(new PedidoAlertListener());
                if(MenuActivity.pedido.getLista().size() == 0){
                    d.setTitulo("Error");
                    d.setMensaje(this.acc.getString(R.string.dialogo_menuvacio));
                    d.show(this.acc.getFragmentManager(),"Error pedido");
                }
            }
            else
            {
                Intent intento = new Intent(this.acc.getApplicationContext(), PedidoActivity.class);
                this.acc.startActivity(intento);
            }
        }
    }

    private Double calcularEstimado()
    {
        Double total= 0d;

        for (Producto prod: this.acc.pedido.getLista())
        {
            total += prod.getPrecio() * prod.getCantidad();
        }
        return total;
    }


    private int calcularItems() {
        int acumulador = 0;
        for (Producto prod : this.acc.pedido.getLista()) {
            acumulador += prod.getCantidad();
        }
        return acumulador;
    }
}
