package com.example.user.tpandroidbuffetv12.adapHold;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.tpandroidbuffetv12.Http.Hilo;
import com.example.user.tpandroidbuffetv12.Http.ThreadConexion;
import com.example.user.tpandroidbuffetv12.R;
import com.example.user.tpandroidbuffetv12.activity.MenuActivity;
import com.example.user.tpandroidbuffetv12.activity.PedidoActivity;
import com.example.user.tpandroidbuffetv12.model.Producto;

import java.util.List;

/**
 * Created by alumno on 09/05/2017.
 */

public class MyAdapterPedido extends RecyclerView.Adapter<MyViewHolderPedido>
{

    private List<Producto> lista;
    private PedidoActivity acc;

    public MyAdapterPedido(List<Producto> lis,PedidoActivity main){this.lista=lis;
        this.acc = main;}

    //EL INFLATE TRANSFROMA EL XML DEL LAYOUT EN UN OBJETO DE TIPO VIEW
    @Override
    public MyViewHolderPedido onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido,parent,false);
        MyViewHolderPedido m = new MyViewHolderPedido(v, this.acc);
        return m;
    }

    // llena el objeto creado con la info que corresponde
    @Override
    public void onBindViewHolder(MyViewHolderPedido holder, int position) {
        Hilo hilo;
        Producto producto = lista.get(position);
        ThreadConexion threadConexion = new ThreadConexion(producto,this);
        if(producto.getImagenDescargada() == null) {
            hilo = new Hilo(threadConexion,producto.getUrlImagen(),1);
            producto.setImagenDescargada(producto.getImagenDescargada());//bitmap o byte[] ?
        }
        else
        {
            hilo = new Hilo(threadConexion,producto.getUrlImagen(),3);
        }
        hilo.run();
        holder.nombre.setText(producto.getNombre());
        holder.precio.setText(String.format("$ %.2f ",producto.getPrecio()));
        holder.txtCantidadProductoPedido.setText(this.acc.getString(R.string.cantidad)+": "+producto.getCantidad());
        holder.importeEstimado.setText(String.format("$ %.2f ",MenuActivity.pedido.getTotal()));
        holder.imagen.setImageBitmap(producto.getImagenDescargada());
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
