package puj.quickparked.quickparkedmobile.model;

public class SedeParqueaderoDTO {

    private Integer id;

    private String nombreSede;

    private double latitud;

    private double longitud;

    private Integer cupo;

    private String imagen;

    private Integer parqueadero;
    private String direccion;

    public SedeParqueaderoDTO() {
    }

    public SedeParqueaderoDTO(Integer id, String nombreSede, double latitud, double longitud, Integer cupo, String imagen, Integer parqueadero, String direccion) {
        this.id = id;
        this.nombreSede = nombreSede;
        this.latitud = latitud;
        this.longitud = longitud;
        this.cupo = cupo;
        this.imagen = imagen;
        this.parqueadero = parqueadero;
        this.direccion = direccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Integer getCupo() {
        return cupo;
    }

    public void setCupo(Integer cupo) {
        this.cupo = cupo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getParqueadero() {
        return parqueadero;
    }

    public void setParqueadero(Integer parqueadero) {
        this.parqueadero = parqueadero;
    }
}