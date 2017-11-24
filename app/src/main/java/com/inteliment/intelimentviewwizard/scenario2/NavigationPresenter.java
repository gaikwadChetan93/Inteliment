package com.inteliment.intelimentviewwizard.scenario2;

import com.inteliment.intelimentviewwizard.interfaces.LocationApiInterface;
import com.inteliment.intelimentviewwizard.models.LocationInfo;
import com.inteliment.intelimentviewwizard.utils.Utils;

import java.util.ArrayList;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chetan_g on 23/11/17.
 */

class NavigationPresenter {

    private NavigationActivity view;
    private LocationApiInterface locationApiInterface;
    NavigationPresenter(NavigationActivity view, LocationApiInterface locationApiInterface) {
        this.view = view;
        this.locationApiInterface = locationApiInterface;
    }
    void removeView(){
        view = null;
    }

    /**
     * Get location data from server
     */
    void getLocationData() {
        Utils.showLog("getLocationData", "getting location...");
        locationApiInterface.getLocationData().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<LocationInfo>>() {
                    @Override
                    public void onCompleted() {
                        Utils.showLog("getLocationData", "Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.showLog("getLocationData", "error : " + e.getMessage());
                    }

                    @Override
                    public void onNext(ArrayList<LocationInfo> locations) {
                        Utils.showLog("getLocationData", "error : " + locations.toString());
                        view.showData(locations);
                    }
                });
    }
}
