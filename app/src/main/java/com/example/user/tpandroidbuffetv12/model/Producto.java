package com.example.user.tpandroidbuffetv12.model;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

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


    public Producto(){
        this.nombre = "";
        this.tipoMenu= "";
        this.precio = 0d;
        this.cantidad = 0;
    }

    public Producto(String name, String tipoMenu, Double price, String imagen){
        this.nombre = name;
        this.tipoMenu= tipoMenu;
        this.precio = price;
        this.cantidad = 0;
        this.urlImagen = imagen;

    }

    public Producto(String name, Double price){
        this.nombre = name;
        this.precio = price;
        this.cantidad = 0;
    }

    public static ArrayList<Producto> getListaMenu() {
        return listaMenu;
    }

    public static void setListaMenu(ArrayList<Producto> listaMenu) {
        Producto.listaMenu = listaMenu;
    }

    public static ArrayList<Producto> getListaBebidas() {
        return listaBebidas;
    }

    public static void setListaBebidas(ArrayList<Producto> listaBebidas) {
        Producto.listaBebidas = listaBebidas;
    }

    public static ArrayList<Producto> getListaSnacks() {
        return listaSnacks;
    }

    public static void setListaSnacks(ArrayList<Producto> listaSnacks) {
        Producto.listaSnacks = listaSnacks;
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
    public static ArrayList<Producto> getStaticListMenus() {
        if(listaMenu == null)
        {
            listaMenu = new ArrayList<Producto>();
        }
        return listaMenu;
    }
    public static ArrayList<Producto> getStaticListBebidas() {
        if(listaBebidas == null)
        {
            listaBebidas = new ArrayList<Producto>();
        }
        return listaBebidas;
    }
    public static ArrayList<Producto> getStaticListSnacks(){
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
        if(this.getNombre() != null)
        {    resultado = resultado + hash * this.getNombre().hashCode();}
        resultado = resultado + hash * this.getPrecio().hashCode();
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
    public void reiniciarCantidad()
    {
        this.cantidad=0;
    }


    public String getTipoMenu() {
        return tipoMenu;
    }

    public void setTipoMenu(String tipoMenu) {
        this.tipoMenu = tipoMenu;
    }

    public static void limpiarCantidades()
    {
        List<Producto> superLista = new ArrayList<Producto>();
        superLista.addAll(Producto.getStaticListBebidas());
        superLista.addAll(Producto.getStaticListMenus());
        superLista.addAll(Producto.getStaticListSnacks());

        for (Producto prod: superLista) {
            prod.reiniciarCantidad();
        }
    }
}
