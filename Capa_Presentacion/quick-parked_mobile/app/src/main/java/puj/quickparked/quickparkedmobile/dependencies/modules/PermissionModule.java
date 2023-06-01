package puj.quickparked.quickparkedmobile.dependencies.modules;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import puj.quickparked.quickparkedmobile.utils.PermissionHelper;


@Module
public class PermissionModule {

    @Singleton
    @Provides
    public PermissionHelper providePermissionHelper() {
        return new PermissionHelper();
    }
}