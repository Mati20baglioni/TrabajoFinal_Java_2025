public class Conserje extends Persona{


    public Conserje() {
        super();
    }

    public Conserje(Persona p) {
        super(p.getNombre(),p.getApellido(), p.getDni(), p.getPassword());

    }

    @Override
    public String toString() {
        return "\n\nConserje " + super.toString();
    }
}
