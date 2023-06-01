package puj.quickparked.quickparkedmobile;

import android.app.Application;

import lombok.Getter;
import puj.quickparked.quickparkedmobile.dependencies.components.ApplicationComponent;
import puj.quickparked.quickparkedmobile.dependencies.components.DaggerApplicationComponent;
import puj.quickparked.quickparkedmobile.dependencies.modules.GeocoderModule;
import puj.quickparked.quickparkedmobile.dependencies.modules.LocationModule;

@Getter
public class App extends Application {
    ApplicationComponent appComponent = DaggerApplicationComponent.builder()
            .locationModule(new LocationModule(this))
            .geocoderModule(new GeocoderModule(this))
            .build();
}
