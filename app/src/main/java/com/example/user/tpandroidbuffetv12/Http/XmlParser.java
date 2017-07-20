package com.example.user.tpandroidbuffetv12.Http;

import android.util.Log;
import android.util.Xml;

import com.example.user.tpandroidbuffetv12.model.Producto;

import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alumno on 01/06/2017.
 */

public class XmlParser {
    public static List<Producto> lista;

    public static List<Producto> obtenerProductos(String xml) {
        List<Producto> lista = new ArrayList<Producto>();
        XmlPullParser xmlPullParser = Xml.newPullParser();

        try
        {
            xmlPullParser.setInput(new StringReader(xml));
            int event = xmlPullParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT )
            {
                if(event == XmlPullParser.START_TAG)
                {
                    if(xmlPullParser.getName().equals("ElPrimerTagEncontrado"))
                    {
                     Producto producto = new Producto();
                    producto.setNombre(xmlPullParser.getAttributeValue(null,"atributo"));
                    producto.setPrecio(Double.parseDouble(xmlPullParser.getAttributeValue(null,"atributo2")));
                        lista.add(producto);
                    }
                }
            }
        }
        catch (Exception e)
        {
            Log.d("pum",e.getMessage());
        }
        return lista;
    }
}
