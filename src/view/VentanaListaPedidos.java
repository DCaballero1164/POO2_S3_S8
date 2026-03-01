package view;

import controller.Controlador;
import model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class VentanaListaPedidos extends JFrame {
    private JTable table1;
    private JPanel panel1;
    private JButton refrescarButton;
    private JButton borrarButton;

    private DefaultTableModel modelo;
    private Controlador controlador;

    public VentanaListaPedidos(Controlador controlador) {
        this.controlador = controlador;

        setTitle("SpeedFast - Lista de Pedidos");               // Título
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);      // Acción al cerrar
        setContentPane(panel1);                                 // Panel raíz del .form
        pack();                                                 // Ajusta tamaño
        setLocationRelativeTo(null);                            // Centrar ventana

        // Configurar modelo de la tabla
        modelo = new DefaultTableModel(new Object[]{"ID", "Dirección", "Tipo", "Estado"}, 0);
        table1.setModel(modelo);

        // Acción del botón refrescar
        refrescarButton.addActionListener(e -> refrescarTabla());

        // Acción del botón borrar
        borrarButton.addActionListener(e -> eliminarSeleccionado());

        // Cargar pedidos al abrir la ventana
        refrescarTabla();
    }

    // Metodo que refresca los datos de la tabla
    private void refrescarTabla() {
        modelo.setRowCount(0); // limpia la tabla
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

    // Metodo para eliminar el pedido seleccionado
    private void eliminarSeleccionado() {
        int fila = table1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un pedido para eliminar.");
            return;
        }

        int id = (int) modelo.getValueAt(fila, 0); // obtiene el ID de la fila seleccionada
        boolean elimina = controlador.eliminarPedido(id);

        if (elimina) {
            JOptionPane.showMessageDialog(this, "Pedido eliminado con éxito.");
            refrescarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar el pedido.");
        }
    }
}
