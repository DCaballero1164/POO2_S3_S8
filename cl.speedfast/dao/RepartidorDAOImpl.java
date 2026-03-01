package dao;

import model.Repartidor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAOImpl implements RepartidorDAO {

    @Override
    public boolean create(Repartidor repartidor) {
        String sql = "INSERT INTO repartidor (nombre) VALUES (?)";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, repartidor.getNombre());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Repartidor buscarPorId(int id) {
        String sql = "SELECT * FROM repartidor WHERE id = ?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Repartidor(
                            rs.getInt("id"),
                            rs.getString("nombre")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // si no se encuentra el repartidor
    }

    @Override
    public List<Repartidor> readAll() {
        List<Repartidor> lista = new ArrayList<>();
        String sql = "SELECT * FROM repartidor";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Repartidor(
                        rs.getInt("id"),
                        rs.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


    @Override
    public boolean update(Repartidor repartidor) {
        String sql = "UPDATE repartidor SET nombre = ? WHERE id = ?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, repartidor.getNombre());
            ps.setInt(2, repartidor.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM repartidor WHERE id = ?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}