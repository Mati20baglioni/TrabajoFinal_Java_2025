public class Economica extends Habitacion{
    private boolean ventilador;
    private boolean bañera;

    public Economica(Habitacion h, boolean ventilador) {
        super(h.getCantidadCamas(), h.getNumeroHabitacion());
        this.ventilador = ventilador;
        this.bañera = true;
        this.setTipo("Economica");
    }

    public Economica() {

    }

    public boolean isVentilador() {
        return ventilador;
    }

    public void setVentilador(boolean ventilador) {
        this.ventilador = ventilador;
    }


    @Override
    public String toString() {
        return  super.toString() +
                "\nBañera             : " + bañera +
                "\nVentilador         : " + ventilador;
    }
}
