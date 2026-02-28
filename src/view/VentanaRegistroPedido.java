package view;

import data.RegistroPedido;
import model.Pedido;
import model.TipoPedido;
import model.EstadoPedido;

import javax.swing.*;

public class VentanaRegistroPedido extends JFrame {
    private JTextField textField2;          // Dirección
    private JComboBox<TipoPedido> comboBox1; // Tipo de Pedido (enum)
    private JButton guardarButton;          // Botón guardar
    private JPanel registroPanel;           // panel raíz del .form

    public VentanaRegistroPedido(RegistroPedido registro) {
        setTitle("SpeedFast - Registro de pedido");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(registroPanel);
        pack();
        setLocationRelativeTo(null);

        // Inicializar combo con valores del enum
        comboBox1.setModel(new DefaultComboBoxModel<>(TipoPedido.values()));

        // Acción del botón guardar
        guardarButton.addActionListener(e -> {
            String direccion = textField2.getText();
            TipoPedido tipo = (TipoPedido) comboBox1.getSelectedItem();

            if (direccion.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "La dirección es obligatoria.");
                return;
            }

            // Crear pedido con estado inicial PENDIENTE
            Pedido nuevoPedido = new Pedido(direccion, tipo, EstadoPedido.PENDIENTE);

            // Guardar en BD
            registro.agregarPedido(nuevoPedido);

            JOptionPane.showMessageDialog(this, "Pedido agregado con éxito.");
            dispose();
        });
    }
}
