package com.example.user.tpandroidbuffetv12.adapHold;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.tpandroidbuffetv12.R;
import com.example.user.tpandroidbuffetv12.activity.PedidoActivity;
import com.example.user.tpandroidbuffetv12.model.Pedido;

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
    Pedido pedido;

    public MyViewHolderPedido(View itemView, PedidoActivity context) {
        super(itemView);
        this.v = itemView;
        this.pedidoActivity = context;
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

    }
}
