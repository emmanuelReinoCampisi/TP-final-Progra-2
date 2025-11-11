package Clases;

import Enumeradores.TURNO;
import Excepciones.ExcepcionYaExistente;
import org.json.JSONObject;

public class Empleado extends Persona{
    private String email;
    private String contrasenia;
    private TURNO turno;
    private boolean cuenta_activa;

    public Empleado(String nombre, int edad, int dni, String email, String contrasenia, TURNO turno) {
        super(nombre, edad, dni);
        this.email = email;
        this.contrasenia = contrasenia;
        this.turno = turno;
        this.cuenta_activa = false; /// al crear la cuenta se settea en true
    }

    public Empleado(String nombre, int edad, int dni, String email, String contrasenia, TURNO turno, boolean cuenta_activa) {
        super(nombre, edad, dni);
        this.email = email;
        this.contrasenia = contrasenia;
        this.turno = turno;
        this.cuenta_activa = cuenta_activa; /// al crear la cuenta se settea en true
    }
    public String getEmail() {
        return email;
    }


    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public TURNO getTurno() {
        return turno;
    }

    public void setTurno(TURNO turno) {
        this.turno = turno;
    }

    public boolean isCuenta_activa() {
        return cuenta_activa;
    }

    public void setCuenta_activa(boolean cuenta_activa) {
        this.cuenta_activa = cuenta_activa;
    }

    public JSONObject TOJSON(){
        JSONObject empleadoJSON = new JSONObject();
        empleadoJSON.put("nombre",getNombre());
        empleadoJSON.put("edad",getEdad());
        empleadoJSON.put("dni",getDni());
        empleadoJSON.put("email",email);
        empleadoJSON.put("contrasenia",contrasenia);
        empleadoJSON.put("turno",turno);
        empleadoJSON.put("cuenta_activa",cuenta_activa);
        return empleadoJSON;
    }

    public static Empleado empleadoFROMJson(JSONObject empleadoJSON) {
        Empleado empleado = null;
        String nombre = empleadoJSON.getString("nombre");
        int edad = empleadoJSON.getInt("edad");
        int dni = empleadoJSON.getInt("dni");
        String email = empleadoJSON.getString("email");
        String contrasenia = empleadoJSON.getString("contrasenia");
        TURNO turno = TURNO.valueOf(empleadoJSON.getString("turno"));
        boolean cuenta_activa = empleadoJSON.getBoolean("cuenta_activa");

        empleado = new Empleado(nombre,edad,dni,email,contrasenia,turno,cuenta_activa);
        return empleado;
    }
}
