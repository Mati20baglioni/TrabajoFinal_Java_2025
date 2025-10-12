import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

// Anotaciones para configurar el polimorfismo
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@Class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Economica.class, name = "Economica"),
        @JsonSubTypes.Type(value = Estandar.class, name = "Estandar"),
        @JsonSubTypes.Type(value = Premium.class, name = "Premium")
})
public class Habitacion{
    private int cantidadCamas;
    private String numeroHabitacion;
    private Estado estado;

    public Habitacion() {
    }

    public Habitacion(int cantidadCamas, String numeroHabitacion) {
        this.cantidadCamas = cantidadCamas;
        this.numeroHabitacion = numeroHabitacion;
        this.estado = Estado.DISPONIBLE;
    }

    public int getCantidadCamas() {
        return cantidadCamas;
    }

    public void setCantidadCamas(int cantidadCamas) {
        this.cantidadCamas = cantidadCamas;
    }

    public String getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(String numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return  "\nNumero             : " + numeroHabitacion +
                "\nCantidad de camas  : " + cantidadCamas +
                "\nEstado             : " + estado;
    }

    protected void setTipo(String economica) {
    }
}
