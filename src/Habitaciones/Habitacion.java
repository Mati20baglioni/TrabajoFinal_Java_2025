package Habitaciones;

import Consumos.Consumo;
import Enums.Estado_Habitacion;
import Exepciones.Dato_Invalido;
import Reservas.Reserva;

import java.util.ArrayList;

public abstract class Habitacion {
    private static int contadorHabitaciones = 1;
    protected int numeroHabitacion;
    protected double precioPorNoche;
    protected Estado_Habitacion estado;
    protected int capacidadHabitacion;
    private ArrayList<Consumo>consumos=new ArrayList<>();
    private Reserva reservaActual;

    /// CONSTRUCTORES.

    public Habitacion(double precioPorNoche, int capacidadHabitacion) {
        this.numeroHabitacion = contadorHabitaciones++;
        this.precioPorNoche = precioPorNoche;
        this.estado = Estado_Habitacion.DISPONIBLE;
        this.capacidadHabitacion = capacidadHabitacion;
    }
    /// GETTERS Y SETTERS.

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }
    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(double precioPorNoche) throws Dato_Invalido {
        if (precioPorNoche <= 0) {
            throw new Dato_Invalido("El precio por noche debe ser mayor a cero.");
        }
        this.precioPorNoche = precioPorNoche;
    }

    public Estado_Habitacion getEstado() {
        return estado;
    }

    public void setEstado(Estado_Habitacion estado) {
        this.estado = estado;
    }

    public int getCapacidadHabitacion() {
        return capacidadHabitacion;
    }

    public void setCapacidadHabitacion(int capacidadHabitacion)  throws Dato_Invalido {
        if (capacidadHabitacion <= 0) {
            throw new Dato_Invalido("La capacidad debe ser mayor a cero.");
        }
        this.capacidadHabitacion = capacidadHabitacion;
    }

    public ArrayList<Consumo> getConsumos() {
        return consumos;
    }

    public Reserva getReservaActual() {
        return reservaActual;
    }

    /// METODOS DE PRECIO.

    public abstract double calcularPrecioFinal();

    /// METODO RELACIONADO A LA RESERVA DE LA HABITACION.

    public void asignarReserva(Reserva reserva) {
        this.reservaActual = reserva;
        this.estado = Estado_Habitacion.RESERVADO;
    }

    public void ocupar() {
        this.estado = Estado_Habitacion.OCUPADO;
    }

    public void liberar() {
        this.reservaActual = null;
        this.estado = Estado_Habitacion.DISPONIBLE;
    }

    /**ME FALTARIA AGREGAR UN METODO PARA AGREGAR UN CONSUMO FRAN QUEDA PARA MAS ADELANTE CUANDO DEFINAMOS.*/

    ///TO STRINGS.

    @Override
    public String toString() {
        return "Habitacion{" +
                "numeroHabitacion=" + numeroHabitacion +
                ", precioPorNoche=" + precioPorNoche +
                ", estado=" + estado +
                ", capacidadHabitacion=" + capacidadHabitacion +
                ", consumos=" + consumos +
                '}';
    }
}




