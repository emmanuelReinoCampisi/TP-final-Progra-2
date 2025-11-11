import org.json.JSONObject;

import java.io.*;

public class FileHandler {


    public static void cargaJSONOBJ(JSONObject jsonObject, String nombreArchivo){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo+".json"));
            bw .write(jsonObject.toString());
            bw .flush();
            bw .close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public static String descargaJSON(String nombreArchivo){
        StringBuilder contenido = new StringBuilder();
        String lectura= "";
        try
        {
            BufferedReader entrada = new BufferedReader(new FileReader(nombreArchivo+".json"));
            while((lectura = entrada.readLine())!=null){
                contenido.append(lectura);
            }
            entrada.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        return contenido.toString();
    }
}
