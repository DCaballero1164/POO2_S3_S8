package model;

public class Pedido {
    private int id;
    private String direccion;
    private TipoPedido tipo;
    private EstadoPedido estado;

    // Constructor con ID (para consultas)
    public Pedido(int id, String direccion, TipoPedido tipo, EstadoPedido estado) {
        this.id = id;
        this.direccion = direccion;
        this.tipo = tipo;
        this.estado = estado;
    }

    // Constructor sin ID (para inserciones)
    public Pedido(String direccion, TipoPedido tipo, EstadoPedido estado) {
        this.direccion = direccion;
        this.tipo = tipo;
        this.estado = estado;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public TipoPedido getTipo() {
        return tipo;
    }

    public EstadoPedido getEstado() {
        return estado;
    }


    // //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTipo(TipoPedido tipo) {
        this.tipo = tipo;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }
}

