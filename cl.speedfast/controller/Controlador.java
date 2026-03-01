package controller;

import dao.PedidoDAO;
import dao.PedidoDAOImpl;
import dao.RepartidorDAO;
import dao.RepartidorDAOImpl;
import dao.EntregaDAO;
import dao.EntregaDAOImpl;
import model.Pedido;
import model.Repartidor;
import model.Entrega;
import java.util.List;

/**
 * Clase Controlador
 * Coordina las operaciones entre la interfaz gráfica y los DAOs (capa intermedia).
 * Centraliza la lógica de negocio y delega la persistencia a los DAOs.
 */
public class Controlador {

    private PedidoDAO pedidoDAO = new PedidoDAOImpl();
    private RepartidorDAO repartidorDAO = new RepartidorDAOImpl();
    private EntregaDAO entregaDAO = new EntregaDAOImpl();

    // PEDIDOS
    public boolean registrarPedido(Pedido pedido) {
        return pedidoDAO.create(pedido);
    }

    public List<Pedido> obtenerPedidos() {
        return pedidoDAO.readAll();
    }

    public Pedido buscarPedidoPorId(int id) {
        return pedidoDAO.buscarPorId(id);
    }

    public boolean actualizarPedido(Pedido pedido) {
        return pedidoDAO.update(pedido);
    }

    public boolean eliminarPedido(int id) {
        return pedidoDAO.delete(id);
    }

    // REPARTIDORES
    public boolean registrarRepartidor(Repartidor repartidor) {
        return repartidorDAO.create(repartidor);
    }

    public List<Repartidor> obtenerRepartidores() {
        return repartidorDAO.readAll();
    }

    public Repartidor buscarRepartidorPorId(int id) {
        return repartidorDAO.buscarPorId(id);
    }

    public boolean actualizarRepartidor(Repartidor repartidor) {
        return repartidorDAO.update(repartidor);
    }

    public boolean eliminarRepartidor(int id) {
        return repartidorDAO.delete(id);
    }

    // ENTREGAS
    public boolean registrarEntrega(Entrega entrega) {
        return entregaDAO.create(entrega);
    }

    public List<Entrega> obtenerEntregas() {
        return entregaDAO.readAll();
    }

    public Entrega buscarEntregaPorId(int id) {
        return entregaDAO.buscarPorId(id);
    }

    public boolean actualizarEntrega(Entrega entrega) {
        return entregaDAO.update(entrega);
    }

    public boolean eliminarEntrega(int id) {
        return entregaDAO.delete(id);
    }
}
