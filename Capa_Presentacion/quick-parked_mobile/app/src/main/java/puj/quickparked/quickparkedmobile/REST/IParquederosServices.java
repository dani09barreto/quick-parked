package puj.quickparked.quickparkedmobile.REST;

import java.util.List;

import puj.quickparked.quickparkedmobile.model.SedeParqueaderoDTO;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IParquederosServices {

    @GET("/api/sedeParqueaderos")
    Call <List <SedeParqueaderoDTO> > getParqueaderos();
}
