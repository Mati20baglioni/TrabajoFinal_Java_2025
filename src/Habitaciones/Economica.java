package Habitaciones;

import Reservas.Reserva;

public class Economica extends Habitacion{
    private boolean ventilador;
    private boolean tina;

    /// CONSTRUCTORES.

    public Economica(double precioPorNoche, int capacidadHabitacion, boolean ventilador, boolean tina) {
        super(precioPorNoche, capacidadHabitacion);
        this.ventilador = ventilador;
        this.tina = tina;
    }
    /// GETTERS Y SETTERS.

    public boolean isVentilador() {
        return ventilador;
    }

    public void setVentilador(boolean ventilador) {
        this.ventilador = ventilador;
    }

    public boolean isTina() {
        return tina;
    }

    public void setTina(boolean tina) {
        this.tina = tina;
    }
    /// METODOS.

    /// METODO PARA CALCULAR PRECIO FINAL CUANDO DEFINAMOS BIEN EL TEMA DE CONSUMOS.

    @Override
    public double calcularPrecioFinal() {
        return 0;
    }

    ///TO STRINGS.
    @Override
    public String toString() {
        return "Economica{" +
                "numeroHabitacion=" + numeroHabitacion +
                ", precioPorNoche=" + precioPorNoche +
                ", estado=" + estado +
                ", capacidadHabitacion=" + capacidadHabitacion +
                ", ventilador=" + ventilador +
                ", tina=" + tina +
                '}';
    }
}
