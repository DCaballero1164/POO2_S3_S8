package view;

import data.RegistroPedido;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private JButton registrarPedidoButton;                      //Boton para registrar pedido
    private JButton listarPedidosButton;                        //Boton para mostrar la tabla
    private JButton asignarRepartidorIniciarEntregaButton;      //Boton para asignar repartidor
    private JPanel mainPanel;                                   // este panel lo genera el .form

    //Se llama a la clase RegistroPedido
    private RegistroPedido registro;

    public VentanaPrincipal(RegistroPedido registro) {
        this.registro = registro;

        setTitle("SpeedFast");        //Titulo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //Accion al cerrar la ventana.
        setContentPane(mainPanel);                          // enlaza el panel del .form
        pack();                                             // ajusta el tamaño según los componentes

        setLocationRelativeTo(null); //Centrar ventana

        // Acciones de los botones
        registrarPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaRegistroPedido(registro).setVisible(true);
            }
        });

        listarPedidosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaListaPedidos(registro).setVisible(true);
            }
        });

        asignarRepartidorIniciarEntregaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaAsignarEntrega().setVisible(true);
            }
        });
    }
}

