package com.inteliment.intelimentviewwizard.models;

import com.google.gson.annotations.SerializedName;

public class FromCentral {

    @SerializedName("car") 
    private String car;
    @SerializedName("train") 
    private String train;

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
    }
}