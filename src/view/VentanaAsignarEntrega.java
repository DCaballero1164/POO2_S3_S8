package view;

import dao.PedidoDAO;
import dao.EntregaDAO;
import model.Pedido;
import model.Entrega;
import model.EstadoPedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class VentanaAsignarEntrega extends JFrame {
    // Componentes vinculados al .form
    private JPanel panel1;             // Panel raíz del formulario
    private JPanel mainPanel;          // Panel interno donde se organizan los elementos
    private JTable tablaRepartidores;  // Tabla para mostrar repartidores
    private JTable tablaPedidos;       // Tabla para mostrar pedidos
    private JButton asignarButton;     // Botón para asignar entrega
    private JButton actualizarButton;  // Botón para refrescar datos
    private JTable tablaHistorialRepatidores;

    public VentanaAsignarEntrega() {
        setTitle("Asignar Repartidor / Iniciar Entrega");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panel1);   // Usa el panel raíz del .form
        setSize(800, 600);        // Tamaño suficiente para mostrar ambas tablas
        setLocationRelativeTo(null);   // Centra la ventana en pantalla

        // Cargar datos iniciales en las tablas
        cargarPedidosPendientes();
        cargarRepartidoresDisponibles();

        // Acción del botón asignar
        asignarButton.addActionListener(e -> asignarEntrega());

        // Acción del botón actualizar
        actualizarButton.addActionListener(e -> {
            cargarPedidosPendientes();
            cargarRepartidoresDisponibles();
        });

        // Añadir títulos visibles a las tablas
        JScrollPane pedidosScroll = new JScrollPane(tablaPedidos);
        pedidosScroll.setBorder(BorderFactory.createTitledBorder("Pedidos pendientes"));

        JScrollPane repartidoresScroll = new JScrollPane(tablaRepartidores);
        repartidoresScroll.setBorder(BorderFactory.createTitledBorder("Repartidores disponibles"));

        // Usamos un JSplitPane para mostrar ambas tablas lado a lado
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                pedidosScroll, repartidoresScroll);
        splitPane.setDividerLocation(400); // Posición del divisor

        // Reorganizamos el mainPanel para incluir el splitPane y los botones
        mainPanel.removeAll();
        mainPanel.setLayout(new java.awt.BorderLayout());

        // Panel inferior con los botones
        JPanel botonesPanel = new JPanel();
        botonesPanel.add(asignarButton);
        botonesPanel.add(actualizarButton);

        mainPanel.add(splitPane, java.awt.BorderLayout.CENTER);
        mainPanel.add(botonesPanel, java.awt.BorderLayout.SOUTH);
    }

    // Metodo para cargar pedidos pendientes en la tabla
    private void cargarPedidosPendientes() {
        PedidoDAO pedidoDAO = new PedidoDAO();
        List<Pedido> pedidosPendientes = pedidoDAO.listarTodos().stream()
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
    }

    // Metodo para cargar repartidores disponibles en la tabla
    private void cargarRepartidoresDisponibles() {
        DefaultTableModel modeloRepartidores = new DefaultTableModel(
                new Object[]{"ID", "Nombre"}, 0);

        // Añadimos manualmente 3 repartidores de ejemplo
        modeloRepartidores.addRow(new Object[]{1, "Juan Pérez"});
        modeloRepartidores.addRow(new Object[]{2, "María González"});
        modeloRepartidores.addRow(new Object[]{3, "Carlos Ramírez"});

        tablaRepartidores.setModel(modeloRepartidores);
    }

    // Metodo que asigna un repartidor a un pedido
    private void asignarEntrega() {
        int filaPedido = tablaPedidos.getSelectedRow();
        int filaRepartidor = tablaRepartidores.getSelectedRow();

        // Validación: debe haber selección en ambas tablas
        if (filaPedido == -1 || filaRepartidor == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un pedido y un repartidor.");
            return;
        }

        // Obtenemos los IDs seleccionados
        int idPedido = (int) tablaPedidos.getValueAt(filaPedido, 0);
        int idRepartidor = (int) tablaRepartidores.getValueAt(filaRepartidor, 0);

        // Registrar entrega en BD
        EntregaDAO entregaDAO = new EntregaDAO();
        Entrega nuevaEntrega = new Entrega(
                idPedido,
                idRepartidor,
                new Date(System.currentTimeMillis()),
                new Time(System.currentTimeMillis())
        );
        entregaDAO.guardar(nuevaEntrega);

        // Actualizar estado del pedido
        PedidoDAO pedidoDAO = new PedidoDAO();
        pedidoDAO.actualizarEstado(idPedido, EstadoPedido.EN_REPARTO);

        JOptionPane.showMessageDialog(this, "Entrega asignada correctamente.");

        // Refrescar tablas para mostrar cambios
        cargarPedidosPendientes();
        cargarRepartidoresDisponibles();
    }
}
