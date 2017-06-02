package com.example.user.tpandroidbuffetv12.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.user.tpandroidbuffetv12.R;
import com.example.user.tpandroidbuffetv12.model.Dialogo;
import com.example.user.tpandroidbuffetv12.model.PedidoAlertListener;
import com.example.user.tpandroidbuffetv12.model.Usuario;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String RECORDAR = "recordarme";
    private static final String USER = "user";
    private static final String PASS = "pass";

    private Button loguear;
    private Button registrar;
    private EditText email;
    private EditText pass;
    private CheckBox remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Usuario.cargarUsuarios();
        setContentView(R.layout.activity_login);
        loguear = (Button) findViewById(R.id.ingresar);
        registrar = (Button) findViewById(R.id.registrar);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.clave);
        remember = (CheckBox) findViewById(R.id.checkBox);
        loguear.setOnClickListener(this);
        registrar.setOnClickListener(this);

        SharedPreferences share = getSharedPreferences("miConfig",MODE_PRIVATE);
        email.setText(share.getString(USER,"user1@hotmail.com"));
        pass.setText(share.getString(PASS,"user1"));
        remember.setSelected(share.getBoolean(RECORDAR,false));


        Log.d("spa",share.getString(USER,""));

        Log.d("spa","sadasdds");
    }

    @Override
    public void onClick(View v) {

        Intent intento;
        if(v.getId() == this.loguear.getId())
        {
            SharedPreferences share;
            if (email.getText().toString().isEmpty()) {
                email.setError(getString(R.string.validacion_campoVacio));
            }
            if (pass.getText().toString().isEmpty()) {
                pass.setError(getString(R.string.validacion_campoVacio));
            }
            if (Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()) {
                if(Usuario.verificarUsuario(email.getText().toString(),pass.getText().toString())) {
                    if (remember.isChecked())
                    {
                        share = getSharedPreferences("miConfig", MODE_PRIVATE);
                        SharedPreferences.Editor e  = share.edit();
                        e.putBoolean(RECORDAR, true);
                        e.putString(USER, email.getText().toString());
                        e.putString(PASS, pass.getText().toString());
                        e.commit();
                        Log.d("spa", "pase x aca");
                    }
                    Log.d("aca llege", "eaea");
                    intento = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(intento);
                }
                else
                {
                    Dialogo d = new Dialogo();
                    d.setListener(new PedidoAlertListener());
                    d.setTitulo("Login error");
                    d.setMensaje(getString(R.string.dialogo_loginusererror));
                    d.show(getFragmentManager(),"Error logueo");
                }
            } else {
                email.setError(getString(R.string.validacion_loginemail));
            }
        }
        if (v.getId() == this.registrar.getId())
        {
            intento = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intento);
        }
    }


    public static String[] getData(){
        String[] array = {USER,PASS,RECORDAR};
        return array;
    }
}
