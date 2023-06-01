package puj.quickparked.quickparkedmobile.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class BitmapUtils {
    //workaround from https://code.google.com/p/gmaps-api-issues/issues/detail?id=9011
    public static BitmapDescriptor getBitmapDescriptor(Context ctx, int id) {
        Drawable vectorDrawable = ContextCompat.getDrawable(ctx, id);
        int h = 128;
        int w = 128;
        vectorDrawable.setBounds(0, 0, w, h);
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bm);
    }
}
