package dao;

import model.Pedido;

import java.util.List;

public interface PedidoDAO {

    // CRUD (create,read,update,delete)
    boolean create(Pedido pedido);

    Pedido buscarPorId(int id);

    List<Pedido> readAll();

    boolean update(Pedido pedido);

    boolean delete(int id);


}

