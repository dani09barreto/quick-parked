package puj.quickparked.quickparked_admin.dependencies.modules;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import puj.quickparked.quickparked_admin.utils.PermissionHelper;


@Module
public class PermissionModule {

    @Singleton
    @Provides
    public PermissionHelper providePermissionHelper() {
        return new PermissionHelper();
    }
}