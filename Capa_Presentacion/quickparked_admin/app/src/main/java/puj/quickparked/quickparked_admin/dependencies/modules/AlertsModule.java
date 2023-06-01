package puj.quickparked.quickparked_admin.dependencies.modules;



import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import puj.quickparked.quickparked_admin.utils.AlertsHelper;

@Module
public class AlertsModule {
    @Singleton
    @Provides
    public AlertsHelper provideAlertHelper() {
        return new AlertsHelper();
    }
}
