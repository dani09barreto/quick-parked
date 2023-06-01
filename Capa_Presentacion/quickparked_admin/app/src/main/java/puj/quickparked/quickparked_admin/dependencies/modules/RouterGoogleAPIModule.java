package puj.quickparked.quickparked_admin.dependencies.modules;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lombok.AllArgsConstructor;
import puj.quickparked.quickparked_admin.services.RouterGoogleAPIService;

@Module
@AllArgsConstructor
public class RouterGoogleAPIModule {

    @Provides
    @Singleton
    public RouterGoogleAPIService provideRouterGoogleARIService (){
        return new RouterGoogleAPIService();
    }
}
