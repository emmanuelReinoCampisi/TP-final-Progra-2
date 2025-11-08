package Clases;

import Enumeradores.ESPECIE;
import Enumeradores.ESTADOCITA;
import Enumeradores.TIPOCITA;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Cita {
    private int idCita;
    private static int contador = 1000;
    private LocalDate fecha; /// Se cambio "LocalDateTime fecha" a LocalDate y LocalTime.
    private LocalTime horario; /// LocalDate almacena un dia, LocalTime un horario.
    private TIPOCITA motivo; /// hay motivos predefinidos mas generales
    private Mascota mascota;
    private ESTADOCITA estadoCita;
    private Veterinario veterinarioAcargo;

    public Cita(LocalDate fecha, LocalTime horario, TIPOCITA motivo, Mascota mascota, ESTADOCITA estadoCita,Veterinario veterinarioAcargo) {
        this.idCita = ++contador;
        this.fecha = fecha;
        this.horario = horario;
        this.motivo = motivo;
        this.mascota = mascota;
        this.estadoCita = estadoCita;
        this.veterinarioAcargo = veterinarioAcargo;
    }

    public ESTADOCITA getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(ESTADOCITA estadoCita) {
        this.estadoCita = estadoCita;
    }

    public TIPOCITA getMotivo() {
        return motivo;
    }

    public void setMotivo(TIPOCITA motivo) {
        this.motivo = motivo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
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

    public Veterinario getVeterinarioAcargo() {
        return veterinarioAcargo;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public LocalTime getFinCita(){
        return horario.plusMinutes(motivo.getDuracionMinutos()); // aca sabemos a que hora estimada termina la cita
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

    @Override
    public String toString() {
        return "Cita{" +
                "idCita=" + idCita +
                ", fecha=" + fecha +
                ", horario=" + horario +
                ", motivo='" + motivo + '\'' +
                 " " + mascota +
                ", estadoCita=" + estadoCita +
                '}';
    }

     public JSONObject citaTOJson (){ /// Metodo para pasar una cita a JSONObject
        JSONObject citaJSON = new JSONObject();
        citaJSON.put("id_cita",idCita);
        citaJSON.put("fecha",fecha);
        citaJSON.put("motivo",motivo);
        citaJSON.put("id_mascota", mascota.getID()); // Se paso solamente el ID de la mascota para evitar un bucle donde citaTOJson llame a mascotaTOJson para que esta llame a citaTOJson nuevamente
        citaJSON.put("estado_cita",estadoCita);
        return citaJSON;
    }
}
