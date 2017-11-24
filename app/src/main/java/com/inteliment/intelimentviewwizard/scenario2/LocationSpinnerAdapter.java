package com.inteliment.intelimentviewwizard.scenario2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.inteliment.intelimentviewwizard.R;
import com.inteliment.intelimentviewwizard.models.LocationInfo;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Chetan on 23/11/17.
 */

class LocationSpinnerAdapter extends ArrayAdapter<LocationInfo> {

    private List<LocationInfo> locationInfoList;

    LocationSpinnerAdapter(Context context, List<LocationInfo> data) {
        super(context, android.R.layout.simple_spinner_dropdown_item);
        locationInfoList = data;
    }

    @Override public int getCount() {
        return locationInfoList == null ? 0 : locationInfoList.size();
    }

    @Override public LocationInfo getItem(int position) {
        return locationInfoList.get(position);
    }

    @Override public long getItemId(int position) {
        return locationInfoList.get(position).getId();
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_dropdown_item_1line, parent, false);
        }

        LocationInfo locationInfo = locationInfoList.get(position);
        TextView tvText = ButterKnife.findById(convertView, android.R.id.text1);
        tvText.setText(locationInfo.getName());
        convertView.setTag(locationInfo);

        return convertView;
    }

    @Override public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_spinner_dropdown_item, parent, false);
        }
        ((TextView) convertView).setText(locationInfoList.get(position).getName());
        return convertView;
    }
}
