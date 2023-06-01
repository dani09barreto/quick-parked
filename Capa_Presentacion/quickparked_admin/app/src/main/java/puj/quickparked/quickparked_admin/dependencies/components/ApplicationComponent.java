package puj.quickparked.quickparked_admin.dependencies.components;


import javax.inject.Singleton;

import dagger.Component;
import puj.quickparked.quickparked_admin.activities.BasicActivity;
import puj.quickparked.quickparked_admin.dependencies.modules.AlertsModule;
import puj.quickparked.quickparked_admin.dependencies.modules.CameraModule;
import puj.quickparked.quickparked_admin.dependencies.modules.GeocoderModule;
import puj.quickparked.quickparked_admin.dependencies.modules.LocationModule;
import puj.quickparked.quickparked_admin.dependencies.modules.PermissionModule;
import puj.quickparked.quickparked_admin.dependencies.modules.ResponseLBModule;
import puj.quickparked.quickparked_admin.dependencies.modules.RouterGoogleAPIModule;

@Singleton
@Component(modules = {AlertsModule.class, PermissionModule.class, LocationModule.class, RouterGoogleAPIModule.class, ResponseLBModule.class, GeocoderModule.class, CameraModule.class})
public interface ApplicationComponent {
    void inject(BasicActivity activity);
}
