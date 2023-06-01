package puj.quickparked.quickparked_admin.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import puj.quickparked.quickparked_admin.R;

public class AlertsHelper {
    private static final String TAG = AlertsHelper.class.getName();

    public void shortToast(Context context, String message) {
        Log.i(TAG, String.format("shortToast: %s", message));
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void longToast(Context context, String message) {
        Log.i(TAG, String.format("longToast: %s", message));
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
    }

    public void shortSimpleSnackbar(View parentView, String message) {
        Log.i(TAG, String.format("shortSimpleSnackBar: %s", message));
        Snackbar snackbar = Snackbar.make(parentView, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    public void longSimpleSnackbar(View parentView, String message) {
        Log.i(TAG, String.format("longSimpleSnackBar: %s", message));
        Snackbar snackbar = Snackbar.make(parentView, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void indefiniteSnackbar(View parentView, String message) {
        Log.i(TAG, String.format("indefiniteSnackbar: %s", message));
        Snackbar snackbar = Snackbar.make(parentView, message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.snackBar_dismiss_label, view -> snackbar.dismiss());
        snackbar.show();
    }
}