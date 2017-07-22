package com.example.user.tpandroidbuffetv12.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.user.tpandroidbuffetv12.Http.Hilo;
import com.example.user.tpandroidbuffetv12.Http.HttpConnection;
import com.example.user.tpandroidbuffetv12.Http.JsonParse;
import com.example.user.tpandroidbuffetv12.Http.ThreadConexion;
import com.example.user.tpandroidbuffetv12.R;
import com.example.user.tpandroidbuffetv12.activity.LoginActivity;
import com.example.user.tpandroidbuffetv12.activity.MenuActivity;
import com.example.user.tpandroidbuffetv12.activity.RegisterActivity;
import com.example.user.tpandroidbuffetv12.model.Dialogo;
import com.example.user.tpandroidbuffetv12.model.PedidoAlertListener;
import com.example.user.tpandroidbuffetv12.model.Usuario;

/**
 * Created by USER on 22/6/2017.
 */

public class LoginController implements View.OnClickListener
{
    private LoginActivity loginActivity;
    private Button loguear;
    private Button registrar;
    private EditText email;
    private EditText pass;
    private CheckBox remember;
    private static final String RECORDAR = "recordarme";
    private static final String USER = "user";
    private static final String PASS = "pass";
    public static SharedPreferences share;


    public LoginController(LoginActivity loginActivity){

        this.loginActivity = loginActivity;
        share = this.loginActivity.getSharedPreferences("miConfig",Context.MODE_PRIVATE);

        loguear = (Button) this.loginActivity.findViewById(R.id.ingresar);
        registrar = (Button) this.loginActivity.findViewById(R.id.registrar);
        email = (EditText) this.loginActivity.findViewById(R.id.email);
        pass = (EditText) this.loginActivity.findViewById(R.id.clave);
        remember = (CheckBox) this.loginActivity.findViewById(R.id.checkBox);
        loguear.setOnClickListener(this);
        registrar.setOnClickListener(this);

        if(validarRecordado(share)){
            email.setText(share.getString(USER,"a@a.com"));
            pass.setText(share.getString(PASS,"clave"));
            remember.setSelected(share.getBoolean(RECORDAR,false));
            Intent intento = new Intent(this.loginActivity.getApplicationContext(), MenuActivity.class);
            this.loginActivity.startActivity(intento);
        }

    }

    @Override
    public void onClick(View v) {

        Intent intento;
        if(v.getId() == this.loguear.getId())
        {
            SharedPreferences share;
            if (email.getText().toString().isEmpty()) {
                email.setError(this.loginActivity.getString(R.string.validacion_campoVacio));
            }
            if (pass.getText().toString().isEmpty()) {
                pass.setError(this.loginActivity.getString(R.string.validacion_campoVacio));
            }
            if (Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()) {
                if(Usuario.verificarUsuario(email.getText().toString(),pass.getText().toString())) {
                    if (remember.isChecked())
                    {
                        share = this.loginActivity.getSharedPreferences("miConfig", Context.MODE_PRIVATE);
                        SharedPreferences.Editor e  = share.edit();
                        e.putBoolean(RECORDAR, true);
                        e.putString(USER, email.getText().toString());
                        e.putString(PASS, pass.getText().toString());
                        e.commit();
                        Log.d("spa", "pase x aca");
                    }
                    loadUserFromDB();
                    Log.d("aca", "eaea");
                    intento = new Intent(this.loginActivity.getApplicationContext(), MenuActivity.class);
                    this.loginActivity.startActivity(intento);
                }
                else
                {
                    Dialogo d = new Dialogo();
                    d.setListener(new PedidoAlertListener());
                    d.setTitulo("Login error");
                    d.setMensaje(this.loginActivity.getString(R.string.dialogo_loginusererror));
                    d.show(this.loginActivity.getFragmentManager(),"Error logueo");
                }
            } else {
                email.setError(this.loginActivity.getString(R.string.validacion_loginemail));
            }
        }
        if (v.getId() == this.registrar.getId())
        {
            intento = new Intent(this.loginActivity.getApplicationContext(), RegisterActivity.class);
            this.loginActivity.startActivity(intento);
        }
    }

    public static String[] getData(){
        String[] array = {USER,PASS,RECORDAR};
        return array;
    }

    private boolean validarRecordado(SharedPreferences sharedPreferences){
        return share.getBoolean(RECORDAR,false);
    }

    private void loadUserFromDB()
    {
        ThreadConexion threadConexion = new ThreadConexion();
        Hilo hilo = new Hilo(threadConexion, HttpConnection.pathUrl+"/usuarios/"+email.getText().toString(),6);
        hilo.run();
    }

}