package com.inteliment.intelimentviewwizard.di.components;

import com.inteliment.intelimentviewwizard.scenario1.DashboardActivity;
import com.inteliment.intelimentviewwizard.di.modules.LocationModule;
import com.inteliment.intelimentviewwizard.di.scopes.UserScope;
import com.inteliment.intelimentviewwizard.scenario2.NavigationActivity;

import dagger.Component;


@UserScope // using the previously defined scope, note that @Singleton will not work
@Component(dependencies = NetComponent.class, modules = LocationModule.class)
public interface LocationComponent {
    void inject(DashboardActivity activity);
    void inject(NavigationActivity navigationActivity);
}