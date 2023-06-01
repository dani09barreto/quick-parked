package puj.quickparked.quickparkedmobile.model;

public class ServicesRoutes {

    private static final String BASE_URL = "https://quickparked.herokuapp.com";

    public static String getUserServices (){
        return String.format("%s%s", BASE_URL, "/api/usuarios/");
    }
}
