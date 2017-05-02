package com.example.user.tpandroidbuffetv12;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by USER on 1/5/2017.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nombre;
    EditText apellido;
    EditText dni;
    EditText email;
    EditText pass1;
    EditText pass2;
    Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nombre = (EditText) findViewById(R.id.txtNombre);
        apellido = (EditText) findViewById(R.id.txtApellido);
        dni = (EditText) findViewById(R.id.txtDni);
        email = (EditText) findViewById(R.id.txtEmail);
        pass1 = (EditText) findViewById(R.id.txtClave1);
        pass2 = (EditText) findViewById(R.id.txtReClave2);
        registrar = (Button) findViewById(R.id.registrar);
        registrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(nombre.getText().toString().trim().isEmpty())
        {
            nombre.setError("El campo nombre no puede ser vacio");
        }else
        if(apellido.getText().toString().trim().isEmpty())
        {
            apellido.setError("El campo apellido no puede ser vacio");
        }else
        if(dni.getText().toString().trim().isEmpty())
        {
            dni.setError("El campo dni no puede ser vacio");
        }else
        if(email.getText().toString().trim().isEmpty())
        {
            email.setError("El campo email no puede ser vacio");
        } else
        if(!Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches())
        {
            email.setError("Formato inválido");
        }else
        if(pass1.getText().toString().trim().isEmpty())
        {
            pass1.setError("El campo clave no puede ser vacio");
        }else
        if(pass2.getText().toString().trim().isEmpty())
        {
            pass2.setError("El campo clave no puede ser vacio");
        }else
        if(!pass1.getText().toString().trim().equals(pass2.getText().toString().trim()))
        {
            pass2.setError("Las claves deben ser iguales");
        }
        else
        {
            //mandame a otro lado papa
            Log.d("aca llege","eaea");
        }
    }
}
