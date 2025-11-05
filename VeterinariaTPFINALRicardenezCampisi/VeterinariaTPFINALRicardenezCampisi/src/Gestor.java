import java.util.HashSet;
import java.util.Iterator;

public class Gestor <T> {
    private HashSet<T> Personas;

    public Gestor(){
        Personas = new HashSet<>();
    }

    public void agregar(T t){
        Personas.add(t);
    }

    public void eliminar(T t){
        Personas.remove(t);
    }
    public String listar(){
        StringBuilder sb = new StringBuilder(" ");
        for (T elemento: Personas){
            sb.append(elemento.toString()).append("\n");
        }
        return sb.toString();
    }



}
