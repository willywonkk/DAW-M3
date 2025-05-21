package net.openwebinars.java.mysql.crud.dao;

import net.openwebinars.java.mysql.crud.model.Categoria;
import java.sql.SQLException;
import java.util.List;

public interface CategoriaDao {
    // Método para obtener todas las categorías
    List<Categoria> getAll() throws SQLException;

    // Método para obtener una categoría por su id
    Categoria getById(int id) throws SQLException;

    // Método para agregar una nueva categoría
    int add(Categoria categoria) throws SQLException;

    // Método para actualizar una categoría existente
    int update(Categoria categoria) throws SQLException;

    // Método para eliminar una categoría por su id
    void delete(int id) throws SQLException;
}