package com.example.user.tpandroidbuffetv12.controller;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.tpandroidbuffetv12.Http.Hilo;
import com.example.user.tpandroidbuffetv12.Http.HttpConnection;
import com.example.user.tpandroidbuffetv12.Http.JsonParse;
import com.example.user.tpandroidbuffetv12.Http.ThreadConexion;
import com.example.user.tpandroidbuffetv12.R;
import com.example.user.tpandroidbuffetv12.activity.RegisterActivity;
import com.example.user.tpandroidbuffetv12.model.Dialogo;
import com.example.user.tpandroidbuffetv12.model.PedidoAlertListener;
import com.example.user.tpandroidbuffetv12.model.Usuario;

/**
 * Created by USER on 22/6/2017.
 */

public class RegisterController implements View.OnClickListener , Handler.Callback {

    EditText nombre;
    EditText apellido;
    EditText dni;
    EditText email;
    EditText pass1;
    EditText pass2;
    Button registrar;
    RegisterActivity activity;

    public RegisterController(RegisterActivity activity){
        this.activity = activity;
        nombre = (EditText) this.activity.findViewById(R.id.txtNombre);
        apellido = (EditText) this.activity.findViewById(R.id.txtApellido);
        dni = (EditText) this.activity.findViewById(R.id.txtDni);
        email = (EditText) this.activity.findViewById(R.id.txtEmail);
        pass1 = (EditText) this.activity.findViewById(R.id.txtClave1);
        pass2 = (EditText) this.activity.findViewById(R.id.txtReClave2);
        registrar = (Button) this.activity.findViewById(R.id.registrar);
        registrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(nombre.getText().toString().trim().isEmpty()){
            nombre.setError(this.activity.getString(R.string.validacion_campoVacio));
        }else
        if(apellido.getText().toString().trim().isEmpty()){
            apellido.setError(this.activity.getString(R.string.validacion_campoVacio));
        }else
        if(dni.getText().toString().trim().isEmpty()){
            dni.setError(this.activity.getString(R.string.validacion_campoVacio));
        }else
        if(email.getText().toString().trim().isEmpty()){
            email.setError(this.activity.getString(R.string.validacion_campoVacio));
        } else
        if(!Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()){
            email.setError(this.activity.getString(R.string.validacion_loginemail));
        }else
        if(pass1.getText().toString().trim().isEmpty()){
            pass1.setError(this.activity.getString(R.string.validacion_campoVacio));
        }else
        if(pass2.getText().toString().trim().isEmpty()){
            pass2.setError(this.activity.getString(R.string.validacion_campoVacio));
        }else
        if(!pass1.getText().toString().trim().equals(pass2.getText().toString().trim())){
            pass2.setError(this.activity.getString(R.string.validacion_passIguales));
        }else
        {
            if(Usuario.verificarDuplicado(email.getText().toString(),dni.getText().toString())){
                Dialogo d = new Dialogo();
                d.setListener(new PedidoAlertListener());
                d.setTitulo(this.activity.getString(R.string.dialogo_registroDuplicadoTitulo));
                d.setMensaje(this.activity.getString(R.string.dialogo_registroDuplicadoMensaje));
                d.show(this.activity.getFragmentManager(),"Registro error");
            }
            else{
                this.saveUserToDB();
            }
        }
    }

    private void saveUserToDB()
    {
        Thread hilo = new Thread(new Hilo(this, HttpConnection.pathUrl+"/usuarios/nuevo",5));
        hilo.start();
    }

    @Override
    public boolean handleMessage(Message msg) {
        try{
            JsonParse jsonParse = new JsonParse();
            HttpConnection httpConnection = new HttpConnection();
            Usuario user = new Usuario(nombre.getText().toString(),apellido.getText().toString(),pass1.getText().toString(),dni.getText().toString(),email.getText().toString());
            String nuevoUsuario = jsonParse.toJsonUser(user);
            httpConnection.getBytesDataByPOST(msg.obj.toString(),nuevoUsuario);
            this.activity.finish();
        }
        catch (Exception e){
            Log.d("CASE5",e.toString());
        }
        return true;
    }
}

