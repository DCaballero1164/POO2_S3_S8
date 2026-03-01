package view;

import controller.Controlador;
import model.Pedido;
import model.TipoPedido;
import model.EstadoPedido;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistroPedido extends JFrame {
    private JTextField textField2;
    private JComboBox<TipoPedido> comboBox1;
    private JButton guardarButton;
    private JPanel registroPanel;

    private Controlador controlador;
    private VentanaAsignarEntrega ventanaAsignarEntrega;
    private VentanaListaPedidos ventanaListaPedidos;

    public VentanaRegistroPedido(Controlador controlador, VentanaAsignarEntrega ventanaAsignarEntrega, VentanaListaPedidos ventanaListaPedidos) {
        this.controlador = controlador;
        this.ventanaAsignarEntrega = ventanaAsignarEntrega;
        this.ventanaListaPedidos = ventanaListaPedidos;

        setTitle("SpeedFast - Registro de pedido");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(registroPanel);
        pack();
        setLocationRelativeTo(null);

        comboBox1.setModel(new DefaultComboBoxModel<>(TipoPedido.values()));

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String direccion = textField2.getText();
                TipoPedido tipo = (TipoPedido) comboBox1.getSelectedItem();

                if (direccion.isEmpty()) {
                    JOptionPane.showMessageDialog(VentanaRegistroPedido.this,
                            "La dirección es obligatoria.");
                    return;
                }

                Pedido nuevoPedido = new Pedido(direccion, tipo, EstadoPedido.PENDIENTE);

                boolean exito = controlador.registrarPedido(nuevoPedido);

                if (exito) {
                    JOptionPane.showMessageDialog(VentanaRegistroPedido.this, "Pedido agregado con éxito.");
                    // Refrescar ambas ventanas
                    ventanaAsignarEntrega.cargarPedidosPendientes();
                    ventanaListaPedidos.cargarPedidos();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(VentanaRegistroPedido.this, "Error al registrar el pedido.");
                }
            }
        });
    }
}
