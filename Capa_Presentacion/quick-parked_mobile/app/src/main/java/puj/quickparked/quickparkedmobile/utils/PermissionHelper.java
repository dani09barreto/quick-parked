package puj.quickparked.quickparkedmobile.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import lombok.Getter;

@Getter
public class PermissionHelper {
    private static final String TAG = PermissionHelper.class.getName();

    static public final int PERMISSIONS_REQUEST_CAMERA = 1001;
    static public final int PERMISSIONS_REQUEST_GALLERY = 1002;
    static public final int PERMISSIONS_REQUEST_READ_CONTACTS = 2002;
    static public final int PERMISSIONS_LOCATION = 3003;

    private boolean mCameraPermissionGranted;
    private boolean mGalleryPermissionGranted;
    private boolean mContactsPermissionGranted;
    private boolean mLocationPermissionGranted;

    public void getCameraPermission(Activity activity) {
        if(checkPermission(activity, Manifest.permission.CAMERA)){
            mCameraPermissionGranted = true;
        } else ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_CAMERA);
    }

    public void getGaelleryPermission(Activity activity) {
        if(checkPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)){
            mGalleryPermissionGranted = true;
        } else ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_GALLERY);
    }

    public void getContactsPermission(Activity activity){
        if(checkPermission(activity, Manifest.permission.READ_CONTACTS)){
            mContactsPermissionGranted = true;
        } else ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
    }

    public void getLocationPermission(Activity activity){
        if(checkPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)){
            mLocationPermissionGranted = true;
        } else ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_LOCATION);
    }

    private boolean checkPermission(Activity activity, String manifestPermissions) {
        /*
         * Request the permission. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        Log.d(TAG, "checkPermission: attempting to get permission for ("+manifestPermissions+").");
        if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), manifestPermissions) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "checkPermission: permission "+manifestPermissions+" is already granted.");
            return true;
        } else {
            Log.d(TAG, "checkPermission: permission ("+manifestPermissions+") not granted, need to request it.");
            return false;
        }
    }

}