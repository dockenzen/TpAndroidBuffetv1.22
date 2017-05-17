package com.example.user.tpandroidbuffetv12.activity;

import android.content.Intent;
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

import com.example.user.tpandroidbuffetv12.R;
import com.example.user.tpandroidbuffetv12.model.Dialogo;
import com.example.user.tpandroidbuffetv12.model.PedidoAlertListener;
import com.example.user.tpandroidbuffetv12.model.Usuario;

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
            nombre.setError(getString(R.string.validacion_campoVacio));
        }else
        if(apellido.getText().toString().trim().isEmpty())
        {
            apellido.setError(getString(R.string.validacion_campoVacio));
        }else
        if(dni.getText().toString().trim().isEmpty())
        {
            dni.setError(getString(R.string.validacion_campoVacio));
        }else
        if(email.getText().toString().trim().isEmpty())
        {
            email.setError(getString(R.string.validacion_campoVacio));
        } else
        if(!Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches())
        {
            email.setError(getString(R.string.validacion_loginemail));
        }else
        if(pass1.getText().toString().trim().isEmpty())
        {
            pass1.setError(getString(R.string.validacion_campoVacio));
        }else
        if(pass2.getText().toString().trim().isEmpty())
        {
            pass2.setError(getString(R.string.validacion_campoVacio));
        }else
        if(!pass1.getText().toString().trim().equals(pass2.getText().toString().trim()))
        {
            pass2.setError(getString(R.string.validacion_passIguales));
        }
        else
        {
            if(Usuario.verificarDuplicado(email.getText().toString(),dni.getText().toString()))
            {
                Dialogo d = new Dialogo();
                d.setListener(new PedidoAlertListener());
                d.setTitulo(getString(R.string.dialogo_registroDuplicadoTitulo));
                d.setMensaje(getString(R.string.dialogo_registroDuplicadoMensaje));
                d.show(getFragmentManager(),"Registro error");
            }
            else
            {
                finish();
            }
        }
    }
}
