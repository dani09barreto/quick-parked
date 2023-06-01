package puj.quickparked.quickparkedmobile.activities.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import puj.quickparked.quickparkedmobile.R;
import puj.quickparked.quickparkedmobile.databinding.DialogParkingOcupadoBinding;

public class ParqueaderoOpcupadoDialog extends Dialog {

    private DialogParkingOcupadoBinding binding;

    public ParqueaderoOpcupadoDialog(@NonNull Context context) {
        super(context);
        binding = DialogParkingOcupadoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setDimAmount(0.7f);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    public void dismissDialog() {
        dismiss();
    }
}
