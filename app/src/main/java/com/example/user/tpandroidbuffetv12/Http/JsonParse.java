package com.example.user.tpandroidbuffetv12.Http;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by alumno on 01/06/2017.
 */

public class JsonParse {
    public void parsear(String json)
    {
        try {
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = jsonObject.getJSONArray("frutas");
            for (int i = 0; i < jsonArray.length();i++)
            {
                JSONObject fruta = jsonArray.getJSONObject(i);
                String nombre = fruta.getString("nombre");
                Integer unidad = fruta.getInt("cantidad");

            }
        }
        catch (Exception ex)
        {

        }

    }
}
