package Clases;

import Enumeradores.ESPECIE;
import Enumeradores.ESTADOCITA;

import java.time.LocalDateTime;
import java.util.Objects;

public class Cita {
    private int idCita;
    private static int contador = 1000;
    private LocalDateTime fecha;
    private String motivo;
    private Mascota mascota;
    private ESTADOCITA estadoCita;

    public Cita(int idCita, LocalDateTime fecha, String motivo, Mascota mascota, ESTADOCITA estadoCita) {
        this.idCita = ++contador;
        this.fecha = fecha;
        this.motivo = motivo;
        this.mascota = mascota;
        this.estadoCita = estadoCita;
    }

    public ESTADOCITA getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(ESTADOCITA estadoCita) {
        this.estadoCita = estadoCita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getIdCita() {
        return idCita;
    }

    public String getNombreMascota(){
        return this.mascota.getNombre();
    }

    public int getEdadMascota(){
        return this.mascota.getEdad();
    }

    public ESPECIE getEspecieMascota(){
        return mascota.getEspecie();
    }

    public String getRazaMascota(){
        return this.mascota.getRaza();
    }

    public int getDniDuenioMascota(){
        return this.mascota.getDniDuenio();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cita cita = (Cita) o;
        return idCita == cita.idCita;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idCita);
    }
}
