package com.example.user.tpandroidbuffetv12.model;

import java.util.ArrayList;

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
    private static ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();

    public Usuario(String email, String pass)
    {
        this.email = email;
        this.contraseña = pass;
    }
    public Usuario(String nombre,String apellido,String contraseña,String dni,String email)
    {
        this.apellido = apellido;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.email = email;
        this.DNI = dni;
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

    public static void cargarUsuarios()
    {
        listaUsuario.add(new Usuario("user1","test","user1","123456","user1@hotmail.com"));
        listaUsuario.add(new Usuario("user2","test","user1","123456","user2@hotmail.com"));
        listaUsuario.add(new Usuario("user3","test","user1","123456","user3@hotmail.com"));
        listaUsuario.add(new Usuario("user4","test","user1","123456","user4@hotmail.com"));
        listaUsuario.add(new Usuario("user5","test","user1","123456","user5@hotmail.com"));
    }
    public static boolean verificarDuplicado(String email,String dni)
    {
        for (Usuario user : listaUsuario)
        {
            if(user.getEmail().equals(email) || user.getDNI().equals(dni))
                return true;
        }
        return false;
    }

    public static boolean verificarUsuario(String email,String pass)
    {
        for (Usuario user : listaUsuario)
        {
            if(user.getEmail().equals(email) && user.getContraseña().equals(pass))
                return true;
        }
        return false;
    }
}
