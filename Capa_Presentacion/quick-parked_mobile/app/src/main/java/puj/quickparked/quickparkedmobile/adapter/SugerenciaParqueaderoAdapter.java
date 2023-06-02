package puj.quickparked.quickparkedmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import puj.quickparked.quickparkedmobile.databinding.SugerenciaAdapterBinding;
import puj.quickparked.quickparkedmobile.model.SedeParqueaderoDTO;
import puj.quickparked.quickparkedmobile.model.SugerenciaParqueadero;
import puj.quickparked.quickparkedmobile.utils.DistanceUtils;

public class SugerenciaParqueaderoAdapter extends ArrayAdapter <SugerenciaParqueadero> {


    public SugerenciaParqueaderoAdapter(@NonNull Context context, int resource, @NonNull List<SugerenciaParqueadero> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        SugerenciaParqueadero sugerenciaParqueadero = getItem(position);
        SugerenciaAdapterBinding binding;
        if (convertView == null) {
            binding = SugerenciaAdapterBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        } else {
            binding = SugerenciaAdapterBinding.bind(convertView);
        }
        binding.txtNombre.setText(sugerenciaParqueadero.getNombre());
        binding.txtDistancia.setText(String.format("%s km", sugerenciaParqueadero.getDistancia()));
        return binding.getRoot();
    }
}
