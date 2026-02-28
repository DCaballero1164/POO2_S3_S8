package view;

import data.RegistroPedido;
import model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaListaPedidos extends JFrame {
    private JTable table1;
    private JPanel panel1;
    private JButton refrescarButton;
    private JButton borrarButton;

    private DefaultTableModel modelo;

    public VentanaListaPedidos(RegistroPedido registro) {
        setTitle("SpeedFast - Lista de Pedidos");               // Título
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);      // Acción al cerrar
        setContentPane(panel1);                                 // Panel raíz del .form
        pack();                                                 // Ajusta tamaño
        setLocationRelativeTo(null);                            // Centrar ventana

        // Configurar modelo de la tabla
        modelo = new DefaultTableModel(new Object[]{"ID", "Dirección", "Tipo", "Estado"}, 0);
        table1.setModel(modelo);

        // Acción del botón refrescar
        refrescarButton.addActionListener(e -> refrescarTabla(registro));

        // Cargar pedidos al abrir la ventana
        refrescarTabla(registro);
    }

    // Metodo que refresca los datos de la tabla
    private void refrescarTabla(RegistroPedido registro) {
        modelo.setRowCount(0); // limpia la tabla
        for (Pedido p : registro.getPedidos()) {
            modelo.addRow(new Object[]{
                    p.getId(),
                    p.getDireccion(),
                    p.getTipo(),
                    p.getEstado()
            });
        }
    }
}
