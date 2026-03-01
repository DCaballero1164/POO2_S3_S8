package dao;

import model.Entrega;
import java.util.List;

public interface EntregaDAO {
    // CRUD (create,read,update,delete)
    boolean create(Entrega entrega);

    Entrega buscarPorId(int id);

    List<Entrega> readAll();

    boolean update(Entrega entrega);

    boolean delete(Entrega entrega);
}