
import java.util.ArrayList;



public class Persona {
    private ArrayList<Persona> personas = new ArrayList<>();
    private String nombre;
    private String apellido;
    private String dni;
    private String password;

    public Persona(String nombre, String apellido, String dni, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.password = password;
    }

    public Persona() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void añadirPersona(Persona p){
        personas.add(p);
    }

    @Override
    public String toString() {
        return  "\n\nNombre             : " + nombre +
                "\nApellido           : " + apellido +
                "\nDNI                : " + dni;
    }

}
