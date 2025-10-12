import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Scanner;

public class Administrador extends Persona{
    private Scanner teclado=new Scanner(System.in);


    public Administrador() {
    }
    public Administrador(String nombre, String apellido, String dni, String password) {
        super(nombre, apellido, dni, password);
    }
    public void backUp(ObjectMapper objectMapper, ArrayList<Conserje>conserjes, ArrayList<Persona>personas, ArrayList<Habitacion>habitacions, ArrayList<Pasajero>pasajeros, ArrayList<Reservas>reservas,ArrayList<Consumo>consumos){
        SistemaHotel.escribirConserjes(objectMapper,conserjes);
        SistemaHotel.escribirHabitaciones(objectMapper,habitacions);
        SistemaHotel.escribirPasajeros(objectMapper,pasajeros);
        SistemaHotel.escribirPersonas(objectMapper,personas);
        SistemaHotel.escribirReservas(objectMapper,reservas);
        SistemaHotel.escribirConsumos(objectMapper,consumos);
        System.out.println("se realizo con exito");
    }
    public void verPersonas(ArrayList<Persona> personas){
        for (Persona p: personas){
            System.out.println(p);
        }
    }
    public void verHabitaciones(ArrayList<Habitacion> habitacions){
        for (Habitacion h:habitacions){
            System.out.println(h);
        }
    }



    public void crearConserje(ArrayList<Conserje>conserjes){
        String nombre;
        String apellido;
        String dni;
        String contraseña;
        System.out.println("ingrese el nombre de el conserje");
        nombre=teclado.next();
        System.out.println("ingrese el apellido del conserje");
        apellido=teclado.next();
        System.out.println("ingrese el dni del conserje");
        dni=teclado.next();
        System.out.println("ingrese una contraseña por favor");
        contraseña=teclado.next();
        Persona p=new Persona(nombre,apellido,dni,contraseña);
        Conserje c=new Conserje(p);
        System.out.println("El conserje se creo correctamente.");
        conserjes.add(c);
    }


    public void añadirHabitacion(ArrayList<Habitacion> habitacions){
        System.out.println("ingese el numero de habitacion");
        String numeroDehabitacion;
        numeroDehabitacion=teclado.next();
        if(habitacions!=null){
            for(Habitacion h:habitacions){
                if(h.getNumeroHabitacion().equals(numeroDehabitacion)){
                    System.out.println("La habitacion existe");
                } else {
                    System.out.println("ingrese la cantidad de camas de la habitacion");
                    int camas;
                    camas=teclado.nextInt();
                    Habitacion habitacion=new Habitacion(camas,numeroDehabitacion);
                    String tipo;
                    System.out.println("ingrese el tipo de habitacion a crear");
                    tipo=teclado.next();
                    if(tipo.equals("ECONOMICA")){
                        boolean ventilador;
                        System.out.println("ingrese si  tiene ventilador true o false");
                        ventilador=teclado.hasNext();
                        Economica economica=new Economica(habitacion,ventilador);
                        habitacions.add(economica);
                    } else if (tipo.equals("PREMIUM")) {
                        boolean vistaAlMar, bañera;
                        System.out.println("ingrese si la habitacion tiene vista al mar true o false");
                        vistaAlMar=teclado.hasNext();
                        System.out.println("ingrese si la habitacion tiene bañera true o false");
                        bañera = teclado.hasNext();
                        Premium premium=new Premium(habitacion,vistaAlMar,bañera);
                        habitacions.add(premium);

                    } else if (tipo.equals("ESTANDAR")) {

                        boolean vistaAlMar;
                        System.out.println("ingrese si tiene vista al mar true o false");
                        vistaAlMar=teclado.hasNext();
                        Estandar estandar=new Estandar(habitacion,vistaAlMar);
                        habitacions.add(estandar);

                    }
                }
            }
        }

    }
}
