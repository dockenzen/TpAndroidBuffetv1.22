package com.example.user.tpandroidbuffetv12.Http;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;

/**
 * Created by USER on 11/5/2017.
 */

public class Hilo implements  Runnable
{
    private String ruta;
    private Handler handler;
    public Hilo(Handler h,String ruta)
    {
        this.handler = h;
        this.ruta = ruta;
    }

    /**
     * Handler cmunicacion entre dos hilos
     */
    @Override
    public void run() {
        Message m = new Message();
        HttpConnection httpConnection = new HttpConnection();
        try
        {
            m.obj = httpConnection.getBytesDataByGET(this.ruta);
            m.arg1  = 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.handler.sendMessage(m);
    }
}
