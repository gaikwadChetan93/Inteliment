package com.inteliment.intelimentviewwizard.interfaces;

import com.inteliment.intelimentviewwizard.models.LocationInfo;

import java.util.ArrayList;

import retrofit2.http.GET;
import rx.Observable;

public interface LocationApiInterface {
  @GET("sample.json")
  Observable<ArrayList<LocationInfo>> getLocationData();
}