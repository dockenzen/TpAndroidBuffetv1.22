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

import com.example.user.tpandroidbuffetv12.Http.JsonParse;
import com.example.user.tpandroidbuffetv12.Http.ThreadConexion;
import com.example.user.tpandroidbuffetv12.R;
import com.example.user.tpandroidbuffetv12.controller.LoginController;
import com.example.user.tpandroidbuffetv12.model.Dialogo;
import com.example.user.tpandroidbuffetv12.model.PedidoAlertListener;
import com.example.user.tpandroidbuffetv12.model.Usuario;

import java.util.List;

public class LoginActivity extends AppCompatActivity  {

    LoginController lg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.lg = new LoginController(this);
    }


}
