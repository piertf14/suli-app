package com.suli.suliapp.data.models.post.response;

import com.google.gson.annotations.SerializedName;

public class InstrumentResponse {

    @SerializedName("id")
    public Integer id;
    @SerializedName("name")
    public String name;
    @SerializedName("brand")
    public String brand;
    @SerializedName("model")
    public String model;
    @SerializedName("series")
    public String series;
    @SerializedName("code_certification")
    public Integer codeCertification;

    public InstrumentResponse() {
    }

    public InstrumentResponse(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Integer getCodeCertification() {
        return codeCertification;
    }

    public void setCodeCertification(Integer codeCertification) {
        this.codeCertification = codeCertification;
    }

    @Override
    public String toString() {
        return name;
    }
}