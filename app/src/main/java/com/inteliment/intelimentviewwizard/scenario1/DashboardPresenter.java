package com.inteliment.intelimentviewwizard.scenario1;

import com.inteliment.intelimentviewwizard.R;

import java.util.Arrays;

/**
 * Created by chetan on 23/11/17.
 */

class DashboardPresenter {

    private DashboardActivity view;
    DashboardPresenter(DashboardActivity view) {
        this.view = view;
    }
    void removeView(){
        view = null;
    }

    void inflateList() {
        view.showItemList(Arrays.asList(view.getResources().getStringArray(R.array.items)));
    }
}
