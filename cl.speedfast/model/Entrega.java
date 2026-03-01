package model;

import java.sql.Date;
import java.sql.Time;

public class Entrega {
    private int id;
    private int idPedido;
    private int idRepartidor;
    private Date fecha;
    private Time hora;

    // constructor con ID (para consultas)
    public Entrega(int id, int idPedido, int idRepartidor, Date fecha, Time hora) {
        this.id = id;
        this.idPedido = idPedido;
        this.idRepartidor = idRepartidor;
        this.fecha = fecha;
        this.hora = hora;
    }

    // constructor sin ID
    public Entrega(int idPedido, int idRepartidor, Date fecha, Time hora) {
        this.idPedido = idPedido;
        this.idRepartidor = idRepartidor;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Getter
    public int getId() {
        return id;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public int getIdRepartidor() {
        return idRepartidor;
    }

    public Date getFecha() {
        return fecha;
    }

    public Time getHora() {
        return hora;
    }

    // Setter

    public void setId(int id) {
        this.id = id;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setIdRepartidor(int idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

}