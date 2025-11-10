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


public class Main {
    public static void main(String[] args) {

        try {
            Admin admin = new Admin();
            JSONObject JSONAdmin = admin.toJsonAdmin();
            JSONUtiles.cargaJSONOBJ(JSONAdmin, "Admin");
        }catch (Exception e){
            e.printStackTrace();
        }
        Veterinaria veterinaria = new Veterinaria();

        LocalDate fechaCita = LocalDate.of(2025,11,8);
        LocalDate fechaCita2 = LocalDate.of(2025,11,8);
        LocalTime horarioCita = java.time.LocalTime.of(15,30);
        LocalTime horarioCita2 = java.time.LocalTime.of(15,30);
        String motivo = "Esta cita fue creada para testeo";

        Veterinario Vettester =new Veterinario("Juan",28,1234,"juan123@gmail.com","1234", TURNO.TARDE,"Veterinario");
        Veterinario Vettester2 =new Veterinario("Pedro",31,412,"pepe533@gmail.com","654", TURNO.TARDE,"Veterinario");
        Mascota TobyTester = new Mascota("TobyTester",2, ESPECIE.CANINO,"Rottweiler",11223344);
        Mascota CocoTester = new Mascota("CocoTester",1, ESPECIE.CANINO,"Pastor Aleman",11223344);
        Cita cita = new Cita (fechaCita,horarioCita, TIPOCITA.CONTROL, TobyTester, ESTADOCITA.PENDIENTE,Vettester);

        System.out.println(cita.toString());

        JSONObject jsonObject = cita.citaTOJson();
        System.out.println("json object cita:");
        System.out.println(jsonObject);


        try{    // asi tira eror por el veterinario ocupado
            //veterinaria.agregarCita(fechaCita,horarioCita, TIPOCITA.CONTROL, TobyTester, ESTADOCITA.PENDIENTE,Vettester);
            //veterinaria.agregarCita(fechaCita,horarioCita, TIPOCITA.CONTROL, CocoTester, ESTADOCITA.PENDIENTE,Vettester);


                //asi por la mascota que ya tiene asignada una cita
                veterinaria.agregarCita(fechaCita,horarioCita, TIPOCITA.CONTROL, TobyTester, ESTADOCITA.PENDIENTE,Vettester);
                veterinaria.agregarCita(fechaCita,horarioCita, TIPOCITA.CONTROL, TobyTester, ESTADOCITA.PENDIENTE,Vettester2);

            // aca anda bien
            //veterinaria.agregarCita(fechaCita,horarioCita, TIPOCITA.CONTROL, TobyTester, ESTADOCITA.PENDIENTE,Vettester);
            //veterinaria.agregarCita(fechaCita,horarioCita, TIPOCITA.CONTROL, CocoTester, ESTADOCITA.PENDIENTE,Vettester2);

        }catch (CitaInvalidaExcep c){
            System.out.println(""+c.getMessage());
        }
    }
}