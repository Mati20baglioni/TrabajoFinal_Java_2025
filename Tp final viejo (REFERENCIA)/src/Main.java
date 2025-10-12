import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

import static com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Persona> persona = new ArrayList<>();
        ArrayList<Reservas> reservas = new ArrayList<>();
        ArrayList<Habitacion> habitacions = new ArrayList<>();
        ArrayList<Conserje> conserjes = new ArrayList<>();
        ArrayList<Pasajero> pasajeros =new ArrayList<>();
        /*

        persona.add(new Persona("Mariano", "Mayora", "562","pas1"));
        persona.add(new Persona("Blas", "Aguilera", "789","pas2"));
        persona.add(new Persona("Bruno", "Starita", "413","pas3"));
        persona.add(new Persona("Francisco", "Mandolino", "647","pas4"));

        persona.add(new Persona("Pepe","Grillo","123","pas5"));
        persona.add(new Persona("Gepetto","Padre","456","pas6"));
        persona.add(new Persona("Pinochio","De madera","789","pas7"));

        pasajeros.add(new Pasajero(persona.get(6),"Francesa","Paris","Pinguino123"));
        pasajeros.add(new Pasajero(persona.get(4),"Francesa","Paris","Pinguino123"));
        pasajeros.add(new Pasajero(persona.get(5),"Francesa","Paris","Pinguino123"));

        conserjes.add(new Conserje(persona.get(1),1));
        conserjes.add(new Conserje(persona.get(2),2));
        conserjes.add(new Conserje(persona.get(3),3));
        conserjes.add(new Conserje(persona.get(0),4));


        LocalDate fechaInicio1 = LocalDate.of(2024, 6, 1);
        LocalDate fechaFin1 = LocalDate.of(2024, 6, 10);
        LocalDate fechaInicio2 = LocalDate.of(2024, 7, 1);
        LocalDate fechaFin2 = LocalDate.of(2024, 7, 10);

        reservas.add(new Reservas(pasajeros.get(1),habitacions.get(3),fechaInicio1,fechaFin1,"1"));
        reservas.add(new Reservas(pasajeros.get(2),habitacions.get(5),fechaInicio2,fechaFin2,"2"));

        System.out.println(conserjes.toString());

        mostrarHabitaciones(habitacions);




        //Funciones de archivos
        //ESCRITURA
        escribirPersonas(objectMapper,persona);
        escribirConserjes(objectMapper,conserjes);
        escribirHabitaciones(objectMapper,habitacions);
        escribirPasajeros(objectMapper,pasajeros);
        escribirReservas(objectMapper,reservas);
        //LECTURA
        persona=leerArchivosPersonas(objectMapper);
        conserjes=leerArchivosConserjes(objectMapper);
        pasajeros=leerArchivosPasajeros(objectMapper);
        habitacions=leerArchivosHabitaciones(objectMapper);
        reservas =leerArchivosReservas(objectMapper);

//        System.out.println(pasajeros.toString());
//        System.out.println(conserjes.toString());
//        System.out.println(reservas.toString());
//        mostrarHabitaciones(habitacions);

//        mostrarHabitacionesPorEstado(habitacions, Estado.DISPONIBLE);
*/


//        Habitacion habitacion1 = new Habitacion(3,"1");
//        Habitacion habitacion2 = new Habitacion(1,"2");
//        Habitacion habitacion3 = new Habitacion(2,"3");
//        Habitacion habitacion4 = new Habitacion(4,"4");
//        Habitacion habitacion5 = new Habitacion(3,"5");
//        Habitacion habitacion6 = new Habitacion(2,"6");
//
//
//        habitacions.add((Habitacion) new Economica(habitacion1,false));
//        habitacions.add((Habitacion) new Economica(habitacion2,true));
//
//
//        habitacions.add((Habitacion) new Estandar(habitacion3,true));
//        habitacions.add((Habitacion) new Estandar(habitacion4,false));
//
//        habitacions.add((Habitacion) new Premium(habitacion5,false,true));
//        habitacions.add((Habitacion) new Premium(habitacion6,true, false));
//
//        ObjectMapper objectMapper;
//        objectMapper = SistemaHotel.archivo();
//        SistemaHotel.escribirHabitaciones(objectMapper,habitacions);


        SistemaHotel sistemaHotel = new SistemaHotel();


        sistemaHotel.sistemaHotelMenu();

    }



    static Comparator<Persona> comparadorPorNombre = new Comparator<Persona>() {
        @Override
        public int compare(Persona o1, Persona o2) {
            return o1.getNombre().compareTo(o2.getNombre());
        }
    };
    static Comparator<Persona> comparadorPorApellido = new Comparator<Persona>() {
        @Override
        public int compare(Persona o1, Persona o2) {
            return o1.getApellido().compareTo(o2.getApellido());
        }
    };
    static Comparator<Persona> comparadorPorDni = new Comparator<Persona>() {
        @Override
        public int compare(Persona o1, Persona o2) {
            return o1.getDni().compareTo(o2.getDni());
        }
    };

    public static class LocalDateSerializer extends StdSerializer<LocalDate> {

        public LocalDateSerializer() {
            super(LocalDate.class);
        }

        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeString(value.toString());
        }
    }
    public static class LocalDateDeserializer extends StdDeserializer<LocalDate> {

        public LocalDateDeserializer() {
            super(LocalDate.class);
        }

        @Override
        public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return LocalDate.parse(p.getValueAsString());
        }
    }



}