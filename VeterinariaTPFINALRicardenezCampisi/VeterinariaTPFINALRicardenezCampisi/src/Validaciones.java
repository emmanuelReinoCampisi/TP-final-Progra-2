import Excepciones.ExcepcionFormatoNoValido;
import Excepciones.ExcepcionNoCoincide;

public class Validaciones {

    private static final String regexPermitidos = "^[a-zA-Z0-9.]+$";

    public boolean validarFormatoEmail(String email)throws ExcepcionFormatoNoValido{
        boolean emailValido = false;
        if(email.endsWith("@gmail.com")){
            String nombreUsuario = email.substring(0,email.length() - 10);
            if(nombreUsuario.length()>5 && nombreUsuario.length()<31){
                if (nombreUsuario.startsWith(".")){
                    throw new ExcepcionFormatoNoValido("El email no puede comenzar con punto.");
                } else {
                    if(!nombreUsuario.matches(regexPermitidos)){
                        throw new ExcepcionFormatoNoValido("El email solo puede contener letras, numeros y puntos.");
                    }
                    else {
                        emailValido = true;
                    }
                }
            } else {
                throw new ExcepcionFormatoNoValido("El email debe tener entre 6 y 30 caracteres.");
            }
        }else {
            throw new ExcepcionFormatoNoValido("El email debe terminar en @gmail.com.");
        }


        return emailValido;
    }

    public boolean validarFormatoContrasenia(String contrasenia)throws ExcepcionFormatoNoValido{
        boolean contraseniaValida = false;

        if(contrasenia.length()<6 || contrasenia.length()>15){
           throw new ExcepcionFormatoNoValido("La contrasenia debe tener entre 6 y 15 caracteres");
        }else {
            contraseniaValida = true;
        }
        return contraseniaValida;
    }

    public boolean validarMismaContrasenia(String contraseniaUno, String contraseniaDos)throws ExcepcionNoCoincide{
        boolean mismaContrasenia = false;

        if(contraseniaUno.equals(contraseniaDos)){
            mismaContrasenia = true;
        }else {
           throw new ExcepcionNoCoincide("Las contrasenias no coinciden");
        }
        return mismaContrasenia;
    }
}
