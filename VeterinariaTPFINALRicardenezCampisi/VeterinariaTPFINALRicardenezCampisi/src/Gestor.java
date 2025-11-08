import java.util.HashSet;
import java.util.Iterator;

public class Gestor <T> {
    private HashSet<T> elementos;

    public Gestor(){
        elementos = new HashSet<>();
    }

    public void agregar(T t){
        elementos.add(t);
    }

    public boolean existe(T e){
        return elementos.contains(e);
    }

    public String listar(){
        StringBuilder sb = new StringBuilder(" ");
        for (T elemento: elementos){
            sb.append(elemento.toString()).append("\n");
        }
        return sb.toString();
    }

public Iterator <T> getIterator(){

        return elementos.iterator();
    }

}
