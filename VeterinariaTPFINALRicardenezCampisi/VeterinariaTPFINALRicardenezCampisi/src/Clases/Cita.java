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
    private LocalDate fecha;
    /// Se cambio "LocalDateTime fecha" a LocalDate y LocalTime.
    private LocalTime horario;
    /// LocalDate almacena un dia, LocalTime un horario.
    private TIPOCITA motivo;
    /// hay motivos predefinidos mas generales
    private int mascota_id;
    private ESTADOCITA estadoCita;
    private int veterinario_id;


/// Constructor general
    public Cita(LocalDate fecha, LocalTime horario, TIPOCITA motivo, ESTADOCITA estadoCita,int mascota_id, int veterinario_id) {
        this.idCita = ++contador;
        this.fecha = fecha;
        this.horario = horario;
        this.motivo = motivo;
        this.mascota_id = mascota_id;
        this.estadoCita = estadoCita;
        this.veterinario_id = veterinario_id;
    }
/// Constructor para el fromJSON
    public Cita(int id, LocalDate fecha, LocalTime horario, TIPOCITA motivo, ESTADOCITA estadoCita, int mascota_id, int veterinario_id) {
        this.idCita = id;
        this.fecha = fecha;
        this.horario = horario;
        this.motivo = motivo;
        this.mascota_id = mascota_id;
        this.estadoCita = estadoCita;
        this.veterinario_id = veterinario_id;
    }

    public void setMascota_id(int mascota_id) {
        this.mascota_id = mascota_id;
    }

    public void setVeterinario_id(int veterinario_id) {
        this.veterinario_id = veterinario_id;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
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

    public LocalTime getFinCita(){
        return horario.plusMinutes(motivo.getDuracionMinutos()); // aca sabemos a que hora estimada termina la cita
    }

    public int getMascota_id() {
        return mascota_id;
    }

    public int getVeterinario_id() {
        return veterinario_id;
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
                ", id_mascota" + mascota_id +
                ", estadoCita=" + estadoCita +
                ", id_veterinario" + veterinario_id +
                '}';
    }

     public JSONObject citaTOJson (){ /// Metodo para pasar una cita a JSONObject
        JSONObject citaJSON = new JSONObject();
        citaJSON.put("id_cita",idCita);
        citaJSON.put("fecha",fecha);
        citaJSON.put("horario",horario);
        citaJSON.put("motivo",motivo);
         citaJSON.put("estado_cita",estadoCita);
        citaJSON.put("id_mascota", mascota_id); // Se paso solamente el ID de la mascota para evitar un bucle donde citaTOJson llame a mascotaTOJson para que esta llame a citaTOJson nuevamente
        citaJSON.put("id_veterinario", veterinario_id);
        return citaJSON;
    }

    public static Cita citaFROMJson(JSONObject citaJSON){  /// Al guardar solamente el id de la mascota la cita queda con la mascota vacia. Aca hay que cambiar las relaciones que tienen las clases
        Cita cita = null;                                                  /// Esto tambien paso con Veterinario.
        int id_cita = citaJSON.getInt("id_cita");  ///Hay que fijarse que no se cambien los ids.
        String fechaString =citaJSON.getString("fecha");
        LocalDate fecha = LocalDate.parse(fechaString);

        String horarioString = citaJSON.getString("horario");
        LocalTime horario = LocalTime.parse(horarioString);

        TIPOCITA motivo = TIPOCITA.valueOf(citaJSON.getString("motivo"));
        ESTADOCITA estadocita = ESTADOCITA.valueOf(citaJSON.getString("estado_cita"));
        int id_mascota = citaJSON.getInt("id_mascota");
        int id_veterinario = citaJSON.getInt("id_veterinario");

        cita = new Cita(id_cita,fecha,horario,motivo,estadocita,id_mascota,id_veterinario);
        return cita;
    }

    public int getId() {
        return this.idCita;
    }
}
