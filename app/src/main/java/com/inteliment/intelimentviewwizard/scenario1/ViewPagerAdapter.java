package com.inteliment.intelimentviewwizard.scenario1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by 638 on 23-Nov-17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 4;

    ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance("Fragment " + position);
    }
}