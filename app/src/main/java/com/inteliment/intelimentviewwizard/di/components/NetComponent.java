package com.inteliment.intelimentviewwizard.di.components;

import android.content.SharedPreferences;

import com.inteliment.intelimentviewwizard.di.modules.AppModule;
import com.inteliment.intelimentviewwizard.di.modules.NetModule;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;


@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
   Retrofit retrofit();
}