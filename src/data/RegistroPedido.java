package data;

import dao.PedidoDAO;
import model.Pedido;
import java.util.List;

public class RegistroPedido {
    private PedidoDAO pedidoDAO;

    public RegistroPedido() {
        pedidoDAO = new PedidoDAO();
    }

    // Guardar pedido en la BD
    public void agregarPedido(Pedido pedido) {
        pedidoDAO.guardar(pedido);
    }

    // Obtener todos los pedidos desde la BD
    public List<Pedido> getPedidos() {
        return pedidoDAO.listarTodos();
    }
}
