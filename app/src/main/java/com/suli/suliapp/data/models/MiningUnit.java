package com.suli.suliapp.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MiningUnit implements Serializable {

    @SerializedName("id")
    public Integer id;
    @SerializedName("chain_custody")
    public List<CustodyChain> custodyChain = null;
    @SerializedName("name")
    public String name;
    @SerializedName("customer")
    public Integer customer;

}
