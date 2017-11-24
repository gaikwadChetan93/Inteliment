package com.inteliment.intelimentviewwizard.models;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("latitude") 
    private Double latitude;
    @SerializedName("longitude") 
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


}