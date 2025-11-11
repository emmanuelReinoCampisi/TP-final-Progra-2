//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import Clases.*;
import Enumeradores.ESPECIE;
import Enumeradores.ESTADOCITA;
import Enumeradores.TIPOCITA;
import Enumeradores.TURNO;
import Excepciones.CitaInvalidaExcep;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        Veterinaria veterinaria = new Veterinaria();
        boolean salir = false;

        System.out.println("🐾----Bienvenido al sistema de: " + veterinaria.getNombre() + " ----🐾");
        while (!salir) {
            System.out.println("💠 MENU PRINCIPAL 💠");
            System.out.println("1. Ingresar como Recepcionista");
            System.out.println("2. Ingresar como Veterinario");
            System.out.println("3. Ingresar modo Admin");
            System.out.println("4. Salir del menu");
            System.out.println("Ingrese una opcion....");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {

                case 1:
                    int opcionRecep;
                    do {
                        System.out.println("==== MENU RECEPCIONISTA ====");
                        System.out.println("1. Asignar una cita");
                        System.out.println("2. Cancelar una cita");
                        System.out.println("3. Listar citas pendientes");
                        System.out.println("4. Registrar nuevo Dueño");
                        System.out.println("5. Registrar nueva Mascota");
                        System.out.println("6. Guardar y salir");
                        System.out.println("Ingrese una opcion....");
                        opcionRecep = sc.nextInt();
                        sc.nextLine();
                        switch (opcionRecep) {
                            case 1:

                                break;

                            case 2:

                                break;


                            case 3:

                                break;

                            case 4:

                                break;

                            case 5:

                                break;

                            case 6:
                                System.out.println("Cerrando sesion....");
                                break;

                            default:
                                System.out.println("Opcion inavalida, ingrese una nueva opcion...");
                                break;

                        }
                    } while (opcionRecep != 6);

                    break;


                case 2:
                    int opcionVet;

                    do {
                        System.out.println("==== MENU VETERINARIO ===");
                        System.out.println("1. Listar citas pendientes");
                        System.out.println("2. Listar animales atendidos");
                        System.out.println("3. Agregar diagnostico");
                        System.out.println("4. Guardar y salir");
                        System.out.println();
                        opcionVet = sc.nextInt();
                        sc.nextLine();

                        switch (opcionVet) {
                            case 1:

                                break;


                            case 2:

                                break;


                            case 3:

                                break;


                            case 4:
                                System.out.println("Cerrando sesion....");
                                break;

                            default:
                                System.out.println("Opcion inavlida, ingrese una nueva opcion...");
                                break;
                        }
                    } while (opcionVet != 4);
                    /// MENU VETERINARIO
                    break;

                case 3:
                    int opcionAdmin;
                    do {
                        System.out.println("===== MODO ADMIN =====");
                        System.out.println("1. Crear Empleado");
                        System.out.println("2. Listar Empleados");
                        System.out.println("3. Listar Duenios");
                        System.out.println("4. Desactivar Cuenta");
                        System.out.println("5. Guardar y Salir");
                        System.out.println("Opcion...");
                        opcionAdmin = sc.nextInt();
                        sc.nextLine();
                        switch (opcionAdmin) {
                            case 1:

                                break;

                            case 2:

                                break;

                            case 3:

                                break;

                            case 4:

                                break;

                            case 5:

                                break;

                            default:
                                System.out.println("Opcion inavlida, ingrese una nueva opcion...");
                                break;
                        }

                    } while (opcionAdmin != 5);
                    break;


                case 4:
                    System.out.println("⚙️-CERRANDO EL SISTEMA-⚙️");
                    /// llamamos al metodo que persiste los datos en el archivo
                    salir = true;
                    break;
                default:
                    System.out.println("⚠️-INGRESE UNA OPCION VALIDA-⚠️");
            }

            sc.close();
        }


        LocalDate fechaCita = LocalDate.of(2025, 11, 8);
        LocalDate fechaCita2 = LocalDate.of(2025, 11, 8);
        LocalTime horarioCita = java.time.LocalTime.of(15, 30);
        LocalTime horarioCita2 = java.time.LocalTime.of(15, 30);
        String motivo = "Esta cita fue creada para testeo";

        Veterinario Vettester = new Veterinario("Juan", 28, 1234, "juan123@gmail.com", "1234", TURNO.TARDE, "Veterinario");
        Veterinario Vettester2 = new Veterinario("Pedro", 31, 412, "pepe533@gmail.com", "654", TURNO.TARDE, "Veterinario");
        Mascota TobyTester = new Mascota("TobyTester", 2, ESPECIE.CANINO, "Rottweiler", 11223344);
        Mascota CocoTester = new Mascota("CocoTester", 1, ESPECIE.CANINO, "Pastor Aleman", 11223344);
        Cita cita = new Cita(fechaCita, horarioCita, TIPOCITA.CONTROL, ESTADOCITA.PENDIENTE, TobyTester.getID(), Vettester.getDni());

        System.out.println(cita.toString());

        JSONObject jsonObject = cita.citaTOJson();
        System.out.println("json object cita:");
        System.out.println(jsonObject);


        try {    // asi tira eror por el veterinario ocupado
            //veterinaria.agregarCita(fechaCita,horarioCita, TIPOCITA.CONTROL, TobyTester, ESTADOCITA.PENDIENTE,Vettester);
            //veterinaria.agregarCita(fechaCita,horarioCita, TIPOCITA.CONTROL, CocoTester, ESTADOCITA.PENDIENTE,Vettester);


            //asi por la mascota que ya tiene asignada una cita
            //veterinaria.agregarCita(fechaCita,horarioCita, TIPOCITA.CONTROL, TobyTester, ESTADOCITA.PENDIENTE,Vettester);
            //veterinaria.agregarCita(fechaCita,horarioCita, TIPOCITA.CONTROL, TobyTester, ESTADOCITA.PENDIENTE,Vettester2);

            // aca anda bien
            //veterinaria.agregarCita(fechaCita,horarioCita, TIPOCITA.CONTROL, TobyTester, ESTADOCITA.PENDIENTE,Vettester);
            //veterinaria.agregarCita(fechaCita,horarioCita, TIPOCITA.CONTROL, CocoTester, ESTADOCITA.PENDIENTE,Vettester2);

        } catch (CitaInvalidaExcep c) {
            System.out.println("" + c.getMessage());
        }
    }

}