package com.example.user.tpandroidbuffetv12.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by USER on 2/5/2017.
 */

public class Producto {
    private Integer id;
    private String nombre;
    private String tipoMenu;
    private Double precio;
    private Integer cantidad;

    private String urlImagen;
    private Bitmap imagenDescargada;

    private static ArrayList<Producto> listaMenu;
    private static ArrayList<Producto> listaBebidas;
    private static ArrayList<Producto> listaSnacks;


    public Producto(){ }

    public Producto(String name, String tipoMenu, Double price, String imagen){
        this.setNombre(name);
        this.setPrecio(price);
        this.tipoMenu = tipoMenu;
        this.urlImagen = imagen;

    }

    public Producto(String name, Double price){
        this.setNombre(name);
        this.setPrecio(price);
    }

    public Producto(String name, Double price, Integer cantidad,String url){
        this.setNombre(name);
        this.setPrecio(price);
        this.cantidad = cantidad;
        this.setUrlImagen(url);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static ArrayList<Producto> getStaticListMenus()
    {
        if(listaMenu == null)
        {
            listaMenu = new ArrayList<Producto>();
        }
        return listaMenu;
    }

    public static ArrayList<Producto> getStaticListBebidas()
    {
        if(listaBebidas == null)
        {
            listaBebidas = new ArrayList<Producto>();
        }
        return listaBebidas;
    }
    public static ArrayList<Producto> getStaticListSnacks()
    {
        if(listaSnacks == null)
        {
            listaSnacks = new ArrayList<Producto>();
        }
        return listaSnacks;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.hashCode() == obj.hashCode())
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {

        int hash = 7;
        int resultado = 0;
        if(this.nombre != null)
        {    resultado = resultado + hash * this.nombre.hashCode();}
        resultado = resultado + hash * this.precio.hashCode();
        return resultado;
    }



    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad += cantidad;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Bitmap getImagenDescargada() {
        return imagenDescargada;
    }

    public void setImagenDescargada(Bitmap imagenDescargada) {
        this.imagenDescargada = imagenDescargada;
    }
}
