package main;

import data.RegistroPedido;
import view.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        RegistroPedido registro = new RegistroPedido();
        VentanaPrincipal vp = new VentanaPrincipal(registro);
        vp.setVisible(true);
    }
}
