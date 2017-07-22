package com.example.user.tpandroidbuffetv12.model;

import com.example.user.tpandroidbuffetv12.Http.Hilo;
import com.example.user.tpandroidbuffetv12.Http.HttpConnection;
import com.example.user.tpandroidbuffetv12.Http.ThreadConexion;
import com.example.user.tpandroidbuffetv12.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 13/5/2017.
 */

public class Usuario
{
    private String nombre;
    private String apellido;
    private String contraseña;
    private String DNI;
    private String email;
    public static Usuario user;
    public static boolean esta = false;

    public static List<Usuario> listaUsuario = new ArrayList<Usuario>();

    public Usuario(String email, String pass)
    {
        this.setEmail(email);
        this.setContraseña(pass);
    }
    public Usuario(String nombre,String apellido,String contraseña,String dni,String email)
    {
        this.setApellido(apellido);
        this.setNombre(nombre);
        this.setContraseña(contraseña);
        this.setEmail(email);
        this.setDNI(dni);
    }

    public static void setListaUsuario(ArrayList<Usuario> listaUsuario) {
        Usuario.listaUsuario = listaUsuario;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static boolean verificarDuplicado(String email,String dni) {
        for (Usuario user : listaUsuario)
        {
            if(user.getEmail().equals(email) || user.getDNI().equals(dni))
                return true;
        }
        return false;
    }

    public static boolean verificarUsuario(String email,String pass) {
        ThreadConexion threadConexion = new ThreadConexion();
        Hilo hilo = new Hilo(threadConexion, HttpConnection.pathUrl+"/usuarios/"+email+"/"+pass,2);
        hilo.run();
        return esta;
    }
}
