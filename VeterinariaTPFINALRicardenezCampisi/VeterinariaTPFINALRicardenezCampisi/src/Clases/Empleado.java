package Clases;

import Enumeradores.TURNO;

public class Empleado extends Persona{
    private String email;
    private String contrasenia;
    private TURNO turno;

    public Empleado(String nombre, int edad, int dni, String email, String contrasenia, TURNO turno) {
        super(nombre, edad, dni);
        this.email = email;
        this.contrasenia = contrasenia;
        this.turno = turno;
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


}
