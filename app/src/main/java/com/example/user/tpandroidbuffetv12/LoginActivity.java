package com.example.user.tpandroidbuffetv12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.user.tpandroidbuffetv12.models.LoginModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loguear;
    private Button registrar;
    private EditText email;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loguear = (Button) findViewById(R.id.ingresar);
        registrar = (Button) findViewById(R.id.registrar);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.clave);
        loguear.setOnClickListener(this);
        registrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(pass.getText().toString().isEmpty()){
            pass.setError("El campo clave no puede estar vacio");
        }
        if(email.getText().toString().isEmpty()){
            email.setError("El campo email no puede estar vacio");
        }
            if(Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches())
            {
                //mandame a otro lado papa
                Log.d("aca llege","eaea");
            }
            else
            {
                email.setError("Formato inválido");
            }
    }
}
