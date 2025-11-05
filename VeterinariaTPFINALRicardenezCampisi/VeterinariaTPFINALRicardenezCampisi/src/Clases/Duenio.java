package Clases;

import java.util.ArrayList;

public class Duenio extends Persona {
    private long telefono;
    private String direccion;
    private ArrayList<Mascota> mascotas;

    public Duenio(String nombre, int edad, int dni, long telefono, String direccion) {
        super(nombre, edad, dni);
        this.telefono = telefono;
        this.direccion = direccion;
        this.mascotas = new ArrayList<>();
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String listarMascotas(){
        StringBuilder sb = new StringBuilder(" ");

        for(Mascota m: mascotas){
            sb.append(m).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Clases.Duenio{" +
                "telefono=" + telefono +
                ", direccion='" + direccion + '\'' +
                ", mascotas=" + mascotas +
                '}';
    }

    public boolean agregarMascota(Mascota mascota){
        boolean seAgrego = false;
        if(!mascotas.contains(mascota)){
            mascotas.add(mascota);
            seAgrego = true;
        } else {
            System.out.println("ExcepcionExistente");
        }
        return seAgrego;
    }

    public boolean eliminarMascota(Mascota mascota) {
        boolean seElimino = false;

        if (!mascotas.isEmpty()) {
            if (mascotas.contains(mascota)) {
                mascotas.remove(mascota);
                seElimino = true;
            } else {
                System.out.println("ExcepcionInexistente");
            }
        } else {
            System.out.println("Excepciones.ExcepcionColeccionVacia");
        }
        return seElimino;
    }
}
