public class Premium extends Habitacion{
    private boolean jaccuzzi;
    private boolean vistaAlMar;
    private boolean aireAcondicionado;
    private boolean bañera;
    private boolean frigobar;

    public Premium(Habitacion h, boolean vistaAlMar, boolean bañera) {
        super(h.getCantidadCamas(),h.getNumeroHabitacion());
        this.jaccuzzi = true;
        this.vistaAlMar = vistaAlMar;
        this.aireAcondicionado = true;
        this.bañera = bañera;
        this.frigobar = true;
        this.setTipo("Premium");
    }

    public Premium() {

    }

    public boolean isJaccuzzi() {
        return jaccuzzi;
    }

    public void setJaccuzzi(boolean jaccuzzi) {
        this.jaccuzzi = jaccuzzi;
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

    public boolean isBañera() {
        return bañera;
    }

    public void setBañera(boolean bañera) {
        this.bañera = bañera;
    }

    public boolean isFrigobar() {
        return frigobar;
    }

    public void setFrigobar(boolean frigobar) {
        this.frigobar = frigobar;
    }



    @Override
    public String toString() {
        return  super.toString() +
                "\nvistaAlMar         : " + vistaAlMar +
                "\naireAcondicionado  : " + aireAcondicionado +
                "\nbañera             : " + bañera +
                "\nfrigobar           : " + frigobar +
                "\nJaccuzzi           : " + jaccuzzi;
    }
}
