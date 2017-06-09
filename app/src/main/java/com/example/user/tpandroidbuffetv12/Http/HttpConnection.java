package com.example.user.tpandroidbuffetv12.Http;

import android.util.Log;
import android.view.MenuInflater;

import com.example.user.tpandroidbuffetv12.activity.MenuActivity;
import com.example.user.tpandroidbuffetv12.activity.PedidoActivity;
import com.example.user.tpandroidbuffetv12.model.Producto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by USER on 11/5/2017.
 */

public class HttpConnection {

    public byte[] getBytesDataByGET(String strUrl) throws IOException
    {
        URL url = new URL(strUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();
        int response = urlConnection.getResponseCode();
        Log.d("http", "Response Code:   "+ response);
        if(response == 200)
        {
            InputStream is = urlConnection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int leng = 0;
            while ((leng = is.read(buffer)) != -1 )
            {
                baos.write(buffer,0,leng);
            }
            is.close();
            return baos.toByteArray();
        }
        else
            throw new IOException();

    }

    //construimos los productos
    public String getStrDataByGET() throws IOException
    {
        StringBuilder str = new StringBuilder();
        for (Producto prod : MenuActivity.pedido.getLista())
        {
            byte[] bytes = getBytesDataByGET(prod.getUrlImagen());

            str.append(new String(bytes,"UTF-8"));
        }
        return str.toString();
    }




}
