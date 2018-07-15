package com.suli.suliapp.data.models.post.response;

import com.google.gson.annotations.SerializedName;

public class MeasurementValueResponse {

    @SerializedName("id")
    public Integer id;
    @SerializedName("max")
    public Integer max;
    @SerializedName("min")
    public Integer min;
    @SerializedName("avg")
    public Integer avg;
    @SerializedName("point_reference")
    public String pointReference;
    @SerializedName("observation_measurement")
    public String observationMeasurement;
    @SerializedName("type_lighting")
    public String typeLighting;
    @SerializedName("chain_custody")
    public Integer chainCustody;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getAvg() {
        return avg;
    }

    public void setAvg(Integer avg) {
        this.avg = avg;
    }

    public String getPointReference() {
        return pointReference;
    }

    public void setPointReference(String pointReference) {
        this.pointReference = pointReference;
    }

    public String getObservationMeasurement() {
        return observationMeasurement;
    }

    public void setObservationMeasurement(String observationMeasurement) {
        this.observationMeasurement = observationMeasurement;
    }

    public String getTypeLighting() {
        return typeLighting;
    }

    public void setTypeLighting(String typeLighting) {
        this.typeLighting = typeLighting;
    }

    public Integer getChainCustody() {
        return chainCustody;
    }

    public void setChainCustody(Integer chainCustody) {
        this.chainCustody = chainCustody;
    }
}