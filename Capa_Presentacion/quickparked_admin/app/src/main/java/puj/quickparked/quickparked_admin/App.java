package puj.quickparked.quickparked_admin;

import android.app.Application;

import lombok.Getter;
import puj.quickparked.quickparked_admin.dependencies.components.ApplicationComponent;
import puj.quickparked.quickparked_admin.dependencies.modules.LocationModule;

@Getter
public class App extends Application {
    ApplicationComponent appComponent = DaggerApplicationComponent.builder()
            .locationModule(new LocationModule(this))
            .build();
}
