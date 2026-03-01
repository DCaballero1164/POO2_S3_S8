package view;

import controller.Controlador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private JButton registrarPedidoButton;
    private JButton listarPedidosButton;
    private JButton asignarRepartidorIniciarEntregaButton;
    private JPanel mainPanel;

    private Controlador controlador;
    private VentanaAsignarEntrega ventanaAsignarEntrega;
    private VentanaListaPedidos ventanaListaPedidos;

    public VentanaPrincipal(Controlador controlador) {
        this.controlador = controlador;

        setTitle("SpeedFast");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);

        // Primero instanciamos las ventanas
        ventanaAsignarEntrega = new VentanaAsignarEntrega(controlador, null);
        ventanaListaPedidos = new VentanaListaPedidos(controlador, ventanaAsignarEntrega);

        // Enlazamos la referencia cruzada
        ventanaAsignarEntrega.setVentanaListaPedidos(ventanaListaPedidos);

        // Acción: registrar pedido
        registrarPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaRegistroPedido(controlador, ventanaAsignarEntrega, ventanaListaPedidos).setVisible(true);
            }
        });

        // Acción: listar pedidos
        listarPedidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaListaPedidos.setVisible(true);
            }
        });

        // Acción: asignar repartidor / iniciar entrega
        asignarRepartidorIniciarEntregaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaAsignarEntrega.setVisible(true);
            }
        });
    }
}
