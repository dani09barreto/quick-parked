package puj.quickparked.quickparkedmobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.Objects;

import puj.quickparked.quickparkedmobile.R;
import puj.quickparked.quickparkedmobile.REST.IUsuarioServices;
import puj.quickparked.quickparkedmobile.activities.dialog.ParqueaderoLibreDialog;
import puj.quickparked.quickparkedmobile.activities.dialog.ParqueaderoOpcupadoDialog;
import puj.quickparked.quickparkedmobile.activities.dialog.ReservaDialog;
import puj.quickparked.quickparkedmobile.databinding.ActivityLoginBinding;
import puj.quickparked.quickparkedmobile.model.IniciarSesionDTO;
import puj.quickparked.quickparkedmobile.model.RespuestaIniciarSesionDTO;
import puj.quickparked.quickparkedmobile.model.ServicesRoutes;
import puj.quickparked.quickparkedmobile.utils.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BasicActivity {

    private IUsuarioServices usuarioServices;

    private ActivityLoginBinding binding;

    public static Intent createIntent(Activity activity) {
        return new Intent(activity, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater()); // Inflate the layout
        setContentView(binding.getRoot()); // Set the content view to the inflated layout

        binding.textRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SingUpActivity.class));
        });
        binding.textViewRecuperar.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RecuperarActivity.class));
        });

        binding.btnIngresar.setOnClickListener(v -> {
            doLogin();
        });
    }

    private void doLogin() {
        loadingDialog.show();
        String user = Objects.requireNonNull(binding.loginUsername.getEditText()).getText().toString();
        String pass = Objects.requireNonNull(binding.loginPassword.getEditText()).getText().toString();

        if (user.isEmpty()) {
            alertsHelper.shortSimpleSnackbar(binding.getRoot(), getString(R.string.mail_error_label));
            binding.loginUsername.setErrorEnabled(true);
            binding.loginUsername.setError(getString(R.string.mail_error_label));
            return;
        }

        if (pass.isEmpty()) {
            alertsHelper.shortSimpleSnackbar(binding.getRoot(), getString(R.string.error_pass_label));
            binding.loginPassword.setErrorEnabled(true);
            binding.loginPassword.setError(getString(R.string.error_pass_label));
            return;
        }

        IniciarSesionDTO iniciarSesionDTO = new IniciarSesionDTO(user, pass);

        usuarioServices = RetrofitClient.getRetrofitInstance(ServicesRoutes.getUserServices()).create(IUsuarioServices.class);

        Call <RespuestaIniciarSesionDTO> call = usuarioServices.login(iniciarSesionDTO);

        call.enqueue(new Callback<RespuestaIniciarSesionDTO>() {
            @Override
            public void onResponse(Call<RespuestaIniciarSesionDTO> call, Response<RespuestaIniciarSesionDTO> response) {
                if (!response.isSuccessful()){
                    alertsHelper.shortToast(getApplicationContext(), "Error: " + response.code());
                    return;
                }
                RespuestaIniciarSesionDTO respuestaIniciarSesionDTO = response.body();
                SharedPreferences sharedPreferences = getSharedPreferences("session", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("token", respuestaIniciarSesionDTO.getToken());
                editor.putString("username", respuestaIniciarSesionDTO.getUsername());
                editor.putString("idUser", respuestaIniciarSesionDTO.getUsuarioId());
                editor.commit();
                loadingDialog.dismissDialog();
                startActivity(new Intent(LoginActivity.this, MapsActivity.class));
            }

            @Override
            public void onFailure(Call<RespuestaIniciarSesionDTO> call, Throwable t) {
                alertsHelper.shortSimpleSnackbar(binding.getRoot(), "Error: " + t.getMessage());
            }
        });
    }


}