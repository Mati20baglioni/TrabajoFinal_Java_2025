import java.time.LocalDate;
import java.util.ArrayList;

public class Reservas {
    private ArrayList<Reservas> reservas;
    private Persona persona;
    private Habitacion habitacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Reservas() {
    }

    public Reservas(Persona persona, Habitacion habitacion, LocalDate fechaInicio, LocalDate fechaFin) {
        this.persona = persona;
        this.habitacion = habitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Persona getPasajero() {
        return persona;
    }

    public void setPasajero(Persona persona) {
        this.persona = persona;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean verficacionReserva(ArrayList<Reservas> reservas, Habitacion h, LocalDate finicio, LocalDate ffin) {
        boolean disponible = true;

        for (Reservas reserva : reservas) {
            // Verificar si hay alguna reserva que se solape con el rango de fechas especificado
            if (reserva.getHabitacion().equals(h)) { // Verificar si la reserva es para la misma habitación
                if (!(ffin.isBefore(reserva.getFechaInicio()) || finicio.isAfter(reserva.getFechaFin()))) {
                    // Si hay solapamiento de fechas, la habitación no está disponible
                    disponible = false;
                    break;
                }
                else {
                    System.out.println("El rango de fechas esta OCUPADO.\n");
                }
            }
        }

        return disponible;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n--RESERVA--")
                .append(persona.toString());

        if(habitacion instanceof Economica){
            sb.append("\n--ECONOMICA-- ")
                    .append(habitacion.toString());
        }else if (habitacion instanceof Estandar){
            sb.append("\n--ESTANDAR-- ")
                    .append(habitacion.toString());
        }else if (habitacion instanceof Premium){
            sb.append("\n--PREMIUM-- ")
                    .append(habitacion.toString());
        }

        sb.append("\nFecha de inicio    : ")
                .append(fechaInicio)
                .append("\nFecha de fin       : ")
                .append(fechaFin);

        return sb.toString();
    }
}
