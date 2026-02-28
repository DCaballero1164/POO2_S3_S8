package dao;

import model.Repartidor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAO {

    // Listar todos los repartidores
    public List<Repartidor> listarTodos() {
        List<Repartidor> lista = new ArrayList<>();
        String sql = "SELECT * FROM repartidor";
        try (Connection conn = ConexionDB.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Repartidor(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Guardar un nuevo repartidor
    public void guardar(Repartidor repartidor) {
        String sql = "INSERT INTO repartidor (nombre) VALUES (?)";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, repartidor.getNombre());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Listar repartidores disponibles (no asignados a pedidos en reparto)
    public List<Repartidor> listarDisponibles() {
        List<Repartidor> lista = new ArrayList<>();
        String sql = "SELECT * FROM repartidor r " +
                "WHERE r.id NOT IN (" +
                "   SELECT e.id_repartidor FROM entrega e " +
                "   JOIN pedido p ON e.id_pedido = p.id " +
                "   WHERE p.estado = 'EN_REPARTO'" +
                ")";
        try (Connection conn = ConexionDB.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Repartidor(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
