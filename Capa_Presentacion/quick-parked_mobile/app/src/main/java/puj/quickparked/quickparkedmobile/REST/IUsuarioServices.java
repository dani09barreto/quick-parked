package puj.quickparked.quickparkedmobile.REST;

import lombok.Getter;
import puj.quickparked.quickparkedmobile.model.IniciarSesionDTO;
import puj.quickparked.quickparkedmobile.model.RespuestaIniciarSesionDTO;
import puj.quickparked.quickparkedmobile.model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IUsuarioServices {
    @POST("IniciarSesion")
    Call <RespuestaIniciarSesionDTO> login (@Body IniciarSesionDTO iniciarSesionDTO);
    @GET("{id}")
    Call <UsuarioDTO> getUsuarioById (@Path("id") Integer id);
}
