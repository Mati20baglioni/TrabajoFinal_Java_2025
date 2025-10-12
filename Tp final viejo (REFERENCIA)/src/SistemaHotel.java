import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@class"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Pasajero.class, name = "pasajero"),
        // Añade otros subtipos aquí
})

public class SistemaHotel {

    public final  ObjectMapper objectMapper= archivo();

    private ArrayList<Persona> personas = new ArrayList<>();
    private ArrayList<Conserje> conserjes = new ArrayList<>();
    private ArrayList<Pasajero> pasajeros = new ArrayList<>();
    private ArrayList<Habitacion> habitaciones = new ArrayList<>();
    private ArrayList<Administrador> administradores = new ArrayList<>();
    private ArrayList<Reservas> reservas = new ArrayList<>();
    private ArrayList<Consumo> consumos = new ArrayList<>();

    private Persona persona = new Persona();
    private Reservas reserva = new Reservas();
    private Conserje conserje = new Conserje();
    private Pasajero pasajero = new Pasajero();
    private Habitacion habitacion = new Habitacion();
    private Administrador administrador = new Administrador();
    private Consumo consumo = new Consumo();

    private Scanner teclado = new Scanner(System.in);

    public SistemaHotel() {
    }

    public String imprimirSistemaHotelMenu(){
        return  "\n1) Registro." +
                "\n2) Inicio sesion." +
                "\n0) Salir.";
    }
    public void sistemaHotelMenu(){
        int opcion;
//        Administrador administrador = new Administrador("Admin","admin","1524862", "admin");
//        escribirAdministrador(objectMapper,administrador);

        do {
            System.out.println(imprimirSistemaHotelMenu());
            opcion = teclado.nextInt();

            switch (opcion){
                case 1:
                    registrarse();
                    break;
                case 2:
                    inicioSesion();
                    break;
            }
        }while (opcion!= 0);
    }

    public void registrarse(){

        Persona p;
        String nombre;
        String apellido;
        String dni;
        String contraseña;
        System.out.println("Ingrese su nombre: ");
        nombre = teclado.next();
        System.out.println("Ingrese su apellido: ");
        apellido = teclado.next();
        System.out.println("Ingrese su dni: ");
        dni = teclado.next();
        System.out.println("Ingrese su contraseña: ");
        contraseña = teclado.next();
        p = new Persona(nombre,apellido,dni,contraseña);
        if (existe(nombre,contraseña) == null) {
            System.out.println("Persona registrada exitosamente.");
            personas.add(p);
            escribirPersonas(objectMapper,personas);
        }else {
            System.err.println("La persona ya existe.");
        }
    }

    public void inicioSesion(){
        String usuario;
        String contraseña;
        int contador = 0;
        boolean si = false;
        while (contador < 3 && si == false) {
            System.out.println("Ingrese su nombre: ");
            usuario = teclado.next();
            System.out.println("Ingrese su contraseña: ");
            contraseña = teclado.next();

            if ((validar(usuario, contraseña)) == true) {
                si = true;
            } else {
                System.err.println("El nombre y/o contraseña son incorrectos.\n");
            }
        }
    }

    public Persona existe(String nombre, String password){

        if (personas != null){
            for (Persona p : personas){
                if (p.getNombre().equals(nombre) && p.getPassword().equals(password)){
                    System.out.println(p.getNombre() + p.getApellido());
                    return p;
                }
            }
        }else {
            System.out.println("La lista esta vacia.");
        }
        return null;
    }

    public boolean validar(String nombre, String password){
        administrador = leerArchivosAdministrador(objectMapper);
        boolean encontre = false;
        System.out.println("Inicio sesion exitosamente.");
        habitaciones = leerArchivosHabitaciones(objectMapper);
        personas = leerArchivosPersonas(objectMapper);
        pasajeros = leerArchivosPasajeros(objectMapper);
        conserjes = leerArchivosConserjes(objectMapper);
        reservas = leerArchivosReservas(objectMapper);
        consumos = leerArchivosConsumos(objectMapper);

        if (conserjes != null && encontre == false){
            for (Conserje conserje : conserjes){
                if (conserje.getNombre().equals(nombre) && conserje.getPassword().equals(password)){
                    conserjeMenu(conserje);
                    encontre = true;
                    break;
                }
            }
        }
        if (pasajeros != null && encontre == false){
            for (Pasajero pasajero : pasajeros){
                if (pasajero.getNombre().equals(nombre) && pasajero.getPassword().equals(password)){
                    pasajeroMenu(pasajero);
                    encontre = true;
                }
            }
        }
        if (administrador != null && encontre == false){
            if (administrador.getNombre().equals(nombre) && administrador.getPassword().equals(password)){
                administradorMenu();
                encontre = true;
            }
        }
        if (personas != null && encontre == false){
            for (Persona persona : personas){
                if (persona.getNombre().equals(nombre) && persona.getPassword().equals(password)){
                    personaMenu(persona);
                    encontre = true;
                }
            }
        }

        return encontre;
    }

