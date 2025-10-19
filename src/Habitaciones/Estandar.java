package Habitaciones;

import Reservas.Reserva;

public class Estandar extends Habitacion {
    private boolean aireAcondicionado;
    private boolean vistaAlMar;

    /// CONSTRUCTORES.

    public Estandar(double precioPorNoche, int capacidadHabitacion, boolean aireAcondicionado, boolean vistaAlMar) {
        super(precioPorNoche, capacidadHabitacion);
        this.aireAcondicionado = aireAcondicionado;
        this.vistaAlMar = vistaAlMar;
    }
    /// GETTERS Y SETTERS.

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean isVistaAlMar() {
        return vistaAlMar;
    }

    public void setVistaAlMar(boolean vistaAlMar) {
        this.vistaAlMar = vistaAlMar;
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
        return "Estandar{" +
                "numeroHabitacion=" + numeroHabitacion +
                ", precioPorNoche=" + precioPorNoche +
                ", estado=" + estado +
                ", capacidadHabitacion=" + capacidadHabitacion +
                ", aireAcondicionado=" + aireAcondicionado +
                ", vistaAlMar=" + vistaAlMar +
                '}';
    }
}
