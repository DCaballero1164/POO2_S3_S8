package controller;

import dao.PedidoDAO;
import dao.RepartidorDAO;
import dao.EntregaDAO;
import model.Pedido;
import model.Repartidor;
import model.Entrega;
import java.util.List;
/*
 * Clase Controlador
 * Coordina las operaciones entre la interfaz gráfica y los DAOs (funciona como capa intermedia).
 * Aquí se centraliza la lógica de negocio.
 */
public class Controlador {
    /**
     * Administa la informacion en la base de datos.
     * @param pedidoDAO objeto Pedido con los datos a guardar
     * @param repartidorDAO objeto Repartidor con los datos a guardar
     * @param entregaDAO objeto Entrega con los datos a guardar
     */
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private RepartidorDAO repartidorDAO = new RepartidorDAO();
    private EntregaDAO entregaDAO = new EntregaDAO();

    // PEDIDOS
    public void registrarPedido(Pedido pedido) {
        pedidoDAO.create(pedido);
    }

    public List<Pedido> obtenerPedidos() {
        return pedidoDAO.readAll();
    }

    public void actualizarPedido(Pedido pedido) {
        pedidoDAO.update(pedido);
    }

    public void eliminarPedido(int id) {
        pedidoDAO.delete(id);
    }

    // REPARTIDORES
    public void registrarRepartidor(Repartidor repartidor) {
        repartidorDAO.create(repartidor);
    }

    public List<Repartidor> obtenerRepartidores() {
        return repartidorDAO.readAll();
    }

    public void actualizarRepartidor(Repartidor repartidor) {
        repartidorDAO.update(repartidor);
    }

    public void eliminarRepartidor(int id) {
        repartidorDAO.delete(id);
    }

    // ENTREGAS
    public void registrarEntrega(Entrega entrega) {
        entregaDAO.create(entrega);
    }

    public List<Entrega> obtenerEntregas() {
        return entregaDAO.readAll();
    }

    public void actualizarEntrega(Entrega entrega) {
        entregaDAO.update(entrega);
    }

    public void eliminarEntrega(int id) {
        entregaDAO.delete(id);
    }
}