    /*public void menuInicioSecion(){
        System.out.println("ingrese al verificar persona");
        for (Persona p : personas){
            if (p instanceof Conserje){
                conserjeMenu();
            }else if (p instanceof Pasajero){
                pasajeroMenu();
            }else if (p instanceof Administrador){
                administradorMenu();
            }else if (p instanceof Persona){
                personaMenu();
            }
        }
    }*/

    public String imprimirMenuAdministrador(){
        return  "Menu Administrador\n" +
                "1) Backup\n" +
                "2) Visualizar Personas -> Pasajeros\n" +
                "3) Visualizar Habitaciones (Todas)\n" +
                "4) Crear conserje\n" +
                "5) Agregar producto al Stock\n" +
                "6) Visualizar productos\n" +
                "0) Salir\n";
    }
    public void administradorMenu(){
        int opcion;


        do {
            System.out.println(imprimirMenuAdministrador());
            opcion = teclado.nextInt();

            switch (opcion){
                case 1:
                    administrador.backUp(objectMapper,conserjes,personas,habitaciones,pasajeros,reservas,consumos);
                    break;
                case 2:
                    System.out.println(pasajeros.toString());
                    break;
                case 3:
                    mostrarHabitaciones(habitaciones);
                    break;
                case 4:
                    administrador.crearConserje(conserjes);
                    escribirConserjes(objectMapper,conserjes);
                    break;
                case 5:
                    System.out.println("Ingrese nombre del producto");
                    String nombreProducto="";
                    nombreProducto=teclado.next();
                    System.out.println("ingrese la cantidas");
                    int cantidad=0;
                    cantidad=teclado.nextInt();
                    System.out.println("ingrese el precio del producto");
                    double precio;
                    precio=teclado.nextDouble();
                    Consumo c=new Consumo(nombreProducto,cantidad,precio);
                    consumos.add(c);
                    escribirConsumos(objectMapper, consumos);
                    break;
                case 6:
                    for (Consumo consumo1 : consumos){
                        System.out.println(consumo1.toString());
                    }
            }
        }while (opcion!= 0);
    }

