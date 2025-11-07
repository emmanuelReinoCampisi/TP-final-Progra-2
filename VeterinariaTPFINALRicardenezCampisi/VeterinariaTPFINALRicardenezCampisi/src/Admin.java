import org.json.JSONException;
import org.json.JSONObject;

public class Admin {

    private String email = "admin@gmail.com";
    private String password = "veterinaria777";


    public Admin() {
    }

    public JSONObject toJsonAdmin() {
        JSONObject admin = new JSONObject();

        try{
            admin.put("email", email);
            admin.put("password", password);
        }catch(JSONException e){
            e.printStackTrace();
        }

        return admin;
    }

    
}
