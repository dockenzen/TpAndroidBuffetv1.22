package com.example.user.tpandroidbuffetv12.adapHold;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.tpandroidbuffetv12.R;
import com.example.user.tpandroidbuffetv12.activity.MenuActivity;
import com.example.user.tpandroidbuffetv12.activity.PedidoActivity;
import com.example.user.tpandroidbuffetv12.controller.PedidoController;
import com.example.user.tpandroidbuffetv12.model.Dialogo;
import com.example.user.tpandroidbuffetv12.model.Pedido;
import com.example.user.tpandroidbuffetv12.model.Producto;

/**
 * Created by alumno on 09/05/2017.
 */

public class MyViewHolderPedido extends RecyclerView.ViewHolder  implements View.OnClickListener
{
    View v;
    PedidoActivity pedidoActivity;
    TextView nombre;
    TextView precio;
    ImageView imagen;
    EditText importeEstimado;
    FloatingActionButton boton;
    Button enviarPedido;
    TextView txtProdPedidos;
    TextView txtCantidadProductoPedido;

    public MyViewHolderPedido(View itemView, PedidoActivity context) {
        super(itemView);
        this.v = itemView;
        this.pedidoActivity = context;
        this.txtCantidadProductoPedido = (TextView) this.v.findViewById(R.id.txtCantidadProductoPedido);
        this.txtProdPedidos = (TextView) this.v.findViewById(R.id.txtCantidadProductoPedido);
        this.imagen = (ImageView) this.v.findViewById(R.id.imageView2);
        this.precio = (TextView) this.v.findViewById(R.id.txtPrecioProductoPedido);
        this.nombre = (TextView) this.v.findViewById(R.id.txtNombreProductoPedido);
        this.importeEstimado = (EditText) this.pedidoActivity.findViewById(R.id.editTxtPrecioEstimadoPedido);
        this.boton = (FloatingActionButton) this.v.findViewById(R.id.deleteProd);
        this.enviarPedido = (Button) this.pedidoActivity.findViewById(R.id.terminarPedidoBtn);
        this.boton.setOnClickListener(this);
        this.enviarPedido.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        if(v.getId()== this.boton.getId())
        {
            Producto o = new Producto(this.nombre.getText().toString(),Double.parseDouble(this.precio.getText().toString().replace("$","")));
            Boolean esta = MenuActivity.pedido.getLista().contains(o);
            if(esta && MenuActivity.pedido.getLista().get(MenuActivity.pedido.getLista().indexOf(o)).getCantidad() == 1)
            {
                MenuActivity.pedido.setTotal(MenuActivity.pedido.getTotal()-o.getPrecio());
                MenuActivity.pedido.getLista().remove(o);
            }
            else
            {
                MenuActivity.pedido.getLista().get(MenuActivity.pedido.getLista().indexOf(o)).setCantidad(-1);
                this.importeEstimado.setText("$ "+MenuActivity.pedido.getTotal().toString());

            }
            MenuActivity.pedido.setTotal(calcularEstimado());
            PedidoController.modificarPedido();
            this.importeEstimado.setText(String.format("$ %s",MenuActivity.pedido.getTotal().toString()));

        }
        if(v.getId() == this.enviarPedido.getId())
        {
            Dialogo d = new Dialogo();
            d.setListener(this.pedidoActivity.pd);
            if(MenuActivity.pedido.getLista().size() == 0){
                d.setTitulo("Error");
                d.setMensaje(this.pedidoActivity.getString(R.string.dialogo_menuvacio));
                d.show(this.pedidoActivity.getFragmentManager(),"Error pedido");
            }
            else
            {
                d.setTitulo(this.pedidoActivity.getString(R.string.pedido_enviado));
                d.setMensaje(MenuActivity.pedido.toString());
                d.show(this.pedidoActivity.getFragmentManager(),"Pedido hecho");
            }
        }
    }
    private Double calcularEstimado()
    {
        Double total= 0d;

        for (Producto prod: MenuActivity.pedido.getLista())
        {
            total += prod.getPrecio() * prod.getCantidad();
        }
        return total;
    }
}