    public String imprimirMenuConserje(){
        return  "Menu Conserje\n" +
                "1) Visualiza habitaciones\n" +
                "2) Visualiza reservas\n" +
                "3) Crear pasajero\n" +
                "4) Ver historial pasajeros\n" +
                "5) Hacer checkin\n" +
                "6) Checkin con reservas\n" +
                "7) Hacer checkout\n" +
                "0) Salir\n";
    }
    public void conserjeMenu(Persona persona){
        int opcion;
        String volver = "s";
        String nombre, apellido;
        Estado estado;
        String numeroHabitacion = "";
        double total=0;
        habitaciones = leerArchivosHabitaciones(objectMapper);
        reservas = leerArchivosReservas(objectMapper);
        pasajeros = leerArchivosPasajeros(objectMapper);
        do {
            System.out.println(imprimirMenuConserje());
            opcion = teclado.nextInt();

            switch (opcion){
                case 1:
                    do {
                        System.out.println("En que estado quieres ver las habitaciones: \n1)DISPONIBLES\n2)OCUPADAS\n3)RESERVADAS\n4)LIMPIESA\n");
                        int op = teclado.nextInt();

                        switch (op){
                            case 1:
                                estado = Estado.DISPONIBLE;
                                break;
                            case 2:
                                estado = Estado.OCUPADA;
                                break;
                            case 3:
                                estado = Estado.RESERVADA;
                                break;
                            case 4:
                                estado = Estado.LIMPIESA;
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + op);
                        }

                        mostrarHabitacionesPorEstado(habitaciones,estado);
                        System.out.println("Desea ver otro estado de habitacion? (s - n)");
                        volver = teclado.next();
                    }while (volver.equals("s") || volver.equals("S"));
                    break;
                case 2:
                    System.out.println(reservas.toString());
                    break;
                case 3:
                    System.out.printf("nombre");
                    nombre = teclado.next();
                    System.out.printf("apellido");
                    apellido = teclado.next();
                    System.out.printf("dni");
                    String dni = teclado.next();
                    System.out.printf("contraseña");
                    String contraseña = teclado.next();

                    if (personas != null){
                        if (existe(nombre,contraseña)!= null) {
                            persona = existe(nombre, contraseña);
                        }else {
                            persona = new Persona(nombre,apellido,dni,contraseña);
                        }
                    }else {
                        persona = new Persona(nombre,apellido,dni,contraseña);
                    }
                    System.out.printf("Nacionalidad");
                    String nacionalidad = teclado.next();
                    System.out.printf("Localidad");
                    String localidad = teclado.next();
                    System.out.printf("Direccion");
                    String direccion = teclado.next();
                    pasajero = new Pasajero(persona,nacionalidad,localidad,direccion);
                    pasajeros.add(pasajero);
                    escribirPasajeros(objectMapper,pasajeros);
                    break;
                case 4:
                    System.out.println(pasajeros.toString());
                    break;
                case 5:
                    boolean validacion = false;
                    do {
                        System.out.printf("ingresa nombre: ");
                        nombre = teclado.next();
                        System.out.printf("ingresa apellido: ");
                        apellido = teclado.next();
                        persona = buscarPersona(nombre, apellido);
                        String numero = buscarHabitacion();
                        for (Habitacion habitacion1 : habitaciones){
                            if (habitacion1.getNumeroHabitacion().equals(numero)){
                                habitacion = habitacion1;
                            }
                        }

                        System.out.printf("Fecha inicio\n");
                        System.out.printf("Ingresa dia: ");
                        int diai = teclado.nextInt();
                        System.out.printf("Ingresa mes: ");
                        int mesi = teclado.nextInt();
                        System.out.printf("Ingresa año: ");
                        int añoi = teclado.nextInt();
                        LocalDate fechainicio = LocalDate.of(añoi, mesi, diai);

                        System.out.printf("Fecha fin\n");
                        System.out.printf("Ingresa dia: ");
                        int diaf = teclado.nextInt();
                        System.out.printf("Ingresa mes: ");
                        int mesf = teclado.nextInt();
                        System.out.printf("Ingresa año: ");
                        int añof = teclado.nextInt();
                        LocalDate fechafin = LocalDate.of(añof, mesf, diaf);

                        if (reserva.verficacionReserva(reservas,habitacion, fechainicio, fechafin) == true) {
                            habitacion.setEstado(Estado.OCUPADA);
                            reservas.add(new Reservas(persona,habitacion,fechainicio,fechafin));

                            escribirReservas(objectMapper,reservas);
                            escribirHabitaciones(objectMapper,habitaciones);
                            validacion = true;
                        }

                    }while (validacion == false);
                    break;
                case 6:

                    System.out.printf("ingresa nombre: ");
                    nombre = teclado.next();
                    System.out.printf("ingresa apellido: ");
                    apellido = teclado.next();
                    if (reservas != null) {
                        for (Reservas reservas1 : reservas) {
                            if (reservas1.getPasajero().getNombre().equals(nombre) && reservas1.getPasajero().getApellido().equals(apellido)) {
                                reservas1.getHabitacion().setEstado(Estado.OCUPADA);
                                numeroHabitacion = reservas1.getHabitacion().getNumeroHabitacion();

                            }
                        }
                    }
                    if (habitaciones != null){
                        for (Habitacion habitacion1 : habitaciones){
                            if (habitacion1.getNumeroHabitacion().equals(numeroHabitacion)){
                                habitacion1.setEstado(Estado.OCUPADA);

                            }
                        }
                    }

                        for (Pasajero p: pasajeros) {
                            if (p.getNombre().equals(nombre) && p.getApellido().equals(apellido)) {
                                System.out.println("se encontro la perosna ya es un pasajero");
                            }
                        }
                            for (Persona p1: personas){
                                if(p1.getNombre().equals(nombre)&&p1.getApellido().equals(apellido)){
                                    System.out.println("ingrese la nacionalidad");
                                    String nacion=teclado.next();
                                    System.out.println("ingrese la localidad");
                                    String loca=teclado.next();
                                    System.out.println("ingrese la direccion");
                                    String dire=teclado.next();
                                    Pasajero pasajero1=new Pasajero(p1,nacion,loca,dire);
                                    pasajeros.add(pasajero1);
                                    escribirPasajeros(objectMapper,pasajeros);
                                }
                            }

                    escribirReservas(objectMapper,reservas);
                    escribirHabitaciones(objectMapper,habitaciones);
                    break;
                case 7:

                        System.out.printf("ingrese nombre");
                        nombre = teclado.next();
                        System.out.printf("ingrese apellido");
                        apellido = teclado.next();

                    if (reservas != null) {
                        Iterator<Reservas> iterator = reservas.iterator();
                        while (iterator.hasNext()) {
                            Reservas reservas1 = iterator.next();
                            if (reservas1.getPasajero().getNombre().equals(nombre) && reservas1.getPasajero().getApellido().equals(apellido)) {
                                numeroHabitacion = reservas1.getHabitacion().getNumeroHabitacion();
                                reservas1.getHabitacion().setEstado(Estado.LIMPIESA);
                                System.out.println("Limpiandose...");
                                reservas1.getHabitacion().setEstado(Estado.DISPONIBLE);
                                System.out.println("La habitacion se encuentra DISPONIBLE");
                                iterator.remove(); // Eliminar usando el iterador para evitar ConcurrentModificationException
                            }
                        }
                    }
                        for (Habitacion habitacion1 : habitaciones){
                            if (habitacion1.getNumeroHabitacion().equals(numeroHabitacion)){
                                habitacion1.setEstado(Estado.DISPONIBLE);
                            }
                        }
                        for (Pasajero p : pasajeros) {
                            if (p.getNombre().equals(nombre) && p.getApellido().equals(apellido)) {
                                System.out.println(p.getConsumos().toString());
                                total = p.sacarTotalConsumo();
                                System.out.println(" el total a pagar es de: " + p.sacarTotalConsumo());
                                System.out.println("pagado con exito");
                                pasajeros.remove(p);
                            }
                        }
                        escribirReservas(objectMapper,reservas);
                        escribirHabitaciones(objectMapper,habitaciones);
                        escribirPasajeros(objectMapper,pasajeros);
                    break;
            }
        }while (opcion!= 0);
    }
    public Reservas buscarReservaExistente(String nombre, String apellido){return null;}
    public Persona buscarPersona(String nombre, String apellido){
        for (Persona persona1 : personas){
            if (persona1.getNombre().equals(nombre) && persona1.getApellido().equals(apellido)){
                return persona1;
            }
        }
        return null;
    }
    public String buscarHabitacion(){
        boolean seguir = false;
        String numero = null;
        String numeroHabitacion = null;
        System.out.printf("Ingrese numero de personas");
        int camas = teclado.nextInt();
        do {
            System.out.println("Ingrese tipo de habitacion \n1)Economica\n2)Estandar\n3)Premium\n");
            int tipo = teclado.nextInt();
            switch (tipo) {
                case 1:
                    for (Habitacion habitacion1 : habitaciones) {
                        if (habitacion1 instanceof Economica) {
                            if (habitacion1.getCantidadCamas() == camas && habitacion1.getEstado().equals(Estado.DISPONIBLE)) {
                                System.out.println(habitacion1.toString());
                                numero = habitacion1.getNumeroHabitacion();
                                seguir = true;
                            }
                        }
                    }
                    break;
                case 2:
                    for (Habitacion habitacion1 : habitaciones) {
                        if (habitacion1 instanceof Estandar) {
                            if (habitacion1.getCantidadCamas() == camas && habitacion1.getEstado().equals(Estado.DISPONIBLE)) {
                                System.out.println(habitacion1.toString());

                                numero = habitacion1.getNumeroHabitacion();
                                seguir = true;
                            }
                        }
                    }
                    break;
                case 3:
                    for (Habitacion habitacion1 : habitaciones) {
                        if (habitacion1 instanceof Premium) {
                            if (habitacion1.getCantidadCamas() == camas && habitacion1.getEstado().equals(Estado.DISPONIBLE)) {
                                System.out.println(habitacion1.toString());
                                numero = habitacion1.getNumeroHabitacion();
                                seguir = true;
                            }
                        }
                    }
                    break;
            }

            if (seguir==true){
                System.out.println("Ingrese numero de habitacion: ");
                numeroHabitacion = teclado.next();
            }

        }while (numero == numeroHabitacion);

        return numeroHabitacion;
    }

    public String imprimirMenuPasajero(){
        return  "Menu Pasajero\n" +
                "1) Ver su información\n" +
                "2) Ver su reserva\n" +
                "3) Ver su consumo\n" +
                "4) Realizar consumo\n" +
//                "5) Ver historial de reserva\n" +
                "0) Salir\n";
    }

    public void pasajeroMenu(Pasajero pasajero) {
        int opcion;
        boolean si = false;

        do {
            System.out.println(imprimirMenuPasajero());
            opcion = teclado.nextInt();
            String validos = "";
            switch (opcion) {
                case 1:
                    System.out.println(pasajero.toString());
                    break;
                case 2:
                    if(reservas!=null) {
                        for (Reservas reservas1 : reservas) {
                            if (reservas1.getPasajero().getNombre().equals(pasajero.getNombre()) && reservas1.getPasajero().getApellido().equals(pasajero.getApellido())) {
                                System.out.printf(reserva.toString());
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println(pasajero.getConsumos().toString());
                    break;
                case 4:
                    System.out.println(consumos.toString());
                    System.out.println("ingrese que desea consumir");
                    String nombre;
                    nombre=teclado.next();
                    System.out.println("ingrese la cantida");
                    int cantidad;
                    cantidad=teclado.nextInt();
                    if(consumos!=null) {
                        for (Consumo c : consumos) {
                            if (c.getDescripcion().equals(nombre) && c.getCantidad() >= cantidad) {
                                int rta = c.getCantidad() - cantidad;
                                c.setCantidad(rta);
                                double precio = c.getCosto() * cantidad;
                                pasajero.agregarConsumos(c.getDescripcion(), cantidad, precio);
                            }
                        }
                    }
                    pasajeros.remove(pasajero);
                    pasajeros.add(pasajero);
                    escribirPasajeros(objectMapper,pasajeros);
                    escribirConsumos(objectMapper,consumos);

                    break;

            }
        }
            while (opcion != 0) ;
    }

    /*public String imprimirMenuModificarInformacionPasajero(){
        return  "Menu Modificar\n" +
                "1) Usuario\n" +
                "2) Contraseña\n" +
                "0) Salir\n";
    }
    public void modificarInformacionPasajeroMenu(){
        int opcion;

        do {
            System.out.println(imprimirMenuModificarInformacionPasajero());
            opcion = teclado.nextInt();

            switch (opcion){
                case 1:
                    System.out.printf("Ingrese nuevo nombre de usuario. ");

                    break;
                case 2:
                    break;
            }
        }while (opcion!= 0);
    }*/

    public String imprimirMenuPersona(){
        return  "Menu Persona\n" +
                "1) Ver información\n" +
                "2) Reservar\n" +
                "0) Salir\n";
    }
    public void personaMenu(Persona persona){
        int opcion;
        do {
            System.out.println(imprimirMenuPersona());
            opcion = teclado.nextInt();

            switch (opcion){
                case 1:
                    System.out.println(persona.toString());
                    break;
                case 2:
                    String numeroHabitacion = buscarHabitacion();
                    for (Habitacion habitacion1 : habitaciones){
                        if (habitacion1.getNumeroHabitacion().equals(numeroHabitacion)){
                            habitacion = habitacion1;
                        }
                    }

                    System.out.printf("Fecha inicio\n");
                    System.out.printf("Ingresa dia: ");
                    int diai = teclado.nextInt();
                    System.out.printf("Ingresa mes: ");
                    int mesi = teclado.nextInt();
                    System.out.printf("Ingresa año: ");
                    int añoi = teclado.nextInt();
                    LocalDate fechainicio = LocalDate.of(añoi, mesi, diai);

                    System.out.printf("Fecha fin\n");
                    System.out.printf("Ingresa dia: ");
                    int diaf = teclado.nextInt();
                    System.out.printf("Ingresa mes: ");
                    int mesf = teclado.nextInt();
                    System.out.printf("Ingresa año: ");
                    int añof = teclado.nextInt();
                    LocalDate fechafin = LocalDate.of(añof, mesf, diaf);

                    if (reserva.verficacionReserva(reservas,habitacion, fechainicio, fechafin) == true) {
                        habitacion.setEstado(Estado.RESERVADA);
                        reservas.add(new Reservas(persona,habitacion,fechainicio,fechafin));

                        escribirReservas(objectMapper,reservas);
                        escribirHabitaciones(objectMapper,habitaciones);
                    }
                    break;

            }
        }while (opcion!= 0);
    }

    static Administrador leerArchivosAdministrador(ObjectMapper objectMapper){
        File archivoAdmin = new File("Admin.json");
        Administrador administrador1 = new Administrador();
        try{
            administrador1 = objectMapper.readValue(archivoAdmin,Administrador.class);

        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer los archivos");
        }
        return administrador1;
    }
    static ArrayList<Persona> leerArchivosPersonas(ObjectMapper objectMapper){
        File archivoPersona = new File("Persona.json");
        ArrayList<Persona> personas = new ArrayList<>();
        try{
            personas = objectMapper.readValue(archivoPersona, new TypeReference<ArrayList<Persona>>() {});
            //System.out.println("Personas leídos correctamente:");
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer los archivos");
        }
        return personas;
    }
    static ArrayList<Conserje> leerArchivosConserjes(ObjectMapper objectMapper){
        File archivoConserje = new File("Conserje.json");
        ArrayList<Conserje> conserjes = new ArrayList<>();
        try{
            conserjes = objectMapper.readValue(archivoConserje, new TypeReference<ArrayList<Conserje>>() {});
            //System.out.println("Conserjes leídos correctamente:");

        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer los archivos");
        }
        return conserjes;
    }
    static ArrayList<Pasajero> leerArchivosPasajeros (ObjectMapper objectMapper){
        File archivoPasajero = new File("Pasajero.json");
        ArrayList<Pasajero> pasajeros = new ArrayList<>();
        try{
            pasajeros = objectMapper.readValue(archivoPasajero, new TypeReference<ArrayList<Pasajero>>() {});
            //System.out.println("Pasajeros leídos correctamente:");
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer los archivos");
        }
        return pasajeros;
    }
    //NO TOCAR HABITACIONES HACHIVOS SE ROMPE FACIL (DELICADA)
    static ArrayList<Habitacion> leerArchivosHabitaciones (ObjectMapper objectMapper){
        File archivoHabitaciones = new File("Habitacion.json");
        ArrayList<Habitacion> habitacions = new ArrayList<>();
        try{
            habitacions = (ArrayList<Habitacion>) objectMapper.readValue(archivoHabitaciones, new TypeReference<Object>() {});
            //System.out.println("Habitaciones leídas correctamente:");
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer los archivos");
        }
        return habitacions;
    }
    static ArrayList<Reservas> leerArchivosReservas (ObjectMapper objectMapper){
        File archivoReserva = new File("Reserva.json");
        ArrayList<Reservas> reservas = new ArrayList<>();
        try{
            reservas = objectMapper.readValue(archivoReserva, new TypeReference<ArrayList<Reservas>>() {});
            //System.out.println("Reservas leídas correctamente:");
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer los archivos");
        }
        return reservas;
    }
    static ArrayList<Consumo> leerArchivosConsumos(ObjectMapper objectMapper){
        File archivoConsumos = new File("Consumos.json");
        ArrayList<Consumo> consumos = new ArrayList<>();
        try{
            consumos = objectMapper.readValue(archivoConsumos, new TypeReference<ArrayList<Consumo>>() {});
            //System.out.println("Reservas leídas correctamente:");
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer los archivos");
        }
        return consumos;
    }

    static void escribirAdministrador(ObjectMapper objectMapper, Administrador administrador){
        File archivoAdmin = new File("Admin.json");
        try{
            objectMapper.writeValue(archivoAdmin,administrador);
            System.out.println("Admin escritos correctamente en " + archivoAdmin.getAbsolutePath());
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al escribir admin en el archivo JSON.");
        }
    }
    static void escribirPersonas(ObjectMapper objectMapper,ArrayList<Persona> personas){
        File archivoPersona = new File("Persona.json");
        try{
            objectMapper.writeValue(archivoPersona, personas);
            System.out.println("Personas escritos correctamente en " + archivoPersona.getAbsolutePath());
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al escribir las personas en el archivo JSON.");
        }
    }
    static void escribirConserjes(ObjectMapper objectMapper,ArrayList<Conserje> conserjes){
        File archivoConserje = new File("Conserje.json");
        try{
            objectMapper.writeValue(archivoConserje, conserjes);
            System.out.println("Conserjes escritos correctamente en " + archivoConserje.getAbsolutePath());
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al escribir los conserjes en el archivo JSON.");
        }
    }
    static void escribirPasajeros(ObjectMapper objectMapper,ArrayList<Pasajero>pasajeros){
        File archivoPasajero = new File("Pasajero.json");
        try{
            objectMapper.writeValue(archivoPasajero,pasajeros);
            System.out.println("Pasajeros escritos correctamente en " + archivoPasajero.getAbsolutePath());
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al escribir los pasajeros en el archivo JSON.");
        }
    }
    static void escribirHabitaciones(ObjectMapper objectMapper,ArrayList<Habitacion>habitacions){
        File archivoHabitaciones = new File("Habitacion.json");
        try{
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(archivoHabitaciones, habitacions);
            System.out.println("Habitaciones escritas correctamente en " + archivoHabitaciones.getAbsolutePath());
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al escribir las habitaciones en el archivo JSON.");
        }
    }
    static void escribirReservas(ObjectMapper objectMapper,ArrayList<Reservas>reservas){
        File archivoReserva = new File("Reserva.json");
        try{
            objectMapper.writeValue(archivoReserva, reservas);
            System.out.println("Reservas escritas correctamente en " + archivoReserva.getAbsolutePath());
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al escribir las reservas en el archivo JSON.");
        }
    }
    static void escribirConsumos(ObjectMapper objectMapper, ArrayList<Consumo> consumos){
        File archivoConsumos = new File("Consumos.json");
        try{
            objectMapper.writeValue(archivoConsumos, consumos);
            System.out.println("Consumos escritas correctamente en " + archivoConsumos.getAbsolutePath());
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al escribir los consumos en el archivo JSON.");
        }
    }

    public static void mostrarHabitaciones(ArrayList<Habitacion> habitacions){
        System.out.println("\nTipos de Habitaciones:");

        for (Habitacion habitacion : habitacions) {
            if (habitacion instanceof Premium) {
                System.out.printf("\nPREMIUM");
                System.out.println(habitacion);
            } else if (habitacion instanceof Estandar) {
                System.out.printf("\nESTANDAR");
                System.out.println(habitacion);
            } else if (habitacion instanceof Economica) {
                System.out.printf("\nECONOMICA");
                System.out.println(habitacion);
            }
        }
    }

    public static void mostrarHabitacionesPorEstado(ArrayList<Habitacion> habitacions, Estado estado){
        System.out.printf("\n" + estado);
        for (Habitacion habitacion : habitacions){
            if(habitacion.getEstado().equals(estado)){
                if (habitacion instanceof Premium) {
                    System.out.printf("\nPREMIUM");
                    System.out.println(habitacion);
                } else if (habitacion instanceof Estandar) {
                    System.out.printf("\nESTANDAR");
                    System.out.println(habitacion);
                } else if (habitacion instanceof Economica) {
                    System.out.printf("\nECONOMICA");
                    System.out.println(habitacion);
                }
            }
        }
    }

    public static ObjectMapper archivo(){
        //Configuración de ObjectMapper para Jackson
        ObjectMapper objectMapper = new ObjectMapper();

        // Modulos de clase de habitaciones
        SimpleModule module = new SimpleModule();
        module.registerSubtypes(new NamedType(Economica.class, "Economica"));
        module.registerSubtypes(new NamedType(Estandar.class, "Estandar"));
        module.registerSubtypes(new NamedType(Premium.class, "Premium"));

        // Modulos de LocalDate fechas
        module.addSerializer(LocalDate.class, new Main.LocalDateSerializer());
        module.addDeserializer(LocalDate.class, new Main.LocalDateDeserializer());

        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        objectMapper.registerModule(module);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return objectMapper;
    }
}
