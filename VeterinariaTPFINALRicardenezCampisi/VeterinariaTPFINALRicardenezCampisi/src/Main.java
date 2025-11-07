//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import Clases.Duenio;
import Clases.Cita;
import Clases.Empleado;
import Clases.Mascota;
import Clases.Persona;
import Clases.Veterinario;
import Enumeradores.ESPECIE;
import Enumeradores.ESTADOCITA;
import Enumeradores.TURNO;
import Excepciones.ExcepcionNoExistente;
import Excepciones.ExcepcionYaExistente;
import Excepciones.ExcepcionColeccionVacia;
import org.json.JSONObject;


public class Main {
    public static void main(String[] args) {

        try {
            Admin admin = new Admin();
            JSONObject JSONAdmin = admin.toJsonAdmin();
            JSONUtiles.cargaJSONOBJ(JSONAdmin, "Admin");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}