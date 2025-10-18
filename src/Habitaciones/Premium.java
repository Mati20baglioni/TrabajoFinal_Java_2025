package Habitaciones;

import Reservas.Reserva;

public class Premium extends Habitacion{
    private boolean jacuzzi;
    private boolean vistaAlMar;
    private boolean aireAcondicionado;
    private boolean frigoBar;

    /// CONSTRUCTORES.

    public Premium(double precioPorNoche, int capacidadHabitacion, boolean jacuzzi, boolean vistaAlMar, boolean aireAcondicionado, boolean frigoBar) {
        super(precioPorNoche, capacidadHabitacion);
        this.jacuzzi = jacuzzi;
        this.vistaAlMar = vistaAlMar;
        this.aireAcondicionado = aireAcondicionado;
        this.frigoBar = frigoBar;
    }
    /// GETTERS Y SETTERS.

    public boolean isJacuzzi() {
        return jacuzzi;
    }

    public void setJacuzzi(boolean jacuzzi) {
        this.jacuzzi = jacuzzi;
    }

    public boolean isVistaAlMar() {
        return vistaAlMar;
    }

    public void setVistaAlMar(boolean vistaAlMar) {
        this.vistaAlMar = vistaAlMar;
    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean isFrigoBar() {
        return frigoBar;
    }

    public void setFrigoBar(boolean frigoBar) {
        this.frigoBar = frigoBar;
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
        return "Premium{" +
                "numeroHabitacion=" + numeroHabitacion +
                ", precioPorNoche=" + precioPorNoche +
                ", estado=" + estado +
                ", capacidadHabitacion=" + capacidadHabitacion +
                ", jacuzzi=" + jacuzzi +
                ", vistaAlMar=" + vistaAlMar +
                ", aireAcondicionado=" + aireAcondicionado +
                ", frigoBar=" + frigoBar +
                '}';
    }
}
