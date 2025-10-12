import java.util.ArrayList;
import java.util.Scanner;


public class Consumo {
    private String nombreProducto;
    private int cantidad;
    private double costo;

    public Consumo() {
    }

    public Consumo(String nombreProducto, int cantidad, double costo) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.costo = costo;
    }

    public String getDescripcion() {
        return nombreProducto;
    }

    public void setDescripcion(String descripcion) {
        this.nombreProducto = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void descontar(int cantidad){
        this.cantidad -= cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Consumo{" +
                "nombreProducto='" + nombreProducto + '\'' +
                ", cantidad=" + cantidad +
                ", costo=" + costo +
                '}';
    }
}
