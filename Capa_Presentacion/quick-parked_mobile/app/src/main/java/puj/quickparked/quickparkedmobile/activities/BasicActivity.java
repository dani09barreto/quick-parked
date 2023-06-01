package puj.quickparked.quickparkedmobile.activities;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import puj.quickparked.quickparkedmobile.App;
import puj.quickparked.quickparkedmobile.activities.dialog.LoadingDialog;
import puj.quickparked.quickparkedmobile.services.CameraServices;
import puj.quickparked.quickparkedmobile.services.GeocoderService;
import puj.quickparked.quickparkedmobile.services.LocationService;
import puj.quickparked.quickparkedmobile.services.RouterGoogleAPIService;
import puj.quickparked.quickparkedmobile.utils.AlertsHelper;
import puj.quickparked.quickparkedmobile.utils.PermissionHelper;
import puj.quickparked.quickparkedmobile.utils.ResponseLB;

public abstract class BasicActivity extends AppCompatActivity {

    protected SharedPreferences sharedPreferences;
    protected LoadingDialog loadingDialog;
    @Inject
    AlertsHelper alertsHelper;
    @Inject
    PermissionHelper permissionHelper;
    @Inject
    LocationService locationService;
    @Inject
    RouterGoogleAPIService routerGoogleAPIService;
    @Inject
    ResponseLB responseLB;
    @Inject
    CameraServices cameraServices;
    @Inject
    GeocoderService geocoderService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ((App) getApplicationContext()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        loadingDialog = new LoadingDialog(this);
    }
}
