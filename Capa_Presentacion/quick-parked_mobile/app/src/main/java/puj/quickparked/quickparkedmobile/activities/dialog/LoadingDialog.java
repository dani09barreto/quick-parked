package puj.quickparked.quickparkedmobile.activities.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.ViewGroup;

import puj.quickparked.quickparkedmobile.R;


public class LoadingDialog extends Dialog {
    public LoadingDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_loading);
        setCancelable(false);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setDimAmount(0.5f);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    public void dismissDialog() {
        dismiss();
    }
}

