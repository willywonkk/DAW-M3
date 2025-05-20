package net.openwebinars.java.mysql.crud.model;

import java.util.Objects;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int categoriaId;

    public Producto() { }

    public Producto(String nombre, double precio, int categoriaId) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoriaId = categoriaId;
    }

    public Producto(int id, String nombre, double precio, int categoriaId) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoriaId = categoriaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id &&
                Double.compare(producto.precio, precio) == 0 &&
                categoriaId == producto.categoriaId &&
                Objects.equals(nombre, producto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, precio, categoriaId);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre +
                ", precio=" + precio +
                ", categoriaId=" + categoriaId +
                '}';
    }
}