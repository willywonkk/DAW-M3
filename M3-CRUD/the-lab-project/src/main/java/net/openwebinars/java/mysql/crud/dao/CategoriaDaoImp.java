package net.openwebinars.java.mysql.crud.dao;

import net.openwebinars.java.mysql.crud.model.Categoria;
import net.openwebinars.java.mysql.crud.pool.MyDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDaoImp implements CategoriaDao {

    private static CategoriaDaoImp instance;

    // Instanciación estática del Singleton
    static {
        instance = new CategoriaDaoImp();
    }

    // Constructor privado para evitar instanciación externa
    private CategoriaDaoImp() {}

    // Método público para obtener la instancia del Singleton
    public static CategoriaDaoImp getInstance() {
        return instance;
    }

    @Override
    public int add(Categoria categoria) throws SQLException {
        String sql = """
                    INSERT INTO categorias (nombre)
                    VALUES (?);
                """;

        int result;

        // Usamos el pool de conexiones para obtener una conexión
        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, categoria.getNombre()); // Insertamos el nombre de la categoría
            result = pstm.executeUpdate();
        }

        return result;
    }

    @Override
    public Categoria getById(int id) throws SQLException {
        Categoria result = null;

        String sql = "SELECT * FROM categorias WHERE id = ?";

        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, id);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    result = new Categoria();
                    result.setId(rs.getInt("id"));
                    result.setNombre(rs.getString("nombre"));
                }
            }
        }

        return result;
    }

    @Override
    public List<Categoria> getAll() throws SQLException {
        String sql = "SELECT * FROM categorias";
        List<Categoria> result = new ArrayList<>();

        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));

                result.add(categoria);
            }
        }

        return result;
    }

    @Override
    public int update(Categoria categoria) throws SQLException {
        String sql = """
                    UPDATE categorias SET
                        nombre = ?
                    WHERE id = ?;
                """;

        int result;

        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, categoria.getNombre());
            pstm.setInt(2, categoria.getId());

            result = pstm.executeUpdate();
        }

        return result;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM categorias WHERE id = ?";

        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, id);
            pstm.executeUpdate();
        }
    }
}