import Clases.*;
import Enumeradores.ESPECIE;
import Enumeradores.ESTADOCITA;
import Enumeradores.TIPOCITA;
import Enumeradores.TURNO;
import Excepciones.CitaInvalidaExcep;
import Excepciones.ExcepcionYaExistente;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;

public class Veterinaria {
    private static final String nombre = "VETPET"; /// Guardar en el archivo segun el profe y lo de gmailAdmin + contraAdmin tambien || COMO???
    private static final String direccion = "Juan B. Justo 492"; ///  Guardar en el archivo segun el profe
    private Gestor<Empleado> Personal; ///veterinarios y recepcionistas
    private Gestor<Duenio> Duenios;
    private Gestor<Cita> Citas;

    public Veterinaria(){
        Personal = new Gestor<>();
        Duenios = new Gestor<>();
        Citas = new Gestor<>();
    }


    public void agregarEmpleado(String nombre, int edad, int dni, String email, String contrasenia, TURNO turno)throws ExcepcionYaExistente {
        Empleado empleado = new Empleado(nombre, edad, dni, email, contrasenia, turno);
        try{
                if(Personal.existe(empleado)){
                    Personal.agregar(empleado);
                }else{
                    throw new ExcepcionYaExistente("Empleado existente");
                }

        }catch (ExcepcionYaExistente e){
            System.out.println(e.getMessage());
        }

    }

    public String listarEmpleados(){
        String lista = "";
        return lista += Personal.listar();
    }

public void agregarCita (LocalDate fecha, LocalTime horario, TIPOCITA motivo, Mascota mascota, ESTADOCITA estadoCita, Veterinario veterinario)throws CitaInvalidaExcep {
        Cita c = new Cita(fecha, horario, motivo, mascota, estadoCita,veterinario);

        if(c.getFecha().isBefore(LocalDate.now())){
            throw new CitaInvalidaExcep("Fecha invalida");
        }

        LocalTime finCitaNueva = c.getHorario().plusMinutes(c.getMotivo().getDuracionMinutos()); /// calculamos el horario aproximado del fin de la cita que queremos cargar

    // ahora vamos a comprobar que este el horario dispo y el veterinario tambien

    Iterator <Cita> it = Citas.getIterator();
    while(it.hasNext()){
        Cita c2 = it.next(); // citas existentes

        LocalTime horarioInicioExistente = c2.getHorario(); // tomamos el horario que arranca
        LocalTime horarioFinExistente = c2.getFinCita(); // tomamos el horario que termina aprox

        // verifico la fecha de la cita
        if (c.getFecha().equals(c2.getFecha())) {
            // comprobar que el veterinario este libre
            if (c2.getVeterinarioAcargo().equals(veterinario)) {

                boolean vetOcupado = c.getHorario().isBefore(horarioFinExistente) && finCitaNueva.isAfter(horarioInicioExistente);
                if (vetOcupado) {
                    throw new CitaInvalidaExcep("Veterinario ocupado en ese dia y horario: " + c.getMotivo());
                }
            }
            // verifico que la mascota no tenga ninguna cita agendada en esa fecha y horario
            if (c2.getMascota().equals(mascota)) {

                boolean mascotaAsig = c.getHorario().isBefore(horarioFinExistente) && finCitaNueva.isAfter(horarioInicioExistente);
                if (mascotaAsig) {
                    throw new CitaInvalidaExcep("La mascota ya tiene una cita en ese dia y horario: " + c.getMotivo());
                }
            }

        }

    }
    // si no se solapa con ninguna existente y es veterinario esta libre la agrega
    Citas.agregar(c);
    veterinario.asignarCita(c); // le asignamos la cita al veterinario
}



    // metodo para agregar un duenio nuevo que no este cargado en el sistema
    public void agregarDuenioNuevo(String nombre, int edad, int dni, long telefono, String direccion, String nombreM, int edadM, ESPECIE especie, String raza, int dniDuenio)throws ExcepcionYaExistente{
        Duenio nuevo = new Duenio(nombre, edad, dni, telefono, direccion);
        Mascota nueva = new Mascota(nombre, edad, especie, raza, dniDuenio);

        if(Duenios.existe(nuevo)){
            throw new ExcepcionYaExistente("Duenio ya existente");
        }else{
            nuevo.agregarMascota(nueva);
            Duenios.agregar(nuevo);
        }
    }


    public String buscarDuenioPorDNI(int dni)
    {
        String lista = "";
        return lista = Duenios.buscarPorId(dni);

    }

    public String buscarEmpleadoPorId(String identificador) /// aca lo que le vamos a mandar es el mail o matricula del empleado ya que son unicos
    {
        String lista = "";
        return lista = Personal.buscarPorIdentificador(identificador);
    }


}
