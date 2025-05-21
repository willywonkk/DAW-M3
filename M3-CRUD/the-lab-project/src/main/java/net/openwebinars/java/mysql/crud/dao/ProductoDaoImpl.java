package net.openwebinars.java.mysql.crud.dao;

import net.openwebinars.java.mysql.crud.model.Producto;
import net.openwebinars.java.mysql.crud.pool.MyDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDaoImpl implements ProductoDao {

    private static ProductoDaoImpl instance;

    static {
        instance = new ProductoDaoImpl();
    }

    private ProductoDaoImpl() {}

    public static ProductoDaoImpl getInstance() {
        return instance;
    }

    @Override
    public int add(Producto producto) throws SQLException {
        String sql = """
                    INSERT INTO productos (nombre, precio, categoria_id)
                    VALUES (?, ?, ?);
                """;

        int result;

        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, producto.getNombre());
            pstm.setDouble(2, producto.getPrecio());
            pstm.setInt(3, producto.getCategoriaId());

            result = pstm.executeUpdate();
        }

        return result;
    }

    @Override
    public Producto getById(int id) throws SQLException {
        Producto result = null;

        String sql = "SELECT id, nombre, precio, categoria_id FROM productos WHERE id = ?";

        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, id);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    result = new Producto();
                    result.setId(rs.getInt("id"));
                    result.setNombre(rs.getString("nombre"));
                    result.setPrecio(rs.getDouble("precio"));
                    result.setCategoriaId(rs.getInt("categoria_id"));
                }
            }
        }

        return result;
    }

    @Override
    public List<Producto> getAll() throws SQLException {
        String sql = "SELECT id, nombre, precio, categoria_id FROM productos";
        List<Producto> result = new ArrayList<>();

        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setCategoriaId(rs.getInt("categoria_id"));

                result.add(producto);
            }
        }

        return result;
    }

    @Override
    public int update(Producto producto) throws SQLException {
        String sql = """
                    UPDATE productos SET
                        nombre = ?, 
                        precio = ?, 
                        categoria_id = ?
                    WHERE id = ?;
                """;

        int result;

        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, producto.getNombre());
            pstm.setDouble(2, producto.getPrecio());
            pstm.setInt(3, producto.getCategoriaId());
            pstm.setInt(4, producto.getId());

            result = pstm.executeUpdate();
        }

        return result;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id = ?";

        try (Connection conn = MyDataSource.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, id);
            pstm.executeUpdate();
        }
    }
}
