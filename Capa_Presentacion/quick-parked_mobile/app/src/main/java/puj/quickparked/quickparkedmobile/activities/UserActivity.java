package puj.quickparked.quickparkedmobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import puj.quickparked.quickparkedmobile.R;
import puj.quickparked.quickparkedmobile.REST.IUsuarioServices;
import puj.quickparked.quickparkedmobile.databinding.ActivityUserBinding;
import puj.quickparked.quickparkedmobile.model.ServicesRoutes;
import puj.quickparked.quickparkedmobile.model.UsuarioDTO;
import puj.quickparked.quickparkedmobile.utils.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AuthenticatedActivity {

    private ActivityUserBinding binding;
    private IUsuarioServices usuarioServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(v -> {
            finish();
        });

        initUser();

    }

    private void initUser() {
        usuarioServices = RetrofitClient.getRetrofitInstance(ServicesRoutes.getUserServices(), getTokenUser()).create(IUsuarioServices.class);
        Integer userId = Integer.valueOf(getUserId());
        Call<UsuarioDTO> call = usuarioServices.getUsuarioById(userId);

        call.enqueue(new Callback<UsuarioDTO>() {
            @Override
            public void onResponse(Call<UsuarioDTO> call, Response<UsuarioDTO> response) {
                if (response.isSuccessful()) {
                    UsuarioDTO usuario = response.body();
                    binding.userEmail.getEditText().setText(usuario.getEmail());
                    binding.userName.getEditText().setText(usuario.getNombres());
                    binding.userLastName.getEditText().setText(usuario.getApellidos());
                    binding.userPhone.getEditText().setText(usuario.getTelefono());
                }
            }

            @Override
            public void onFailure(Call<UsuarioDTO> call, Throwable t) {

            }
        });
    }
}