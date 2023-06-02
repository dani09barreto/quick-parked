package puj.quickparked.quickparkedmobile.model;

public class ServicesRoutes {

    public static final String BASE_URL = "https://quickparked.herokuapp.com/";

    public static String getUserServices (){
        return String.format("%s%s", BASE_URL, "api/usuarios/");
    }
}
