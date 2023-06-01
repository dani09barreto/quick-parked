package puj.quickparked.quickparkedmobile.dependencies.modules;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import lombok.AllArgsConstructor;
import puj.quickparked.quickparkedmobile.services.RouterGoogleAPIService;

@Module
@AllArgsConstructor
public class RouterGoogleAPIModule {

    @Provides
    @Singleton
    public RouterGoogleAPIService provideRouterGoogleARIService (){
        return new RouterGoogleAPIService();
    }
}
