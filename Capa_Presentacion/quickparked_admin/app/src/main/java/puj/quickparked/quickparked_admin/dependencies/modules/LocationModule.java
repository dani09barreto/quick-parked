package puj.quickparked.quickparked_admin.dependencies.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lombok.AllArgsConstructor;
import puj.quickparked.quickparked_admin.services.LocationService;

@Module
@AllArgsConstructor
public class LocationModule {
    private final Application application;

    @Provides
    @Singleton
    Application providesApplication(){
        return application;
    }

    @Provides
    public LocationService provideLocationService() {
        return new LocationService(application.getApplicationContext());
    }
}
