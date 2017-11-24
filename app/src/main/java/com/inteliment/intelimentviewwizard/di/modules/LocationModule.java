package com.inteliment.intelimentviewwizard.di.modules;

import com.inteliment.intelimentviewwizard.di.scopes.UserScope;
import com.inteliment.intelimentviewwizard.interfaces.LocationApiInterface;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class LocationModule {

    @Provides
    @UserScope // needs to be consistent with the component scope
    public LocationApiInterface providesLocationInterface(Retrofit retrofit) {
        return retrofit.create(LocationApiInterface.class);
    }
}