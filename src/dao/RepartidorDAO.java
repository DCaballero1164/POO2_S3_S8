package dao;

import model.Repartidor;

import java.util.List;

public interface RepartidorDAO {
    // CRUD (create,read,update,delete)
    boolean create(Repartidor repartidor);

    Repartidor buscarPorId(int id);

    List<Repartidor> readAll();

    boolean update(Repartidor repartidor);

    boolean delete(Repartidor repartidor);

}
