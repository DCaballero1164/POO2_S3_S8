package main;

import controller.Controlador;
import view.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        Controlador controlador = new Controlador();                // Crea el controlador
        VentanaPrincipal vp = new VentanaPrincipal(controlador);    // Crea la ventana principal
        vp.setVisible(true);                                        // Muestra la ventana principal
    }
}
