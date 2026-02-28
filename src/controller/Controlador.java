package controller;

import dao.PedidoDAO;
import dao.RepartidorDAO;
import dao.EntregaDAO;
import model.Pedido;
import model.Repartidor;
import model.Entrega;
import java.util.List;

public class Controlador {
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private RepartidorDAO repartidorDAO = new RepartidorDAO();
    private EntregaDAO entregaDAO = new EntregaDAO();

    public void registrarPedido(Pedido pedido) {
        pedidoDAO.guardar(pedido);
    }

    public List<Repartidor> obtenerRepartidores() {
        return repartidorDAO.listarTodos();
    }

    public void registrarEntrega(Entrega entrega) {
        entregaDAO.guardar(entrega);
    }
}
