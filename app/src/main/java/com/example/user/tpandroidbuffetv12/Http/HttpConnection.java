package com.example.user.tpandroidbuffetv12.Http;

import android.net.Uri;
import android.os.StrictMode;
import android.util.Log;

import com.example.user.tpandroidbuffetv12.model.Dialogo;
import com.example.user.tpandroidbuffetv12.model.PedidoAlertListener;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by USER on 11/5/2017.
 */

public class HttpConnection {

    public static String pathUrl = "http://192.168.0.4:3000";

    public byte[] getBytesDataByGET(String strUrl) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ByteArrayOutputStream baos = null;
        try {
            URL url = new URL(strUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(10000);
            urlConnection.connect();
            int response = urlConnection.getResponseCode();
            Log.d("http", "Response Code:" + response);
            if (response == 200) {
                InputStream is = urlConnection.getInputStream();
                baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int leng = 0;
                while ((leng = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, leng);
                }
                is.close();
            } else throw new IOException();
        } catch (IOException ex) {
            Dialogo d = new Dialogo();
            d.setListener(new PedidoAlertListener());
            d.setTitulo("Error al obtener foto");
            d.setMensaje(ex.getMessage());
        }
        return baos.toByteArray();
    }

    public String getStringDataByGET(String strUrl) throws UnsupportedEncodingException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ByteArrayOutputStream baos = null;
        try {
            URL url = new URL(strUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            int response = urlConnection.getResponseCode();
            Log.d("http", "Response code:" + response);
            if (response == 200) {
                InputStream is = urlConnection.getInputStream();
                 baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int lenght = 0;
                while ((lenght = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, lenght);
                }
                is.close();
            } else {
                throw new IOException();
            }
        } catch (IOException ex) {
            Dialogo d = new Dialogo();
            d.setListener(new PedidoAlertListener());
            d.setTitulo("Error al obtener foto");
            d.setMensaje(ex.getMessage());
        }
        return new String(baos.toByteArray(),"UTF-8");
    }

    public byte[] getBytesDataByPOST(String strUrl, String datos) throws IOException
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URL url = new URL(strUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
        writer.write(datos);
        writer.flush();
        writer.close();
        os.close();
        int response = conn.getResponseCode();
        if(response==200) {
            InputStream inputStream = conn.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            inputStream.close();
            return baos.toByteArray();
        }
        else
            throw new IOException();
    }
}
