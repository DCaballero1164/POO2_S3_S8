package dao;

import model.Entrega;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {

    // CREATE
    public void guardar(Entrega entrega) {
        String sql = "INSERT INTO entregas (id_pedido, id_repartidor, fecha, hora) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, entrega.getIdPedido());
            ps.setInt(2, entrega.getIdRepartidor());
            ps.setDate(3, entrega.getFecha());
            ps.setTime(4, entrega.getHora());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (listar todas las entregas con datos de pedido y repartidor)
    public List<Entrega> listar() {
        List<Entrega> entregas = new ArrayList<>();
        String sql = "SELECT e.id, e.id_pedido, e.id_repartidor, e.fecha, e.hora, " +
                "p.direccion, p.tipo, p.estado, r.nombre AS repartidor " +
                "FROM entregas e " +
                "JOIN pedidos p ON e.id_pedido = p.id " +
                "JOIN repartidores r ON e.id_repartidor = r.id";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Entrega entrega = new Entrega(
                        rs.getInt("id"),
                        rs.getInt("id_pedido"),
                        rs.getInt("id_repartidor"),
                        rs.getDate("fecha"),
                        rs.getTime("hora")
                );
                // Campos adicionales para mostrar en la tabla
                entrega.setDireccionPedido(rs.getString("direccion"));
                entrega.setTipoPedido(rs.getString("tipo"));
                entrega.setEstadoPedido(rs.getString("estado"));
                entrega.setNombreRepartidor(rs.getString("repartidor"));

                entregas.add(entrega);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entregas;
    }

    // UPDATE
    public void actualizar(Entrega entrega) {
        String sql = "UPDATE entregas SET id_pedido=?, id_repartidor=?, fecha=?, hora=? WHERE id=?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, entrega.getIdPedido());
            ps.setInt(2, entrega.getIdRepartidor());
            ps.setDate(3, entrega.getFecha());
            ps.setTime(4, entrega.getHora());
            ps.setInt(5, entrega.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void eliminar(int id) {
        String sql = "DELETE FROM entregas WHERE id=?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
