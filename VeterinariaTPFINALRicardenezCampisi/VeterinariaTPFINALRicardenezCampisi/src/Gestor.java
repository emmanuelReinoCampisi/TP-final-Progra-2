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

    public boolean existe(T e){
        return Personas.contains(e);
    }

    public String listar(){
        StringBuilder sb = new StringBuilder(" ");
        for (T elemento: Personas){
            sb.append(elemento.toString()).append("\n");
        }
        return sb.toString();
    }



}
