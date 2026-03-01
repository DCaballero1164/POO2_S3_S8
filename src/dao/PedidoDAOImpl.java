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
        return null;
    }

    @Override
    public List<Pedido> readAll() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";

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
            e.printStackTrace(); // se informa el error
        }

        return pedidos; // (si hubo error, se devuelve lista vacía)
    }



    @Override
    public boolean update(Pedido pedido) {
        String sql = "UPDATE pedidos SET estado = ? WHERE id = ?";
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nuevoEstado.name()); // enum → String
            ps.setInt(2, idPedido);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null,
                        "Estado del pedido actualizado correctamente.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null,
                        "No se encontró el pedido con ID: " + idPedido);
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al actualizar estado: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


    @Override
    public boolean delete(Pedido pedido) {
        return false;
    }

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





