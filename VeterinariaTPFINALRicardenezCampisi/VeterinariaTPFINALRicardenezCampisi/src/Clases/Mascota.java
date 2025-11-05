package Clases;

import Enumeradores.ESPECIE;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Mascota {
    private int ID;
    private static int contador;
    private String nombre;
    private int edad;
    private ESPECIE especie;
    private String raza;
    private int dniDuenio;
    private HashSet<Cita> historialClinico;

    public Mascota(String nombre, int edad, ESPECIE especie, String raza, int dniDuenio) {
        this.ID = ++contador;
        this.nombre = nombre;
        this.edad = edad;
        this.especie = especie;
        this.raza = raza;
        this.dniDuenio = dniDuenio;
        this.historialClinico = new HashSet<>();
    }

    public int getID() {
        return ID;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ESPECIE getEspecie() {
        return especie;
    }

    public void setEspecie(ESPECIE especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getDniDuenio() {
        return dniDuenio;
    }

    @Override
    public String toString() {
        return "Clases.Mascota{" +
                "ID=" + ID +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", especie=" + especie +
                ", raza='" + raza + '\'' +
                ", dniDuenio=" + dniDuenio +
                ", historialClinico=" + historialClinico +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Mascota mascota = (Mascota) o;
        return ID == mascota.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ID);
    }

    public String listarHistorialClinico(){
        StringBuilder sb = new StringBuilder(" ");
        Iterator<Cita> it = historialClinico.iterator();
        Cita cita = null;
        while(it.hasNext()){
            cita = it.next();
            sb.append(cita).append("\n");
        }

        return sb.toString();
    }
    public boolean agregarCita(Cita cita){
        boolean seAgrego = false;
        if(!historialClinico.contains(cita)){
            historialClinico.add(cita);
            seAgrego = true;
        } else {
            System.out.println("ExcepcionExistente");
        }

        return seAgrego;
    }

    public boolean eliminarCita(Cita cita){
        boolean seElimino = false;

        if(!historialClinico.isEmpty()){
            if(historialClinico.contains(cita)){
                historialClinico.remove(cita);
                seElimino = true;
            }else {
                System.out.println("ExcepcionInexistente");
            }
        } else {
            System.out.println("ExcepcionContenedorVacio");
        }
        return seElimino;
    }
}
