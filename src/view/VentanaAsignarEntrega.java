package view;

import controller.Controlador;
import model.Pedido;
import model.Entrega;
import model.EstadoPedido;
import model.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class VentanaAsignarEntrega extends JFrame {
    private JPanel panel1;
    private JPanel mainPanel;
    private JTable tablaRepartidores;
    private JTable tablaPedidos;
    private JButton ejecutarAcciónButton;
    private JTable tablaHistorialRepartidores;
    private JComboBox<String> comboBox1;

    private Controlador controlador;
    private DefaultTableModel modeloHistorial;
    private VentanaListaPedidos ventanaListaPedidos; // referencia a la ventana de lista de pedidos

    public VentanaAsignarEntrega(Controlador controlador, VentanaListaPedidos ventanaListaPedidos) {
        this.controlador = controlador;
        this.ventanaListaPedidos = ventanaListaPedidos;

        setTitle("Asignar Repartidor / Iniciar Entrega");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panel1);
        setSize(900, 600);
        setLocationRelativeTo(null);

        modeloHistorial = new DefaultTableModel(
                new Object[]{"Dirección", "Tipo", "Estado", "Fecha", "Hora", "Repartidor"}, 0);
        tablaHistorialRepartidores.setModel(modeloHistorial);
        tablaHistorialRepartidores.getTableHeader().setVisible(true);

        cargarPedidosPendientes();
        cargarRepartidoresDisponibles();
        cargarHistorialEntregas();

        ejecutarAcciónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accion = (String) comboBox1.getSelectedItem();

                if ("Realizar Entrega".equals(accion)) {
                    asignarEntrega();
                } else if ("Agregar Repartidor".equals(accion)) {
                    new VentanaAgregarRepartidor(controlador, VentanaAsignarEntrega.this).setVisible(true);
                } else if ("Eliminar Repartidor".equals(accion)) {
                    eliminarRepartidorSeleccionado();
                }
            }
        });
    }

    // Setter para enlazar la ventana de lista de pedidos después de instanciar
    public void setVentanaListaPedidos(VentanaListaPedidos ventanaListaPedidos) {
        this.ventanaListaPedidos = ventanaListaPedidos;
    }

    public void cargarPedidosPendientes() {
        List<Pedido> pedidosPendientes = controlador.obtenerPedidos().stream()
                .filter(p -> p.getEstado() == EstadoPedido.PENDIENTE)
                .toList();

        DefaultTableModel modeloPedidos = new DefaultTableModel(
                new Object[]{"ID", "Dirección", "Tipo", "Estado"}, 0);

        for (Pedido p : pedidosPendientes) {
            modeloPedidos.addRow(new Object[]{
                    p.getId(),
                    p.getDireccion(),
                    p.getTipo(),
                    p.getEstado()
            });
        }
        tablaPedidos.setModel(modeloPedidos);
        tablaPedidos.getTableHeader().setVisible(true);
    }

    public void cargarRepartidoresDisponibles() {
        List<Repartidor> repartidores = controlador.obtenerRepartidores();

        DefaultTableModel modeloRepartidores = new DefaultTableModel(
                new Object[]{"ID", "Nombre"}, 0);

        for (Repartidor r : repartidores) {
            modeloRepartidores.addRow(new Object[]{
                    r.getId(),
                    r.getNombre()
            });
        }
        tablaRepartidores.setModel(modeloRepartidores);
        tablaRepartidores.getTableHeader().setVisible(true);
    }

    public void cargarHistorialEntregas() {
        modeloHistorial.setRowCount(0);
        List<Entrega> entregas = controlador.obtenerEntregas();

        for (Entrega e : entregas) {
            Pedido p = controlador.buscarPedidoPorId(e.getIdPedido());
            Repartidor r = controlador.buscarRepartidorPorId(e.getIdRepartidor());

            modeloHistorial.addRow(new Object[]{
                    (p != null ? p.getDireccion() : "Pedido eliminado"),
                    (p != null ? p.getTipo() : "-"),
                    (p != null ? p.getEstado() : "-"),
                    e.getFecha(),
                    e.getHora(),
                    (r != null ? r.getNombre() : "Repartidor eliminado")
            });
        }
        tablaHistorialRepartidores.setModel(modeloHistorial);
        tablaHistorialRepartidores.getTableHeader().setVisible(true);
    }

    public void asignarEntrega() {
        int filaPedido = tablaPedidos.getSelectedRow();
        int filaRepartidor = tablaRepartidores.getSelectedRow();

        if (filaPedido == -1 || filaRepartidor == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un pedido y un repartidor.");
            return;
        }

        int idPedido = (int) tablaPedidos.getValueAt(filaPedido, 0);
        int idRepartidor = (int) tablaRepartidores.getValueAt(filaRepartidor, 0);

        Entrega nuevaEntrega = new Entrega(
                idPedido,
                idRepartidor,
                new Date(System.currentTimeMillis()),
                new Time(System.currentTimeMillis())
        );

        boolean guardadoEntrega = controlador.registrarEntrega(nuevaEntrega);

        Pedido pedido = controlador.buscarPedidoPorId(idPedido);
        if (pedido != null) {
            pedido.setEstado(EstadoPedido.EN_REPARTO);
            controlador.actualizarPedido(pedido);
        }

        if (guardadoEntrega && pedido != null) {
            JOptionPane.showMessageDialog(this, "Entrega asignada correctamente.");
            if (ventanaListaPedidos != null) {
                ventanaListaPedidos.cargarPedidos(); // refrescar lista de pedidos
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error al asignar la entrega.");
        }

        cargarPedidosPendientes();
        cargarRepartidoresDisponibles();
        cargarHistorialEntregas();
    }

    public void eliminarRepartidorSeleccionado() {
        int fila = tablaRepartidores.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un repartidor para eliminar.");
            return;
        }

        int idRepartidor = (int) tablaRepartidores.getValueAt(fila, 0);
        boolean eliminado = controlador.eliminarRepartidor(idRepartidor);

        if (eliminado) {
            JOptionPane.showMessageDialog(this, "Repartidor eliminado correctamente.");
            cargarRepartidoresDisponibles();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar repartidor.");
        }
    }
}
