package net.openwebinars.java.mysql.crud.dao;

import net.openwebinars.java.mysql.crud.model.Producto;
import java.sql.SQLException;
import java.util.List;

public interface ProductoDao {
    // Método para agregar un nuevo producto
    int add(Producto producto) throws SQLException;

    // Método para obtener un producto por su id
    Producto getById(int id) throws SQLException;

    // Método para obtener todos los productos
    List<Producto> getAll() throws SQLException;

    // Método para actualizar un producto existente
    int update(Producto producto) throws SQLException;

    // Método para eliminar un producto por su id
    void delete(int id) throws SQLException;
}