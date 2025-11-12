    import Clases.*;
    import Enumeradores.ESPECIE;
    import Enumeradores.ESTADOCITA;
    import Enumeradores.TIPOCITA;
    import Enumeradores.TURNO;
    import Excepciones.CitaInvalidaExcep;
    import Excepciones.ExcepcionNoExistente;
    import Excepciones.ExcepcionYaExistente;
    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.time.LocalDate;
    import java.time.LocalDateTime;
    import java.time.LocalTime;
    import java.time.chrono.ChronoLocalDate;
    import java.util.Iterator;

    public class Veterinaria {
        private final String nombre = "VETPET"; /// Guardar en el archivo segun el profe y lo de gmailAdmin + contraAdmin tambien || COMO???
        private final String direccion = "Juan B. Justo 492"; ///  Guardar en el archivo segun el profe
        private final String emailAdmin = "adminVeterinaria@gmail.com";
        private final String contraseniaAdmin = "Perrunos123";
        private Gestor<Empleado> Personal; ///veterinarios y recepcionistas
        private Gestor<Duenio> Duenios;
        private Gestor<Cita> Citas;

        public Veterinaria(){
            Personal = new Gestor<>();
            Duenios = new Gestor<>();
            Citas = new Gestor<>();
        }

        public String getNombre() {
            return nombre;
        }

        public void agregarEmpleado(String nombre, int edad, int dni, String email, String contrasenia, TURNO turno)throws ExcepcionYaExistente {
            Empleado empleado = new Empleado(nombre, edad, dni, email, contrasenia, turno);
            try{
                    if(Personal.existe(empleado)){
                        throw new ExcepcionYaExistente("Empleado existente");
                    }else{

                        Personal.agregar(empleado);
                    }

            }catch (ExcepcionYaExistente e){
                System.out.println(e.getMessage());
            }

        }

        public String listarEmpleados(){
            String lista = "";
            return lista += Personal.listar();
        }



    public void agregarCita (LocalDate fecha, LocalTime horario, TIPOCITA motivo,int idMascota, ESTADOCITA estadoCita, int  dniVet)throws CitaInvalidaExcep, ExcepcionNoExistente {
            Cita c = new Cita(fecha, horario, motivo, estadoCita,idMascota,dniVet);
            Veterinario vet = (Veterinario) Personal.obtenerPorIdentificador(dniVet);

            Validaciones.validarFecha(fecha);
            Validaciones.validarHorarioRango(horario);
            LocalTime finCitaNueva = c.getHorario().plusMinutes(c.getMotivo().getDuracionMinutos()); /// calculamos el horario aproximado del fin de la cita que queremos cargar

        // ahora vamos a comprobar que este el horario dispo y el veterinario tambien

        Iterator <Cita> it = Citas.getIterator();
        while(it.hasNext()) {
            Cita c2 = it.next(); // citas existentes

            LocalTime horarioInicioExistente = c2.getHorario(); // tomamos el horario que arranca
            LocalTime horarioFinExistente = c2.getFinCita(); // tomamos el horario que termina aprox

            // verifico la fecha de la cita
            if (c.getFecha().equals(c2.getFecha())) {
                // comprobar que el veterinario este libre/exista
                if (vet != null) {
                    if (c2.getVeterinario_dni() == dniVet) {

                        boolean vetOcupado = c.getHorario().isBefore(horarioFinExistente) && finCitaNueva.isAfter(horarioInicioExistente);
                        if (vetOcupado) {
                            throw new CitaInvalidaExcep("Veterinario ocupado en ese dia y horario: " + c.getMotivo());
                        }
                    }
                    // verifico que la mascota no tenga ninguna cita agendada en esa fecha y horario
                    if (c2.getMascota_id() == idMascota) {

                        boolean mascotaAsig = c.getHorario().isBefore(horarioFinExistente) && finCitaNueva.isAfter(horarioInicioExistente);
                        if (mascotaAsig) {
                            throw new CitaInvalidaExcep("La mascota ya tiene una cita en ese dia y horario: " + c.getMotivo());
                        }
                    }

                }else {throw new ExcepcionNoExistente("Veterinario no existente");}
            }

        }
        // si no se solapa con ninguna existente y es veterinario esta libre la agrega
        Citas.agregar(c);

        if(Personal.existe(vet)){
            vet.asignarCita(c);
        }
    } /// Este metodo de aca se tendria que rehacer con los cambios a cita


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


        public void asignarDiagnostico(int idCita, int dniVet, String diagnostico)throws ExcepcionNoExistente{

            boolean citaEncontrada = false;

            Iterator<Cita> it = Citas.getIterator();
            while(it.hasNext()) {
                Cita c = it.next();
                if(c.getId() == idCita && c.getVeterinario_dni() == dniVet){

                    c.setDiagnostico(diagnostico);
                    c.setEstadoCita(ESTADOCITA.ATENDIDA);
                    citaEncontrada = true;
                }
            }

            if(!citaEncontrada){
                throw new ExcepcionNoExistente("No se encontro la cita para asignar el diagnostico");
            }

        }

        public String buscarDuenioPorDNI(int dni)
        {
            String lista = "";
            return lista = Duenios.buscarPorId(dni);

        }

        public String buscarEmpleadoPorDNI(int dni) /// aca lo que le vamos a mandar es el mail o matricula del empleado ya que son unicos
        {
            String lista = "";
            return lista = Personal.buscarPorId(dni);
        }

        public String listarMascotas(int dni) {
            String mensaje = "";
            Duenio d = Duenios.obtenerPorIdentificador(dni);
            if (d != null) {
                Iterator<Duenio> it = Duenios.getIterator();
                while (it.hasNext()) {
                    Duenio duenio = it.next();
                    mensaje+=duenio.listarMascotas();
                }
            }
            return mensaje;
        }

        public String listarMascotasEspecifica(int dni, String nombreMascota) {
            String mensaje = "";
            Duenio d = Duenios.obtenerPorIdentificador(dni);
            if (d != null) {
                Iterator<Duenio> it = Duenios.getIterator();
                while (it.hasNext()) {
                    Duenio duenio = it.next();
                    mensaje= duenio.listarMascotasEspecifica(nombreMascota);
                }
            }
            return mensaje;
        }


        public void cancelarCita(LocalDate fecha, LocalTime horario, int dniVet)throws CitaInvalidaExcep{

            Cita citaCancelar = null;

            Iterator<Cita> it = Citas.getIterator();
            while(it.hasNext()) {
                Cita c= it.next();

                if(c.getFecha().equals(fecha)&&c.getHorario().equals(horario)&& c.getVeterinario_dni() == dniVet) {
                    citaCancelar=c;
                    c.setEstadoCita(ESTADOCITA.CANCELADA);
                    c.setHorario(null);
                }
            }

            if(citaCancelar==null){
                throw new CitaInvalidaExcep("Cita no encontrada");
            }

            Empleado vet = Personal.obtenerPorIdentificador(dniVet);

            if(vet instanceof Veterinario){
                Veterinario vetCancelar = (Veterinario)vet;
                vetCancelar.cancelarCita(citaCancelar);
                System.out.println("se cancelo la cita");
            }
        }



      public String listarCitasPendientes()throws ExcepcionNoExistente{
            LocalDate fecha = LocalDate.now();
            String mensaje = "";
            boolean citasPendientes = false;
            Iterator<Cita> it = Citas.getIterator();
            while(it.hasNext()) {
                Cita c= it.next();
                if(c.getFecha().equals(fecha)|| c.getFecha().isAfter(fecha)) {
                    mensaje=c.toString();
                    citasPendientes=true;
                }
            }

            if(citasPendientes==false){
                throw new ExcepcionNoExistente("No hay citas pendientes");
            }

            return mensaje;
      }




        public JSONObject toJSONVET(){
            JSONObject jsonVet = new JSONObject();

            try {
                jsonVet.put("nombre", nombre);
                jsonVet.put("direccion",direccion);
                jsonVet.put("email_admin",emailAdmin);
                jsonVet.put("contrasenia_admin",contraseniaAdmin);
                //Empleados
                JSONArray empleadosJSON = jsonVet.getJSONArray("empleados");
                Iterator<Empleado> itE = Personal.getIterator();
                while (itE.hasNext()) {
                    empleadosJSON.put(itE.next().TOJSON());
                }
                jsonVet.put("personal", empleadosJSON);


                // Duenios
                JSONArray dueniosJSON = jsonVet.getJSONArray("duenios");
                Iterator<Duenio> itD = Duenios.getIterator();
                while (itD.hasNext()) {
                    dueniosJSON.put(itD.next().TOJSON());
                }
                jsonVet.put("duenios", dueniosJSON);

                // Citas

                JSONArray citasJSON = jsonVet.getJSONArray("citas");
                Iterator<Cita> itC = Citas.getIterator();
                while (itC.hasNext()) {
                    citasJSON.put(itC.next().citaTOJson());
                }
                jsonVet.put("citas", citasJSON);

            }catch(JSONException e){
                e.printStackTrace();
            }

            return jsonVet;
        }


        public void cargarVetJSON(String nombreArchivo){
            JSONObject jsonVet = this.toJSONVET();
            FileHandler.cargaJSONOBJ(jsonVet, nombreArchivo);
        }


    }
