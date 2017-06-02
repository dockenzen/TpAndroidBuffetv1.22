package com.example.user.tpandroidbuffetv12.Http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by alumno on 01/06/2017.
 */

public class ThreadConexion implements Handler.Callback
{
    @Override
    public boolean handleMessage(Message msg) {

        //tiene que ser algo que este definido dentro del thread de la grafica ese algo
        //que reciba los mensajes
        //identificador para relacionar ocn algo
        switch(msg.arg1)
        {
            case 0:
                Log.d("error","error");
                break;
            case 1:
                Log.d("activit","recibiendo foto");
                byte[] bytes = (byte[])msg.obj;
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
         //       this.imagen.setImageBitmap(bitmap);
                break;
        }
        return true;
    }
}
