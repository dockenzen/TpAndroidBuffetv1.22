package com.example.user.tpandroidbuffetv12.model;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.user.tpandroidbuffetv12.R;
import com.example.user.tpandroidbuffetv12.activity.PedidoActivity;
import com.example.user.tpandroidbuffetv12.controller.PedidoController;

/**
 * Created by USER on 13/5/2017.
 */

public class Dialogo extends DialogFragment
{
    private String titulo;
    private String mensaje;
    private DialogInterface.OnClickListener l;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(this.titulo);
        builder.setMessage(this.mensaje);
        builder.setPositiveButton(getString(R.string.aceptar), this.l);

        // Creamos el dialogo
        AlertDialog ad = builder.create();
        return ad;
    }

    public void setListener(DialogInterface.OnClickListener listener) {this.l = listener;}
    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }
    public void setMensaje(String mensaje)
    {
        this.mensaje = mensaje;
    }
}
