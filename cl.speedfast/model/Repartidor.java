package model;

public class Repartidor {
    private int id;
    private String nombre;

    public Repartidor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Repartidor(String nombre) {
        this.nombre = nombre;
    }

    //GETTER
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    //SETTER
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
