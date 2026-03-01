package view;

import controller.Controlador;
import model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaListaPedidos extends JFrame {
    private JTable table1;
    private JPanel panel1;
    private JButton refrescarButton;
    private JButton borrarButton;

    private DefaultTableModel modelo;
    private Controlador controlador;
    private VentanaAsignarEntrega ventanaAsignarEntrega; // referencia a la ventana de asignar entrega

    public VentanaListaPedidos(Controlador controlador, VentanaAsignarEntrega ventanaAsignarEntrega) {
        this.controlador = controlador;
        this.ventanaAsignarEntrega = ventanaAsignarEntrega;

        setTitle("SpeedFast - Lista de Pedidos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(panel1);
        pack();
        setLocationRelativeTo(null);

        modelo = new DefaultTableModel(new Object[]{"ID", "Dirección", "Tipo", "Estado"}, 0);
        table1.setModel(modelo);
        table1.getTableHeader().setVisible(true);

        refrescarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPedidos();
            }
        });

        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarSeleccionado();
            }
        });

        cargarPedidos();
    }

    public void cargarPedidos() {
        modelo.setRowCount(0);
        List<Pedido> pedidos = controlador.obtenerPedidos();
        for (Pedido p : pedidos) {
            modelo.addRow(new Object[]{
                    p.getId(),
                    p.getDireccion(),
                    p.getTipo(),
                    p.getEstado()
            });
        }
    }

    private void eliminarSeleccionado() {
        int fila = table1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un pedido para eliminar.");
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0);
        boolean elimina = controlador.eliminarPedido(id);

        if (elimina) {
            JOptionPane.showMessageDialog(this, "Pedido eliminado con éxito.");
            cargarPedidos();

            // refrescar también la ventana de asignar entrega
            if (ventanaAsignarEntrega != null) {
                ventanaAsignarEntrega.cargarPedidosPendientes();
                ventanaAsignarEntrega.cargarHistorialEntregas();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar el pedido.");
        }
    }
}
