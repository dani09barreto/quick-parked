package puj.quickparked.quickparkedmobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import puj.quickparked.quickparkedmobile.activities.dialog.ParqueaderoLibreDialog;
import puj.quickparked.quickparkedmobile.activities.dialog.ParqueaderoOpcupadoDialog;
import puj.quickparked.quickparkedmobile.activities.dialog.ReservaDialog;
import puj.quickparked.quickparkedmobile.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

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

        ReservaDialog dialogReserva = new ReservaDialog(this);
        //dialogReserva.show();
        ParqueaderoOpcupadoDialog dialogParqueaderoOpcupado = new ParqueaderoOpcupadoDialog(this);
        //dialogParqueaderoOpcupado.show();

        ParqueaderoLibreDialog dialogParqueaderoLibre = new ParqueaderoLibreDialog(this);
        dialogParqueaderoLibre.show();
    }


}