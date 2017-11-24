package com.inteliment.intelimentviewwizard;

import android.app.Application;

import com.inteliment.intelimentviewwizard.di.components.DaggerLocationComponent;
import com.inteliment.intelimentviewwizard.di.components.DaggerNetComponent;
import com.inteliment.intelimentviewwizard.di.components.LocationComponent;
import com.inteliment.intelimentviewwizard.di.components.NetComponent;
import com.inteliment.intelimentviewwizard.di.modules.AppModule;
import com.inteliment.intelimentviewwizard.di.modules.LocationModule;
import com.inteliment.intelimentviewwizard.di.modules.NetModule;

/**
 * Created by chetan on 23-Nov-17.
 */

public class IntelimentApplication extends Application {
    private NetComponent mNetComponent;
    private LocationComponent mLocationComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .netModule(new NetModule(BuildConfig.BASE_URL))
                .build();

        mLocationComponent = DaggerLocationComponent.builder()
                .netComponent(mNetComponent)
                .locationModule(new LocationModule())
                .build();
    }
    public LocationComponent getmLocationComponent() { return mLocationComponent; }
}
