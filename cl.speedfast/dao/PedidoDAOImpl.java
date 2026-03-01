package dao;

import model.EstadoPedido;
import model.Pedido;
import model.TipoPedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOImpl implements PedidoDAO {
    // CRUD (create,read,update,delete)
    @Override
    public boolean create(Pedido pedido) {
        // Nombre correcto de la tabla: "pedido"
        String sql = "INSERT INTO pedido (direccion, tipo, estado) VALUES (?, ?, ?)";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pedido.getDireccion());
            ps.setString(2, pedido.getTipo().name());
            ps.setString(3, pedido.getEstado().name());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Pedido buscarPorId(int id) {
        String sql = "SELECT * FROM pedido WHERE id = ?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Pedido(
                            rs.getInt("id"),
                            rs.getString("direccion"),
                            TipoPedido.valueOf(rs.getString("tipo")),
                            EstadoPedido.valueOf(rs.getString("estado"))
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // si no se encuentra el pedido
    }


    @Override
    public List<Pedido> readAll() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {     // Mientras el siguiente valor exista dentro de la lista entonces:
                Pedido pedido = new Pedido(
                        rs.getInt("id"),
                        rs.getString("direccion"),
                        TipoPedido.valueOf(rs.getString("tipo")),
                        EstadoPedido.valueOf(rs.getString("estado"))
                );
                pedidos.add(pedido);    // Agrega el pedido a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // mensaje donde se informa el error
        }

        return pedidos; // retorna una lista vacia al no encontrar pedido
    }



    @Override
    public boolean update(Pedido pedido) {
        String sql = "UPDATE pedido SET direccion = ?, tipo = ?, estado = ? WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pedido.getDireccion());       // actualiza dirección
            ps.setString(2, pedido.getTipo().name());     // enum TipoPedido → String
            ps.setString(3, pedido.getEstado().name());   // enum EstadoPedido → String
            ps.setInt(4, pedido.getId());                 // id del pedido

            int filas = ps.executeUpdate();
            return filas > 0; // true si se actualizó al menos una fila

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM pedido WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id); // usa el id recibido como parámetro

            int filas = ps.executeUpdate();
            return filas > 0; // retorna true si se eliminó al menos una fila

        } catch (SQLException e) {
            e.printStackTrace();    // mensaje
            return false;
        }
    }

}






