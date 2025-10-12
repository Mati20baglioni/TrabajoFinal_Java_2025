import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pasajero extends Persona{
    private List<Consumo> consumos = new ArrayList<>();
    private ArrayList<ArrayList<Persona>> personas  = new ArrayList<ArrayList<Persona>>();
    private String nacionalidad;
    private String localidad;
    private String direccion;
    private HashMap<String, Double> cuenta;

    public Pasajero() {

    }

    public Pasajero(Persona p, String nacionalidad, String localidad, String direccion) {
        super(p.getNombre(), p.getApellido(), p.getDni(), p.getPassword());
        this.nacionalidad = nacionalidad;
        this.localidad = localidad;
        this.direccion = direccion;
    }

    public ArrayList<ArrayList<Persona>> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<Persona> personas) {
        this.personas.add(personas);
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Consumo> getConsumos() {

        return consumos;
    }

    public double sacarTotalConsumo(){
        double total = 0;
        for (Consumo consumo : consumos){
            total += consumo.getCosto();
        }
        return total;
    }
    public HashMap<String, Double> getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cadena, Double precio) {
        cuenta.put(cadena, precio);
    }
    public void agregarConsumos(String nombre, int cantidad,double precio) {
        Consumo c=new Consumo(nombre,cantidad,precio);
        consumos.add(c);
    }
    public static double calcularTotal(HashMap<String, Double> productos) {
        double suma = 0.0;
        for (double precio : productos.values()) {
            suma += precio;
        }
        return suma;
    }

    // Función para imprimir el recibo
    public static void imprimirRecibo(HashMap<String, Double> productos, double total) {
        System.out.println("RECIBO:");
        for (HashMap.Entry<String, Double> entry : productos.entrySet()) {
            String nombreProducto = entry.getKey();
            double precio = entry.getValue();
            System.out.println(nombreProducto + ": $" + precio);
        }
        System.out.println("--------------------");
        System.out.println("TOTAL: $" + total);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nNacionalidad       : " + nacionalidad +
                "\nLocalidad          : " + localidad +
                "\nDireccion          : " + direccion +
                "\nConsumo: " + consumos.toString() + "\n";
    }
}






