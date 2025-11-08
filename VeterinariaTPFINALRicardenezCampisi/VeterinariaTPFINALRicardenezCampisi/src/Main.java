//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import Clases.*;
import Enumeradores.ESPECIE;
import Enumeradores.ESTADOCITA;
import Enumeradores.TIPOCITA;
import Enumeradores.TURNO;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;


public class Main {
    public static void main(String[] args) {

        try {
            Admin admin = new Admin();
            JSONObject JSONAdmin = admin.toJsonAdmin();
            JSONUtiles.cargaJSONOBJ(JSONAdmin, "Clases.Admin");
        }catch (Exception e){
            e.printStackTrace();
        }
        LocalDate fechaCita = LocalDate.of(2025,11,7);
        LocalTime horarioCita = java.time.LocalTime.of(10,30);
        String motivo = "Esta cita fue creada para testeo";

        Mascota TobyTester = new Mascota("TobyTester",2, ESPECIE.CANINO,"Rottweiler",11223344);
        Cita cita = new Cita (fechaCita,horarioCita, TIPOCITA.CONTROL, TobyTester, ESTADOCITA.PENDIENTE,new Veterinario("Juan",28,1234,"juan123@gmail.com","1234", TURNO.MANIANA,"Veterinario"));

        System.out.println(cita.toString());

        JSONObject jsonObject = cita.citaTOJson();
        System.out.println("json object cita:");
        System.out.println(jsonObject);
    }
}