package com.suli.suliapp.data.models.post.request;

import com.google.gson.annotations.SerializedName;

public class MeasurementValueRequest {

    @SerializedName("chain_custody")
    public Integer custodyChain;
    @SerializedName("max")
    public String max;
    @SerializedName("min")
    public String min;
    @SerializedName("avg")
    public String avg;
    @SerializedName("point_reference")
    public String referencePoint;
    @SerializedName("observation_measurement")
    public String observation;
    @SerializedName("type_lighting")
    public String typeLighting;

    public MeasurementValueRequest(Integer custodyChain, String max, String min, String avg, String referencePoint, String observation, String typeLighting) {
        this.custodyChain = custodyChain;
        this.max = max;
        this.min = min;
        this.avg = avg;
        this.referencePoint = referencePoint;
        this.observation = observation;
        this.typeLighting = typeLighting;
    }

    public Integer getCustodyChain() {
        return custodyChain;
    }

    public void setCustodyChain(Integer custodyChain) {
        this.custodyChain = custodyChain;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getReferencePoint() {
        return referencePoint;
    }

    public void setReferencePoint(String referencePoint) {
        this.referencePoint = referencePoint;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getTypeLighting() {
        return typeLighting;
    }

    public void setTypeLighting(String typeLighting) {
        this.typeLighting = typeLighting;
    }
}
