package Clases;

import Enumeradores.ESPECIE;
import Enumeradores.TURNO;
import Excepciones.ExcepcionColeccionVacia;
import Excepciones.ExcepcionNoExistente;
import Excepciones.ExcepcionYaExistente;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

public class Veterinario extends Empleado {
    private String matricula;
    private ArrayList <ESPECIE> especialidades;
    private HashSet<Cita> citas;

    public Veterinario(String nombre, int edad, int dni, String email, String contrasenia, TURNO turno, String matricula) {
        super(nombre, edad, dni, email, contrasenia, turno);
        this.matricula = matricula;
        this.especialidades = new ArrayList<>();
        this.citas = new HashSet<>();
    }


    public void asignarCita(Cita cita){
        citas.add(cita);
    }
    public String getMatricula() {
        return matricula;
    }


    public String listarEspecialidades() throws ExcepcionColeccionVacia {
        StringBuilder sb = new StringBuilder(" ");
    if(!especialidades.isEmpty()) {
        for (ESPECIE e : especialidades) {
            sb.append(e).append("\n");
        }
    } else{
        throw new ExcepcionColeccionVacia("El veterinario no cuenta con especialidades");
    }
        return sb.toString();
    }

    public boolean agregarEspecialidad(ESPECIE espec) throws ExcepcionYaExistente {
        boolean seAgrego = false;
        if(!especialidades.contains(espec)){
            especialidades.add(espec);
            seAgrego = true;
        } else {
            throw new ExcepcionYaExistente("Especialidad ya registrada");
        }

        return seAgrego;
    }

    public boolean eliminarEspecialidad(ESPECIE espec)throws ExcepcionNoExistente, ExcepcionColeccionVacia{
        boolean seElimino = false;

        if(!especialidades.isEmpty()){
            if(especialidades.contains(espec)){
                especialidades.remove(espec);
                seElimino = true;
            }else {
                throw new ExcepcionNoExistente("El veterinario no cuenta con esta especialidad");
            }
        }else {
            throw new ExcepcionColeccionVacia("El veterinario no cuenta con ninguna especialidad");
        }

        return seElimino;
    }

    @Override
    public String getIdentificador() {
        return this.matricula;
    }


    public JSONObject TOJSON(){
        JSONObject empleadoJSON = new JSONObject();
        JSONArray especialidadesARRAY = new JSONArray();
        JSONArray citasARRAY = new JSONArray();
        for (ESPECIE e: especialidades){
            especialidadesARRAY.put(e.toString());
        }
        for(Cita c: citas){
            citasARRAY.put(c.citaTOJson());
        }
        empleadoJSON.put("nombre",getNombre());
        empleadoJSON.put("edad",getEdad());
        empleadoJSON.put("dni",getDni());
        empleadoJSON.put("email",getEmail());
        empleadoJSON.put("contrasenia",getContrasenia());
        empleadoJSON.put("turno",getTurno());
        empleadoJSON.put("cuenta_activa",isCuenta_activa());
        empleadoJSON.put("matricula",matricula);
        empleadoJSON.put("especialidades",especialidadesARRAY);
        empleadoJSON.put("citas",citasARRAY);
        return empleadoJSON;
    }
}
