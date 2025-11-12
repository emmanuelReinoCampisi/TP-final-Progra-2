package Clases;

import org.json.JSONObject;
/// Consultar interfaz JSONABLE con toJSON y fromJSON     ||| Esto va
public interface JSONable<T>{
    JSONObject toJSON (T t);
    T fromJSON (JSONObject t);
}
