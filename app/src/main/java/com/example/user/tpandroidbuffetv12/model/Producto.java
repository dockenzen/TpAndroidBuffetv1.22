package com.example.user.tpandroidbuffetv12.model;

import java.util.ArrayList;

/**
 * Created by USER on 2/5/2017.
 */

public class Producto {
    private Integer id;
    private String nombre;
    private Double precio;
    private Integer cantidad;
    private String urlImagen;
    private byte[] imagenDescargada;

    private static ArrayList<Producto> listaMenu;
    private static ArrayList<Producto> listaBebidas;
    private static ArrayList<Producto> listaSnacks;


    public Producto(){

    }

    public Producto(String name, Double price){
        this.setNombre(name);
        this.setPrecio(price);

    }

    public Producto(String name, Double price, Integer cantidad){
        this.setNombre(name);
        this.setPrecio(price);
        this.cantidad = cantidad;
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
            listaMenu.add(new Producto("Pizza", 12.50,1,"https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg/220px-Eq_it-na_pizza-margherita_sep2005_sml.jpg"));
            listaMenu.add(new Producto("Tostados",25.0,1,"https://www.cater-line.com/wp-content/uploads/2014/06/SCM-Sandw.Club-Mixto-Tostado-Caliente.png"));
            listaMenu.add(new Producto("Frutas",30.0,1,"http://www.frutasdelfraile.com/wp-content/uploads/2016/05/fruta-frutas-del-fraile.jpg"));
            listaMenu.add(new Producto("Empanadas",16.0,1,"http://planetaempanada.com/pe/wp-content/uploads/2014/08/Empanadas-planeta.png"));
            listaMenu.add(new Producto("Hamburguesa",80.0,1,"http://bk-latam-prod.s3.amazonaws.com/sites/burgerking.cl/files/BK_300x270_hamburguesa_deluxe_queso.png"));
        }
        return listaMenu;
    }
    public static ArrayList<Producto> getStaticListBebidas()
    {
        if(listaBebidas == null)
        {
            listaBebidas = new ArrayList<Producto>();
            listaBebidas.add(new Producto("Coca Cola", 20.00,1,"http://www.36700.suborder.de/images/shop_produkte/ktvdg9v2eb/80.png"));
            listaBebidas.add(new Producto("Sprite",20.0,1,"http://www.sprite.com.ar/content/dam/GO/sprite/argentina/products/products_Sprite_1.5L.jpg"));
            listaBebidas.add(new Producto("Agua Mineral",18.0,1,"http://www.ballena.com.ar/images/201408/goods_img/1764_G_1408849750701.jpg"));
            listaBebidas.add(new Producto("Pepsi",15.0,1,"http://www.cdparque.com.ar/img/sections/productos/pepsi.png"));
            listaBebidas.add(new Producto("Gatorei",45.0,1,"https://www.dollartree.com/assets/product_images_2016/styles/xlarge/837558.jpg"));
        }
        return listaBebidas;
    }
    public static ArrayList<Producto> getStaticListSnacks()
    {
        if(listaSnacks == null)
        {
            listaSnacks = new ArrayList<Producto>();
            listaSnacks.add(new Producto("Lays", 15.50,1,"http://2.bp.blogspot.com/_tQVckLGToNA/TB_rn9LxlZI/AAAAAAAAEjk/mpV31bXbmgU/s1600/LAYS_Classic.gif"));
            listaSnacks.add(new Producto("Saladix",25.0,1,"http://www.arcor.com.ar/images/historiaMarcas-saladix03.jpg"));
            listaSnacks.add(new Producto("Cheetos",30.0,1,"http://www.cheetos.com.mx/imgs/productos/big/cheetos-1.png"));
            listaSnacks.add(new Producto("3D",15.0,1,"http://supermundialonline.com/26911-home_default/pepsico-3d-megatube-38-grs.jpg"));
            listaSnacks.add(new Producto("Doritos",45.0,1,"http://www.fritolay.com/images/default-source/blue-bag-image/doritos-nacho-cheese.png?sfvrsn=2"));
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

    public byte[] getImagenDescargada() {
        return imagenDescargada;
    }

    public void setImagenDescargada(byte[] imagenDescargada) {
        this.imagenDescargada = imagenDescargada;
    }
}
