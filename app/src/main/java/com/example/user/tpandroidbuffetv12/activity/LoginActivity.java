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
       setContentView(R.layout.activity_login);
        loguear = (Button) findViewById(R.id.ingresar);
        registrar = (Button) findViewById(R.id.registrar);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.clave);
        remember = (CheckBox) findViewById(R.id.checkBox);
        loguear.setOnClickListener(this);
        registrar.setOnClickListener(this);

        SharedPreferences share = getSharedPreferences("miConfig",MODE_PRIVATE);
        email.setText(share.getString(USER,""));
        pass.setText(share.getString(PASS,""));
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
            if (pass.getText().toString().isEmpty()) {
                pass.setError("El campo clave no puede estar vacio");
            }
            if (email.getText().toString().isEmpty()) {
                email.setError("El campo email no puede estar vacio");
            }
            if (Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()) {
                if (remember.isChecked()) {
                    share = getSharedPreferences("miConfig", MODE_PRIVATE);
                    share.edit().putBoolean(RECORDAR, true);
                    share.edit().putString(USER, email.getText().toString());
                    share.edit().putString(PASS, pass.getText().toString());
                    share.edit().apply();
                    Log.d("spa", "pase x aca");
                }
                //mandame a otro lado papa
                Log.d("aca llege", "eaea");
                intento = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intento);
            } else {
                email.setError("Formato inválido");
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
