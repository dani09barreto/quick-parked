package puj.quickparked.quickparkedmobile.dependencies.modules;



import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import puj.quickparked.quickparkedmobile.utils.ResponseLB;

@Module
public class ResponseLBModule {
    @Singleton
    @Provides
    public ResponseLB provideResponseLB() {
        return new ResponseLB();
    }
}
