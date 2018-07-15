package com.suli.suliapp.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Customer implements Serializable {

    @SerializedName("id")
    public Integer id;
    @SerializedName("number_ruc")
    public String numberRuc;
    @SerializedName("name")
    public String name;
    @SerializedName("number_telephone")
    public String numberTelephone;
    @SerializedName("consulting_company")
    public Integer consultingCompany;
}