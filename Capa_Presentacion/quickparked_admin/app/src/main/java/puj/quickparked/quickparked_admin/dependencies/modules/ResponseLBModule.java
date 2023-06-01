package puj.quickparked.quickparked_admin.dependencies.modules;



import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import puj.quickparked.quickparked_admin.utils.ResponseLB;

@Module
public class ResponseLBModule {
    @Singleton
    @Provides
    public ResponseLB provideResponseLB() {
        return new ResponseLB();
    }
}
