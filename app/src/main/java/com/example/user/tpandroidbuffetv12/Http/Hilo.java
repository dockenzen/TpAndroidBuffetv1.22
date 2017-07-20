package com.example.user.tpandroidbuffetv12.Http;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;

/**
 * Created by USER on 11/5/2017.
 */

public class Hilo implements  Runnable{
    private String ruta;
    private Handler.Callback handler;
    private int bandera;

    public Hilo(Handler.Callback h,String ruta,int flag){
        this.handler = h;
        this.ruta = ruta;
        this.bandera = flag;
    }

    /**
     * Handler cmunicacion entre dos hilos
     */
    @Override
    public void run() {
            Message m = new Message();
            m.obj = this.ruta;
            m.arg1  = this.bandera;
            this.handler.handleMessage(m);
    }
}
