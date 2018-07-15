package com.suli.suliapp.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProjectResponse implements Serializable {

    @SerializedName("id")
    public Integer id;
    @SerializedName("status")
    public Status status;
    @SerializedName("customer")
    public Customer customer;
    @SerializedName("mining_units")
    public List<MiningUnit> miningUnits = null;
    @SerializedName("observations")
    public String observations;
    @SerializedName("start_date")
    public String startDate;
    @SerializedName("end_date")
    public String endDate;
}
