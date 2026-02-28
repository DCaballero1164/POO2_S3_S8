package dao;

import model.Pedido;
import model.TipoPedido;
import model.EstadoPedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public void guardar(Pedido pedido) {
        // Nombre correcto de la tabla: "pedido"
        String sql = "INSERT INTO pedido (direccion, tipo, estado) VALUES (?, ?, ?)";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pedido.getDireccion());
            ps.setString(2, pedido.getTipo().name());
            ps.setString(3, pedido.getEstado().name());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Pedido> listarTodos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido";
        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getInt("id"),
                        rs.getString("direccion"),
                        TipoPedido.valueOf(rs.getString("tipo")),
                        EstadoPedido.valueOf(rs.getString("estado"))
                );
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    public void actualizarEstado(int idPedido, EstadoPedido nuevoEstado) {
        String sql = "UPDATE pedido SET estado = ? WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado.name()); // enum → String
            ps.setInt(2, idPedido);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

