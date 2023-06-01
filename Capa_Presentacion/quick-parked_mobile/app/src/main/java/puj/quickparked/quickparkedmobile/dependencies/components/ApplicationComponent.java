package puj.quickparked.quickparkedmobile.dependencies.components;


import javax.inject.Singleton;

import dagger.Component;
import puj.quickparked.quickparkedmobile.activities.BasicActivity;
import puj.quickparked.quickparkedmobile.activities.MapsFragment;
import puj.quickparked.quickparkedmobile.dependencies.modules.AlertsModule;
import puj.quickparked.quickparkedmobile.dependencies.modules.CameraModule;
import puj.quickparked.quickparkedmobile.dependencies.modules.GeocoderModule;
import puj.quickparked.quickparkedmobile.dependencies.modules.LocationModule;
import puj.quickparked.quickparkedmobile.dependencies.modules.PermissionModule;
import puj.quickparked.quickparkedmobile.dependencies.modules.ResponseLBModule;
import puj.quickparked.quickparkedmobile.dependencies.modules.RouterGoogleAPIModule;

@Singleton
@Component(modules = {AlertsModule.class, PermissionModule.class, LocationModule.class, RouterGoogleAPIModule.class, ResponseLBModule.class, GeocoderModule.class, CameraModule.class})
public interface ApplicationComponent {
    void inject(BasicActivity activity);
    void inject(MapsFragment fragment);
}
