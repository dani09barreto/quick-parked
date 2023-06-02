package puj.quickparked.quickparkedmobile.dependencies.modules;



import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import puj.quickparked.quickparkedmobile.utils.AlertsHelper;

@Module
public class AlertsModule {
    @Singleton
    @Provides
    public AlertsHelper provideAlertHelper() {
        return new AlertsHelper();
    }
}
