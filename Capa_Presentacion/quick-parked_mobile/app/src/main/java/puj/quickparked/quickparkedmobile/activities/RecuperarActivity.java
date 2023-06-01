package puj.quickparked.quickparkedmobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import puj.quickparked.quickparkedmobile.databinding.ActivityRecuperarBinding;

public class RecuperarActivity extends AppCompatActivity {

    private ActivityRecuperarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecuperarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}