package puj.quickparked.quickparkedmobile.dependencies.modules;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import puj.quickparked.quickparkedmobile.services.CameraServices;

@Module
public class CameraModule {
    @Singleton
    @Provides
    public CameraServices provideCameraService() {
        return new CameraServices();
    }
}
