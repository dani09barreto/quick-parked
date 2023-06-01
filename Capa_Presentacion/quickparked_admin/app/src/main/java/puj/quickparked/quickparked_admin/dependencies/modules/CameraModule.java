package puj.quickparked.quickparked_admin.dependencies.modules;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import puj.quickparked.quickparked_admin.services.CameraServices;

@Module
public class CameraModule {
    @Singleton
    @Provides
    public CameraServices provideCameraService() {
        return new CameraServices();
    }
}
