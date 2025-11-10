import Clases.Identificable;

import java.util.HashSet;
import java.util.Iterator;

public class Gestor <T extends Identificable>  {
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


    public String buscarPorId(int id) // con este buscamos por DNI
    {
        String mensaje = "";
        for(T t: elementos) if(t.getId()==id) return mensaje+=t.toString();
        return mensaje;
    }


    public String buscarPorIdentificador(String identificador) // con este buscamos por mail o matricula del empleado
    {
        String mensaje = "";
        for(T t: elementos){
            if(t.getIdentificador().equals(identificador)){

                mensaje+=t.toString();
            }
        }
        return mensaje;
    }
}
