package view;

import controller.Controlador;
import model.Repartidor;

import javax.swing.*;

public class VentanaAgregarRepartidor extends JFrame {
    private JTextField textField1;
    private JButton agregarButton;
    private JLabel nombreRepartidorLabel;
    private JPanel mainPanel;

    private Controlador controlador;
    private VentanaAsignarEntrega ventanaAsignarEntrega; // referencia a la ventana principal

    public VentanaAgregarRepartidor(Controlador controlador, VentanaAsignarEntrega ventanaAsignarEntrega) {
        this.controlador = controlador;
        this.ventanaAsignarEntrega = ventanaAsignarEntrega;

        setTitle("Agregar Repartidor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);

        agregarButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                agregarRepartidor();
            }
        });
    }

    private void agregarRepartidor() {
        String nombre = textField1.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un nombre para el repartidor.");
            return;
        }

        Repartidor nuevo = new Repartidor(nombre);
        boolean registrado = controlador.registrarRepartidor(nuevo);

        if (registrado) {
            JOptionPane.showMessageDialog(this, "Repartidor agregado correctamente.");
            textField1.setText("");
            ventanaAsignarEntrega.cargarRepartidoresDisponibles(); // refrescar tabla en ventana principal
            dispose(); // cerrar ventana
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar repartidor.");
        }
    }
}
