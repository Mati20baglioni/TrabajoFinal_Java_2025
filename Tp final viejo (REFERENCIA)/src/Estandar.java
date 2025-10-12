public class Estandar extends Habitacion{
    private boolean aireAcondicionado;
    private  boolean bañera;
    private boolean frigobar;
    private boolean vistaAlMar;

    public Estandar(Habitacion h, boolean vistaAlMar) {
        super(h.getCantidadCamas(), h.getNumeroHabitacion());
        this.aireAcondicionado = true;
        this.bañera = true;
        this.frigobar = true;
        this.vistaAlMar = vistaAlMar;
        this.setTipo("Estandar");
    }

    public Estandar() {

    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public boolean isBañera() {
        return bañera;
    }

    public boolean isFrigobar() {
        return frigobar;
    }

    public boolean isVistaAlMar() {
        return vistaAlMar;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    public void setBañera(boolean bañera) {
        this.bañera = bañera;
    }

    public void setFrigobar(boolean frigobar) {
        this.frigobar = frigobar;
    }

    public void setVistaAlMar(boolean vistaAlMar) {
        this.vistaAlMar = vistaAlMar;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "\nAire acondicionado : " + aireAcondicionado +
                "\nBañera             : " + bañera +
                "\nFrigobar           : " + frigobar +
                "\nVistaAlMar         : " + vistaAlMar;
    }
}
