package Clases;

import org.json.JSONObject;
/// Consultar interfaz JSONABLE con toJSON y fromJSON
public interface toJSON <T>{
    JSONObject toJSON (T t);
}
