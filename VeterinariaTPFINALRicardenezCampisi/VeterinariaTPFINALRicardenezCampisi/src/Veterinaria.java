import Clases.Cita;
import Clases.Duenio;
import Clases.Empleado;

public class Veterinaria {
    private static final String nombre = "BETTYLAFEA"; /// Guardar en el archivo segun el profe y lo de gmailAdmin + contraAdmin tambien || COMO???
    private static final String direccion = "Juan B. Justo 492"; ///  Guardar en el archivo segun el profe
    private Gestor<Empleado> Personal; ///veterinarios y recepcionistas
    private Gestor<Duenio> Duenios;
    private Gestor<Cita> Citas;

    public Veterinaria(){
        Personal = new Gestor<>();
        Duenios = new Gestor<>();
        Citas = new Gestor<>();
    }

    public void agregarEmpleado(Empleado empleado){


    }

    public void agregarDuenios(){

    }

    public void agregarCitas(){

    }
}
